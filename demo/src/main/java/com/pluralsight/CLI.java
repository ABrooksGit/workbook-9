package com.pluralsight;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CLI implements CommandLineRunner {

    @Override
    public  void run(String...args /*  Creates list for you  */){
        System.out.println("Hello world from CLI");

    }




}
