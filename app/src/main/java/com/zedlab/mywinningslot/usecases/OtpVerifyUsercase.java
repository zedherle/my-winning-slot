package com.zedlab.mywinningslot.usecases;

import com.zedlab.mywinningslot.data.service.OtpService;

public class OtpVerifyUsercase {

    OtpService otpService;

    public interface OtpVerifyUsecaseCallback {
        void onSucces(String txnId);
        void onFailure();
    }

    public void verifyOtp(String otp, String txnID, OtpVerifyUsercase.OtpVerifyUsecaseCallback callback) {
        otpService = new OtpService();
        otpService.verifyOtp(otp, txnID, new OtpService.OtpServiceCallback() {
            @Override
            public void onSuccess(String txnId) {
                callback.onSucces(txnID);
            }
            @Override
            public void onFailure() {
                callback.onFailure();
            }
        });
    }
}
