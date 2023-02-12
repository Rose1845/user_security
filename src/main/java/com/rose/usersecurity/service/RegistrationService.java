package com.rose.usersecurity.service;

import com.rose.usersecurity.entity.User;
import com.rose.usersecurity.entity.VerificationToken;
import com.rose.usersecurity.model.UserModel;
import com.rose.usersecurity.repository.RegistrationRepository;
import com.rose.usersecurity.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;

    private VerificationTokenRepository verificationTokenRepository;

   private PasswordEncoder passwordEncoder;
    public User registerUser(UserModel userModel) {
        User user=new User();
        user.setEmail(userModel.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setPassword(passwordEncoder.encode(
                userModel.getPassword()
        ));
        user.setRole("USER");
        user.setLastName(userModel.getLastName());
        registrationRepository.save(user);
        return user;

    }

    public void saveTokenForVerifyingUser( User user,String token) {
        VerificationToken verificationToken=new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);
    }
}
