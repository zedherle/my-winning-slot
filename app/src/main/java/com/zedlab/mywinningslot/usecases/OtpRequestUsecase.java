package com.zedlab.mywinningslot.usecases;

import com.zedlab.mywinningslot.data.service.OtpService;

public class OtpRequestUsecase {

    OtpService otpService;

    public interface OtpRequestUsecaseCallback {
        void onSucces(String txnId);

        void onFailure();
    }

    public void fetchOtp(String mobile, OtpRequestUsecaseCallback callback) {
        otpService = new OtpService();
        otpService.getOtp(mobile, new OtpService.OtpServiceCallback() {
            @Override
            public void onSuccess(String txnId) {
                callback.onSucces(txnId);
            }

            @Override
            public void onFailure() {
                callback.onFailure();
            }
        });
    }
}
