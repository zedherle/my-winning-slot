package com.zedlab.mywinningslot.usecases;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import androidx.work.*;
import com.zedlab.mywinningslot.data.worker.SlotCheckerWorker;
import java.util.concurrent.TimeUnit;

public class SlotCheckNotifierUsecase {
    private static final String SLOT_NOTIFY = "SLOCTNOTIFIER";
    private WorkManager mWorkManager;
    public void stockCheckNotifier(String pin, String agegroup, Context context) {

        mWorkManager = WorkManager.getInstance(context);
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresCharging(true)
                .build();
        Data inputFilter = new Data.Builder()
                .putString("pincode", pin)
                .putString("agegroup", agegroup)
                .build();
        PeriodicWorkRequest stockCheckRequest = new PeriodicWorkRequest.Builder
                (SlotCheckerWorker.class, 15, TimeUnit.MINUTES)
                .setInputData(inputFilter)
                .addTag(SLOT_NOTIFY)
                .build();
        mWorkManager.enqueue(stockCheckRequest);
        mWorkManager.getWorkInfoByIdLiveData(stockCheckRequest.getId()).observe((LifecycleOwner) context, workInfo -> {
            switch (workInfo.getState()) {
                case ENQUEUED: // ENQUEUED
                case RUNNING: // Running
                    break;
                case SUCCEEDED:
                    // this will not be called since it is periodic work
                    break;
                case FAILED:
                    break;
                case BLOCKED:
                    break;
                case CANCELLED:
                    break;
            }
        });
    }

    public void stockCheckNotifierDisable() {
        mWorkManager.cancelAllWorkByTag(SLOT_NOTIFY);
    }

}
