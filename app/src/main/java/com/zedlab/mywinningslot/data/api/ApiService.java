package com.zedlab.mywinningslot.data.api;

import com.google.gson.JsonObject;
import com.zedlab.mywinningslot.model.AppointmentResponse;
import com.zedlab.mywinningslot.model.Centers;
import com.zedlab.mywinningslot.model.OtpResponse;
import com.zedlab.mywinningslot.model.OtpVerificationResponse;
import io.reactivex.Observable;
import retrofit2.http.*;

public interface ApiService {

    String AUTH = "Authorization";
    String OTP_API_URL="v2/auth/public/generateOTP";
    String VERIFY_TP_API_URL="v2/auth/public/confirmOTP";
    String SLOT_SERVICE = "v2/appointment/sessions/public/calendarByPin";
    String SLOT_BOOK_SERVICE = "v2/appointment/schedule";

    @POST(OTP_API_URL)
    Observable<OtpResponse> fetchOtp(@Body JsonObject mobile);

    @POST(VERIFY_TP_API_URL)
    Observable<OtpVerificationResponse> verifyOtp(@Body JsonObject request) ;

    @Headers({"User-Agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36", "Content-Type: application/json"})
    @GET(SLOT_SERVICE)
    Observable<Centers> slotCheck(@Query("pincode") String pincode, @Query("date") String date);

    @POST(SLOT_BOOK_SERVICE)
    Observable<AppointmentResponse> slotBook(@Body JsonObject request) ;
}