package com.zedlab.mywinningslot.usecases;

import com.zedlab.mywinningslot.data.service.SlotBookService;

public class SlotBookUsecase {

    SlotBookService slotBookService;

    public interface SlotBookUsecaseCallback {
        void onSuccess(String txnId);
        void onFailure();
    }

    public void slotBook(String sessionId, String benId, SlotBookUsecase.SlotBookUsecaseCallback callback) {
        slotBookService = new SlotBookService();
        slotBookService.slotBook(sessionId, benId, new SlotBookService.SlotBookServiceCallback() {
            @Override
            public void onSuccess() {
                callback.onSuccess("done");
            }

            @Override
            public void onFailure() {
                callback.onFailure();
            }
        });
    }

}
