package com.jiho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by jiho87.shin on 2017-01-09.
 */
@SpringBootApplication

public class Application {


    public void main(String[] args){

        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.run(args);



    }
}
