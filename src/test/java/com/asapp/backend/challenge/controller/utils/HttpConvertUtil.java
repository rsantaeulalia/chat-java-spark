package com.asapp.backend.challenge.controller.utils;

import org.apache.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class HttpConvertUtil {

    public static String convertHttpResponseToString(HttpResponse httpResponse) throws IOException {
        InputStream inputStream = httpResponse.getEntity().getContent();
        return convertInputStreamToString(inputStream);
    }

    private static String convertInputStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        String string = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return string;
    }

}
