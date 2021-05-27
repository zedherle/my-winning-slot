package com.zedlab.mywinningslot.data.service;

import com.zedlab.mywinningslot.data.api.ApiClient;
import com.zedlab.mywinningslot.data.api.ApiService;
import com.zedlab.mywinningslot.model.Centers;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class SlotService {

    private ApiService apiService = null;

    public interface SlotCheckCallback {
        void onSuccess(Centers centers);
        void onFailure();
    }

    public void slotcheck(String pincode, String date, SlotCheckCallback callback) {
        Retrofit retrofit = ApiClient.getInstance();
        if (apiService == null)
            apiService = retrofit.create(ApiService.class);

        apiService.slotCheck(pincode, date)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Centers>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //Do nothing
                    }

                    @Override
                    public void onNext(Centers centers) {
                        callback.onSuccess(centers);
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


}
