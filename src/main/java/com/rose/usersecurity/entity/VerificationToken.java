package com.rose.usersecurity.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@Entity
@NoArgsConstructor
public class VerificationToken {
    //verification link to expire in 15 minutes
    private static  final int EXPIRATION_TIME=15;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id",
            nullable = false,
            foreignKey = @ForeignKey(name="USER_VERIFICATION_TOKEN"))
    private User user;

    public VerificationToken(String token,User user) {
        super();

        this.token = token;
        this.expirationTime = calculateExpirationTime(EXPIRATION_TIME);
        this.user = user;
    }
    public VerificationToken(String token){
        super();
        this.token=token;
        this.expirationTime=calculateExpirationTime(EXPIRATION_TIME);
    }
    private Date calculateExpirationTime(int expirationTime){
        Calendar calendar=Calendar.getInstance();
//        calendar.getTimeInMillis(new Date().getTime());
        calendar.getTimeInMillis();
        calendar.add(Calendar.MINUTE,expirationTime);
        return new Date(calendar.getTime().getTime());
    }
}
