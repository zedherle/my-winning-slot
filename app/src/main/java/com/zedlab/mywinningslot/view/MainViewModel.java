package com.zedlab.mywinningslot.view;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.zedlab.mywinningslot.data.service.ServiceCallback;
import com.zedlab.mywinningslot.model.RegionSlot;
import com.zedlab.mywinningslot.usecases.*;

import java.util.List;

public class MainViewModel extends ViewModel {

    private SlotCheckNotifierUsecase slotCheckNotifierUsecase;
    private MutableLiveData<String> txnID = new MutableLiveData<>();
    private MutableLiveData<List<RegionSlot>> rListMutableLiveData;

    public MainViewModel() {
        rListMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<RegionSlot>> getRegionSlotMutableLiveData() {
        return rListMutableLiveData;
    }

    public LiveData<String> getOtp(String mobile) {
        OtpRequestUsecase otpRequestUsecase = new OtpRequestUsecase();
        otpRequestUsecase.fetchOtp(mobile, new OtpRequestUsecase.OtpRequestUsecaseCallback() {
            @Override
            public void onSucces(String txnId) {
                txnID.setValue(txnId);
            }

            @Override
            public void onFailure() {
                //  failure - do nothing
            }
        });
        return txnID;
    }

    public LiveData<String> verifyOtp(String otp, String txnId) {
        OtpVerifyUsercase otpVerifyUsercase = new OtpVerifyUsercase();
        otpVerifyUsercase.verifyOtp(otp, txnId, new OtpVerifyUsercase.OtpVerifyUsecaseCallback() {
            @Override
            public void onSucces(String txnId) {
                txnID.setValue(txnId);
            }

            @Override
            public void onFailure() {
                //  failure - do nothing
            }
        });
        return txnID;
    }

    public LiveData<String> slotCheck(String pincode, String date, String agegroup) {

        SlotCheckUsecase slotCheckUsecase = new SlotCheckUsecase();
        slotCheckUsecase.slotCheck(pincode, date, agegroup, new ServiceCallback<List<RegionSlot>>() {
            @Override
            public void onSuccess(List<RegionSlot> response) {
                rListMutableLiveData.setValue(response);
            }

            @Override
            public void onFailure(List<RegionSlot> response) {
                //failure - do nothing
            }
        });
        return txnID;
    }

    public LiveData<String> slotBook(String sessionId, String benId) {

        SlotBookUsecase slotBookUsecase = new SlotBookUsecase();
        slotBookUsecase.slotBook(sessionId, benId, new SlotBookUsecase.SlotBookUsecaseCallback() {
            @Override
            public void onSuccess(String txnId) {
                txnID.setValue(txnId);
            }

            @Override
            public void onFailure() {
                //failure - do nothing
            }
        });
        return txnID;
    }

    public void notifyEnable(String pincode, String ageGroup, Context appContext) {
        slotCheckNotifierUsecase = new SlotCheckNotifierUsecase();
        slotCheckNotifierUsecase.stockCheckNotifier(pincode, ageGroup, appContext);
    }

    public void notifyDisable() {
        slotCheckNotifierUsecase.stockCheckNotifierDisable();
    }

}
