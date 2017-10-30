package com.newt.lms;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.newt.lms")
public class LmsApplication {
	
	  /**
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(LmsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LmsApplication.class, args);
	}

    public LmsApplication() {
	startup();
    }

    public void startup() {
	LOGGER.info("Starting LmsApplication ...");
    }

}
