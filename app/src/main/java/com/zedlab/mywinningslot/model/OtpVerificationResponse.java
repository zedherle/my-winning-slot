package com.zedlab.mywinningslot.model;

public class OtpVerificationResponse {

    String token;
    String isNewAccount;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIsNewAccount() {
        return isNewAccount;
    }

    public void setIsNewAccount(String isNewAccount) {
        this.isNewAccount = isNewAccount;
    }
}
