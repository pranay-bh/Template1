package com.solvedunsloved.template1;

import com.solvedunsloved.template1.data.color;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface jsonAPI {
    @GET("photos")
    Call<List<color>> getcolor();
}
