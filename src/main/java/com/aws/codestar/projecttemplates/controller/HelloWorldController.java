package com.aws.codestar.projecttemplates.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Basic Spring web service controller that handles all GET requests.
 */
@RestController
@RequestMapping("/")
public class HelloWorldController {

    private static final String MESSAGE_FORMAT = "Hello %s!";
    private static Logger logger = Logger.getLogger(HelloWorldController.class);

    @RequestMapping(path = "/test1", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity helloWorldGet(@RequestParam(value = "name", defaultValue = "World1") String name) {
        logServerInfo();
        return ResponseEntity.ok(createResponse(name));
    }

    @RequestMapping(path = "/test2", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity helloWorldPost(@RequestParam(value = "name", defaultValue = "World2") String name) {
        return ResponseEntity.ok(createResponse(name));
    }


    private String createResponse(String name) {
        return new JSONObject().put("Output", String.format(MESSAGE_FORMAT, name)).toString();
    }

    private void logServerInfo() {
        logger.info("test");
        System.out.println("starting db connection");
        try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbName = System.getenv("RDS_DB_NAME");
        String userName = System.getenv("RDS_USERNAME");
        String password = System.getenv("RDS_PASSWORD");
        String hostname = System.getenv("RDS_HOSTNAME");
        String port = System.getenv("RDS_PORT");
        String jdbcUrl = "jdbc:sqlserver://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
        logger.trace("Getting remote connection with connection string from environment variables.");
        System.out.println("dbName > "+dbName);
        System.out.println("userName > "+userName);
        System.out.println("password > "+password);
        System.out.println("hostname > "+hostname);
        System.out.println("port > "+port);
        System.out.println("jdbcUrl > "+jdbcUrl);
        System.out.println("connecting");
        Connection con = DriverManager.getConnection(jdbcUrl);
        System.out.println("connected");
        logger.info("Remote connection successful.");
        }
        catch (ClassNotFoundException e) { 
            System.out.println("class error");
            logger.warn(e.toString());
        }
        catch (SQLException e) { 
            System.out.println("sql error");
            logger.warn(e.toString());
        }
    }
    
}