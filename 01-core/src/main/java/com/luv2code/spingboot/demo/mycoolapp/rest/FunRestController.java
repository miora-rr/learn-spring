package com.luv2code.spingboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    @Value("${coach.name}")
    private  String coachName;

    @Value("${team.name}")
    private  String teamName;
    @GetMapping("/")
    public String sayHelloWorld() {
        return ("I am the coolest person");
    }

    @GetMapping("/workout")
    public String getDailyWorkout() {
        return ("Go for a run !" + teamName);
    }

    @GetMapping("/fortune")
    public String getDailyFortune() {
        return ("You will be successfully" + coachName);
    }
}
