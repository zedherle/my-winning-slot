package com.zedlab.mywinningslot.view;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.*;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.zedlab.mywinningslot.R;
import com.zedlab.mywinningslot.data.service.OtpService;
import com.zedlab.mywinningslot.model.RegionSlot;
import com.zedlab.mywinningslot.view.adapters.SlotViewAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText etPhone;
    private EditText etOtp;
    private EditText etPin;
    private EditText etAge;
    private Button btConfirm;
    private Button btVerify;
    private Button btNotify;
    private Button btSlots;
    private RadioButton radioButton;
    private RadioGroup radioAgeGroupSelection;
    private ToggleButton toggleNotifier;
    private RecyclerView recyclerView;
    private SlotViewAdapter slotViewAdapter;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slot_checker);

        etPhone = findViewById(R.id.edPhone);
        etOtp = findViewById(R.id.etOtp);
        btConfirm = findViewById(R.id.btConfirm);
        btVerify = findViewById(R.id.btVerify);
        btSlots = findViewById(R.id.btSlot);
        btNotify = findViewById(R.id.btNotify);
        etPin = findViewById(R.id.etPin);
        etAge = findViewById(R.id.etAge);
        toggleNotifier = findViewById(R.id.tgNotifier);
        recyclerView = findViewById(R.id.rv_main);
        radioAgeGroupSelection = findViewById(R.id.radio_group);

        MainViewModel mainViewModel = ViewModelProviders.of(this).
                                      get(MainViewModel.class);

        mainViewModel.getRegionSlotMutableLiveData().observe(this, regionSlots -> {
            slotViewAdapter = new SlotViewAdapter(MainActivity.this,regionSlots);
            slotViewAdapter.notifyDataSetChanged();
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            recyclerView.setAdapter(slotViewAdapter);
        });
//        btConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                mainViewModel.getOtp(etPhone.getText().toString()).observe(MainActivity.this,
//                        new Observer<String>() {
//                            @Override
//                            public void onChanged(String s) {
//
//                                txnIdReceived = s;
//                                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
//                            }
//                        });
//            }
//        });
//
//        btVerify.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                mainViewModel.verifyOtp(etOtp.getText().toString(), txnIdReceived).observe(MainActivity.this, new Observer<String>() {
//                    @Override
//                    public void onChanged(String s) {
//
//                        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
//                    }
//                });
//
//            }
//        });

        btSlots.setOnClickListener(v -> {
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
            int selectedId=radioAgeGroupSelection.getCheckedRadioButtonId();
            radioButton=findViewById(selectedId);
            mainViewModel.slotCheck(etPin.getText().toString(), formatter.format(today), radioButton.getText().toString())
                         .observe(MainActivity.this, response -> Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show());
        });

      toggleNotifier.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            int selectedId=radioAgeGroupSelection.getCheckedRadioButtonId();
            RadioButton radioButton1 = findViewById(selectedId);
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mainViewModel.notifyEnable(etPin.getText().toString(), radioButton1.getText().toString(), MainActivity.this);
                } else {
                    mainViewModel.notifyDisable();
                }
            }
        });
    }
}