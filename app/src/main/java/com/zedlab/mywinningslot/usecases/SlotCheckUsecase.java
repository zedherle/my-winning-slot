package com.zedlab.mywinningslot.usecases;

import com.zedlab.mywinningslot.data.service.ServiceCallback;
import com.zedlab.mywinningslot.data.service.SlotService;
import com.zedlab.mywinningslot.model.Centers;
import com.zedlab.mywinningslot.model.RegionSlot;
import java.util.*;
import java.util.stream.Collectors;

public class SlotCheckUsecase {

    SlotService slotService;

    public void slotCheck(String pincode, String date, String age, ServiceCallback<List<RegionSlot>> callback) {

        List<RegionSlot> regionSlots = new ArrayList<>();
        slotService = new SlotService();
        slotService.slotcheck(pincode, date, new SlotService.SlotCheckCallback() {
            @Override
            public void onSuccess(Centers centers) {

                centers.getCenters().forEach(eachCenter -> {
                    eachCenter.getSessions().forEach(eachSession -> {
                        RegionSlot regionSlot = new RegionSlot();
                        regionSlot.setCenterName(eachCenter.getName());
                        regionSlot.setLongitude(eachCenter.getLong());
                        regionSlot.setLatitude(eachCenter.getLat());
                        regionSlot.setDate(eachSession.getDate());
                        regionSlot.setAgeGroup("" + eachSession.getMin_age_limit());
                        regionSlot.setVaccineName(eachSession.getVaccine());
                        regionSlot.setSlotId(eachSession.getSession_id());
                        regionSlot.setSlots("" + eachSession.getSlots().size());
                        regionSlots.add(regionSlot);
                    });
                });

//                List<RegionSlot> regionSlots = centers.getCenters().stream().map(eachCenter -> {
//                    return eachCenter.getSessions().stream().map(eachSession -> {
//                        RegionSlot regionSlot = new RegionSlot();
//                        regionSlot.setCenterName(eachCenter.getName());
//                        regionSlot.setLongitude(eachCenter.getLong());
//                        regionSlot.setLatitude(eachCenter.getLat());
//                        regionSlot.setDate(eachSession.getDate());
//                        regionSlot.setVaccineName(eachSession.getVaccine());
//                        regionSlot.setSlots(""+eachCenter.getSessions().size());
//                        return regionSlot;
//                    }).collect(Collectors.toList());
//                }).flatMap(Collection::stream).collect(Collectors.toList());

                List<RegionSlot> collect = regionSlots.stream().filter(regionSlot -> Integer.parseInt(regionSlot.getAgeGroup()) == Integer.parseInt(age)).collect(Collectors.toList());
                callback.onSuccess(collect);
            }

            @Override
            public void onFailure() {
                callback.onFailure(null);
            }
        });
    }
}
