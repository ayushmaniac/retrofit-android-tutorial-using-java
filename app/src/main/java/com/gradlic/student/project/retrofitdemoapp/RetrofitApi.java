package com.gradlic.student.project.retrofitdemoapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitApi {
    @GET("users")
    public Call<List<User>> getAllUsers();

    @GET("users/{id}")
    public Call<User> getUser(@Path("id") int id);

    @POST("users")
    public Call<Void> addUser(@Body User user);

    @PUT("users/{id}")
    public Call<Void> updateUser(@Body User user, @Path("id") int id);

    @DELETE("users/{id}")
    public Call<Void> deleteUser(@Path("id") int id);
}
