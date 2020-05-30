package com.aws.codestar.projecttemplates;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/** Simple class to start up the application.
 *
 * @SpringBootApplication adds:
 *  @Configuration
 *  @EnableAutoConfiguration
 *  @ComponentScan
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    static Logger log = Logger.getLogger(Application.class.getName());
    public static void main(String[] args) {
        log.debug("Hello this is a debug message");
        log.info("Hello this is an info message");
        SpringApplication.run(Application.class, args);
        log.debug("Hello this is a debug message");
        log.info("Hello this is an info message");
    }

}
