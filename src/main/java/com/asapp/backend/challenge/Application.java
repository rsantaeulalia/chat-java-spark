package com.asapp.backend.challenge;

import static spark.Spark.before;

import com.asapp.backend.challenge.controller.AuthController;
import com.asapp.backend.challenge.controller.HealthController;
import com.asapp.backend.challenge.controller.MessagesController;
import com.asapp.backend.challenge.controller.UsersController;
import com.asapp.backend.challenge.filter.TokenValidatorFilter;
import com.asapp.backend.challenge.repository.MessageRepository;
import com.asapp.backend.challenge.repository.MessageSqlLiteRepository;
import com.asapp.backend.challenge.repository.UserRepository;
import com.asapp.backend.challenge.repository.UserSqlLiteRepository;
import com.asapp.backend.challenge.service.AuthenticationService;
import com.asapp.backend.challenge.service.AuthenticationServiceImpl;
import com.asapp.backend.challenge.service.MessageService;
import com.asapp.backend.challenge.service.MessageServiceImpl;
import com.asapp.backend.challenge.service.TokenValidatorService;
import com.asapp.backend.challenge.service.TokenValidatorServiceImpl;
import com.asapp.backend.challenge.service.UserService;
import com.asapp.backend.challenge.service.UserServiceImpl;
import com.asapp.backend.challenge.utils.Path;
import spark.Spark;

public class Application {

    public static void main(String[] args) {

        //Repositories
        UserRepository userRepository = new UserSqlLiteRepository();
        MessageRepository messageRepository = new MessageSqlLiteRepository();

        //Services
        TokenValidatorService tokenValidatorService = new TokenValidatorServiceImpl("secret_jwt_key");
        UserService userService = new UserServiceImpl(userRepository, tokenValidatorService);
        AuthenticationService authenticationService = new AuthenticationServiceImpl(userService, tokenValidatorService);
        MessageService messageService = new MessageServiceImpl(messageRepository);

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

    }

}
