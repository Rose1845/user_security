package com.rose.usersecurity.event.listener;

import com.rose.usersecurity.entity.User;
import com.rose.usersecurity.event.RegistrationCompleteEvent;
import com.rose.usersecurity.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

@Slf4j
public class RegistrationCompeleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private RegistrationService registrationService;
    @Override
    public  void onApplicationEvent(RegistrationCompleteEvent event) {

        //create token for user verification link
        User user=event.getUser();
        String token= UUID.randomUUID().toString();
        registrationService.saveTokenForVerifyingUser(user, token);
//send email

        String url=event.getApplicationUrl()
                + "verifyRegistration?token="
                + token;

        log.info("Click the link to verify your account:{}",url);


    }



}
