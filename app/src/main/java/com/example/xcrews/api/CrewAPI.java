package com.example.xcrews.api;

import com.example.xcrews.model.Crew;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CrewAPI {

    @GET("v4/crew")
    Call<List<Crew>> getCrewList();
}
