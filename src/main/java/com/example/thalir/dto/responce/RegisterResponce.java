package com.example.thalir.dto.responce;

import lombok.Data;

@Data
public class RegisterResponce {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;





    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   private String email;
}
