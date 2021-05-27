package com.zedlab.mywinningslot.data.worker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.app.NotificationCompat;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.google.common.util.concurrent.ListenableFuture;
import com.zedlab.mywinningslot.R;
import com.zedlab.mywinningslot.data.service.ServiceCallback;
import com.zedlab.mywinningslot.model.RegionSlot;
import com.zedlab.mywinningslot.usecases.SlotCheckUsecase;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SlotCheckerWorker extends ListenableWorker {
    /**
     * @param appContext   The application {@link Context}
     * @param workerParams Parameters to setup the internal state of this worker
     */
    SlotCheckUsecase slotCheckUsecase;
    public SlotCheckerWorker(@NonNull Context appContext, @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
        slotCheckUsecase = new SlotCheckUsecase();
    }
    @NonNull
    @Override
    public ListenableFuture<Result> startWork() {

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
        String pin = getInputData().getString("pincode");
        String ageGroup = getInputData().getString("agegroup");

        return CallbackToFutureAdapter.getFuture(completer -> {
            ServiceCallback<List<RegionSlot>> serviceCallback = new ServiceCallback<List<RegionSlot>>() {
                @Override
                public void onSuccess(List<RegionSlot> response) {
                    if(response.stream().filter(regionSlot -> regionSlot.getAgeGroup()==ageGroup).count()>0)
                    {addNotification("Your Winning Slot", "Vaccine Available for " +pin, getApplicationContext());}
                    completer.set(Result.success());
                }
                @Override
                public void onFailure(List<RegionSlot> response) {
                    completer.set(Result.failure());
                }
            };
            slotCheckUsecase.slotCheck(pin, formatter.format(today), ageGroup, serviceCallback);
            return serviceCallback;
        });
    }

    private void addNotification(String title, String message, Context context) {
        NotificationManager mNotificationManager;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "notify_001");
        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText(message);
        bigText.setBigContentTitle(title);
        bigText.setSummaryText(message);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        mBuilder.setStyle(bigText);

        mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "Your_channel_id";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }
        mNotificationManager.notify(0, mBuilder.build());
    }
}
