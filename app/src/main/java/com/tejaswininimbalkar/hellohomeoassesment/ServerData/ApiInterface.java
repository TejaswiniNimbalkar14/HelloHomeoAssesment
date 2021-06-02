package com.tejaswininimbalkar.hellohomeoassesment.ServerData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("crew")
    Call<List<CrewMembers>> getCrewMembers();

}
