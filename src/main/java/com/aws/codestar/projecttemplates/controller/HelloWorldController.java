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
        System.out.println("logServerInfo starting db connection");
        try {
            System.out.println("logServerInfo try 2");
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            Class test = Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println(test);
            String dbName = System.getenv("RDS_DB_NAME");
            String userName = System.getenv("RDS_USERNAME");
            String password = System.getenv("RDS_PASSWORD");
            String hostname = System.getenv("RDS_HOSTNAME");
            String port = System.getenv("RDS_PORT");
            dbName = "trialrun1";
            userName = "admin";
            password = "Clau.266";
            hostname = "trialrun1.cqslbggdmkng.ap-southeast-1.rds.amazonaws.com";
            port = "1433";
            String jdbcUrl = "jdbc:sqlserver://" + hostname + ":" + port + ";databaseName=" + dbName + ";user=" + userName + ";password=" + password;
            //String jdbcUrl = "jdbc:sqlserver://" + hostname + "\\" + dbName + "?user=" + userName + "&password=" + password;
            logger.trace("Getting remote connection with connection string from environment variables.");
            System.out.println("logServerInfo dbName > "+dbName);
            System.out.println("logServerInfo userName > "+userName);
            System.out.println("logServerInfo password > "+password);
            System.out.println("logServerInfo hostname > "+hostname);
            System.out.println("logServerInfo port > "+port);
            System.out.println("logServerInfo jdbcUrl > "+jdbcUrl);
            System.out.println("logServerInfo connecting");
            Connection con = DriverManager.getConnection(jdbcUrl);
            System.out.println("logServerInfo connected");
            logger.info("Remote connection successful.");
        }
        catch (ClassNotFoundException e) { 
           System.out.println("logServerInfo class error > " + e.toString());
           logger.warn(e.toString());
        }
        catch (SQLException e) { 
            System.out.println("logServerInfo sql error > " + e.toString());
            logger.warn(e.toString());
        }
    }
    
}