package com.se122l11pmcl.weebchat.Fragments;

import com.se122l11pmcl.weebchat.Notifications.MyResponse;
import com.se122l11pmcl.weebchat.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAADrHOm84:APA91bFJroOMiIr8UJ1sjYqKdBXTTjxv_obK6HbjLWlx6c6a9BafQh3e-LCeWOgABap0o-jPJhHOwpNGbUr1LxGbhaDbKk59706M43Q6Rp6xQxiOUVFc2iRVyxu4twK-LwaQLk-cfl94"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
