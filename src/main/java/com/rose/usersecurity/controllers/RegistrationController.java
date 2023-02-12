package com.rose.usersecurity.controllers;

import com.rose.usersecurity.entity.User;
import com.rose.usersecurity.event.RegistrationCompleteEvent;
import com.rose.usersecurity.model.UserModel;
import com.rose.usersecurity.service.RegistrationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
   @Autowired
    private RegistrationService registrationService;
   private ApplicationEventPublisher applicationEventPublisher;
    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){
      User user=  registrationService.registerUser(userModel);
      applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(user,applicationUrl(request)));
      return "Success";

    }
    public String applicationUrl(HttpServletRequest request){
        return  "http://" +
                ":/" +
                request.getServerName() +
                request.getServerPort()+
                request.getContextPath();
    }
}
