package com.zedlab.mywinningslot.data.service;

import android.util.Log;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zedlab.mywinningslot.data.api.ApiClient;
import com.zedlab.mywinningslot.data.api.ApiService;
import com.zedlab.mywinningslot.model.AppointmentResponse;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Retrofit;

import java.util.Arrays;
import java.util.List;

public class SlotBookService {

    private ApiService apiService = null;

    public interface SlotBookServiceCallback {
        void onSuccess();
        void onFailure();
    }

    public void slotBook(String sessionId, String benId, SlotBookService.SlotBookServiceCallback callback)
    {
        Retrofit retrofit = ApiClient.getInstance();
        if (apiService == null)
            apiService = retrofit.create(ApiService.class);
        JsonObject json = buildJsonForApi(1, sessionId, "FORENOON", Arrays.asList(benId));
        apiService.slotBook(json)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<AppointmentResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //Do nothing
                    }

                    @Override
                    public void onNext(AppointmentResponse appointmentResponse) {
                        callback.onSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure();
                    }
                    @Override
                    public void onComplete() {
                        //Do nothing
                    }
                });
    }

    private JsonObject buildJsonForApi(int dose, String sessionId, String slot, List<String> bene) {

        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("dose", dose);
            jsonObj.put("session_id", sessionId);
            jsonObj.put("slot", slot);
            jsonObj.put("beneficiaries", bene);

            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject)jsonParser.parse(jsonObj.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return gsonObject;
    }
}
