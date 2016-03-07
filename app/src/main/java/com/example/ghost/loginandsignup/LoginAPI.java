package com.example.ghost.loginandsignup;

import javax.security.auth.callback.Callback;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Field;

/**
 * Created by ghost on 26/02/2016.
 */
public interface LoginAPI {
@GET("/users/{id}")
Call<User>getUser(@Path("id") String userId);


    @GET("/users/")
   Call<Users>getUsers();



    //@GET("/users(email)")
   // Call<User>getUser(@Path("email") String email );

    @PUT("/users(id)")
    Call<User>updateUser (@Path("id") int user_id, @Body User user);

    @POST("/users")
    Call<User>saveUser (@Body User user);

    @DELETE("/users(id)")
    Call<User>deleteUser (@Path("id") String user_id);

}
