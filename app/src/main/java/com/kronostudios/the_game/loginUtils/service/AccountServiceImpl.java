package com.kronostudios.the_game.loginUtils.service;


import com.kronostudios.the_game.loginUtils.Account;
import com.kronostudios.the_game.loginUtils.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Retrofit;

public class AccountServiceImpl implements AccountService {

    private Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

    @Override
    public Call<Account> createTokenUser(String authorizationToken) {
        return retrofit.create(AccountService.class).createTokenUser(authorizationToken);
    }

}
