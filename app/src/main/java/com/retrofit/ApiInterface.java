package com.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("register.php")
    Call<User> performRegistration(@Query("sponsor_id") String sponsor_id, @Query("name") String name, @Query("email_id") String email_id,
                                   @Query("phone") String phone, @Query("password") String password);

    @GET("login.php")
    Call<User> performUserLogin(@Query("email_id") String email_id,@Query("password") String password);
}
