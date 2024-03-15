package com.grp3.bid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswdToken implements Serializable {
    private Integer id;
    private User user;
    private String token;
    private LocalDateTime expiration;
    private boolean isAlreadyUsed;
}
