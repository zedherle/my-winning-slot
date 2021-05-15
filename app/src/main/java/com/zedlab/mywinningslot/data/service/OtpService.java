package com.zedlab.mywinningslot.data.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zedlab.mywinningslot.data.api.ApiClient;
import com.zedlab.mywinningslot.data.api.ApiService;
import com.zedlab.mywinningslot.model.OtpResponse;
import com.zedlab.mywinningslot.model.OtpVerificationResponse;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Retrofit;

public class OtpService {

    private ApiService apiService = null;
    private static final String MOBILE = "mobile";
    private static final String OTP = "otp";
    private static final String TRANS = "txnId";

    public interface OtpServiceCallback {
        void onSuccess(String txnId);
        void onFailure();
    }

    public void getOtp(String mobile, OtpServiceCallback callback) {
        Retrofit retrofit = ApiClient.getInstance();
        if (apiService == null)
            apiService = retrofit.create(ApiService.class);
        JsonObject json = buildJsonForApi(mobile);
        apiService.fetchOtp(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<OtpResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Do nothing
                    }

                    @Override
                    public void onNext(OtpResponse value) {
                        callback.onSuccess(value.getTxnId());
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure();
                    }

                    @Override
                    public void onComplete() {
                        // Do nothing
                    }
                });
    }


    public void verifyOtp(String otp, String txnId, OtpServiceCallback callback) {
        Retrofit retrofit = ApiClient.getInstance();
        if (apiService == null)
            apiService = retrofit.create(ApiService.class);
        JsonObject json = buildJsonForApi(otp, txnId);
        apiService.verifyOtp(json).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<OtpVerificationResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                // Do nothing
            }

            @Override
            public void onNext(OtpVerificationResponse otpVerificationResponse) {
                callback.onSuccess(otpVerificationResponse.getToken());
            }

            @Override
            public void onError(Throwable e) {
                // Do nothing
            }

            @Override
            public void onComplete() {
                // Do nothing
            }
        });

    }

    private JsonObject buildJsonForApi(String mobile) {

        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put(MOBILE, mobile);
            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObj.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return gsonObject;
    }

    private JsonObject buildJsonForApi(String otp, String txnId) {

        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put(OTP, otp);
            jsonObj.put(TRANS, txnId);

            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObj.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return gsonObject;
    }
}
