package com.kronostudios.the_game.loginUtils.service;


import com.kronostudios.the_game.loginUtils.Account;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AccountService {
    @POST("account/create_token")
    Call<Account> createTokenUser(@Header("Authorization") String authorizationToken);
}
