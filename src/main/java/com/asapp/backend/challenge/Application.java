package com.asapp.backend.challenge;

import static spark.Spark.exception;

import com.asapp.backend.challenge.controller.AuthController;
import com.asapp.backend.challenge.controller.HealthController;
import com.asapp.backend.challenge.controller.MessagesController;
import com.asapp.backend.challenge.controller.UsersController;
import com.asapp.backend.challenge.controller.model.ErrorResponse;
import com.asapp.backend.challenge.exceptions.ContentTypeNotSupportedException;
import com.asapp.backend.challenge.exceptions.MissingParametersException;
import com.asapp.backend.challenge.exceptions.UserNotFoundException;
import com.asapp.backend.challenge.exceptions.UsernameAlreadyExistsException;
import com.asapp.backend.challenge.filter.TokenValidatorFilter;
import com.asapp.backend.challenge.repository.MessageRepository;
import com.asapp.backend.challenge.repository.SqLiteImplementation.MessageSqlLiteRepository;
import com.asapp.backend.challenge.repository.SqLiteImplementation.UserSqlLiteRepository;
import com.asapp.backend.challenge.repository.UserRepository;
import com.asapp.backend.challenge.service.AuthenticationService;
import com.asapp.backend.challenge.service.MessageService;
import com.asapp.backend.challenge.service.TokenValidatorService;
import com.asapp.backend.challenge.service.UserService;
import com.asapp.backend.challenge.service.implementation.AuthenticationServiceImpl;
import com.asapp.backend.challenge.service.implementation.MessageServiceImpl;
import com.asapp.backend.challenge.service.implementation.TokenValidatorServiceImpl;
import com.asapp.backend.challenge.service.implementation.UserServiceImpl;
import com.asapp.backend.challenge.utils.JSONUtil;
import com.asapp.backend.challenge.utils.Path;
import org.sql2o.Sql2o;
import spark.Spark;

public class Application {

    public static final Sql2o DATASOURCE = new Sql2o("jdbc:sqlite:sample.db", null, null);
    public static final String SECRET_JWT_KEY = "secret_jwt_key";

    public static void main(String[] args) {

        //Repositories
        UserRepository userRepository = new UserSqlLiteRepository(DATASOURCE);
        MessageRepository messageRepository = new MessageSqlLiteRepository(DATASOURCE);

        //Services
        TokenValidatorService tokenValidatorService = new TokenValidatorServiceImpl(SECRET_JWT_KEY);
        UserService userService = new UserServiceImpl(userRepository);
        AuthenticationService authenticationService = new AuthenticationServiceImpl(userService, tokenValidatorService);
        MessageService messageService = new MessageServiceImpl(messageRepository, userService);

        //Controllers
        UsersController usersController = new UsersController(userService);
        AuthController authController = new AuthController(authenticationService);
        MessagesController messagesController = new MessagesController(messageService);

        //Filters
        TokenValidatorFilter tokenValidatorFilter = new TokenValidatorFilter(tokenValidatorService);

        // Spark Configuration
        Spark.port(8080);

        // Users
        Spark.post(Path.USERS, usersController.createUser);
        // Auth
        Spark.post(Path.LOGIN, authController.login);
        // Messages
        Spark.before(Path.MESSAGES, tokenValidatorFilter.validateUser);
        Spark.post(Path.MESSAGES, messagesController.sendMessage);
        Spark.get(Path.MESSAGES, messagesController.getMessages);
        // Health
        Spark.post(Path.HEALTH, HealthController.check);
        // Errors
        exception(UserNotFoundException.class, (e, req, res) -> {
            res.type("application/json");
            res.body(JSONUtil.dataToJson(new ErrorResponse("User.error", e.getMessage())));
            res.status(404);
        });
        exception(MissingParametersException.class, (e, req, res) -> {
            res.type("application/json");
            res.body(JSONUtil.dataToJson(new ErrorResponse("MissingParameter.error", e.getMessage())));
            res.status(400);
        });
        exception(ContentTypeNotSupportedException.class, (e, req, res) -> {
            res.type("application/json");
            res.body(JSONUtil.dataToJson(new ErrorResponse("ContentType.error", e.getMessage())));
            res.status(409);
        });
        exception(UsernameAlreadyExistsException.class, (e, req, res) -> {
            res.type("application/json");
            res.body(JSONUtil.dataToJson(new ErrorResponse("User.error", e.getMessage())));
            res.status(409);
        });
        exception(Exception.class, (e, req, res) -> {
            res.type("application/json");
            res.body(JSONUtil.dataToJson(new ErrorResponse("Internal server error")));
            res.status(500);
        });
    }

}
