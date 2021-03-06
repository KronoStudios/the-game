package com.kronostudios.the_game.loginUtils.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kronostudios.the_game.core.APIController;
import com.kronostudios.the_game.loginUtils.Account;
import com.kronostudios.the_game.loginUtils.helpers.ApiCallback;
import com.kronostudios.the_game.loginUtils.service.AccountService;
import com.kronostudios.the_game.loginUtils.service.AccountServiceImpl;
import com.kronostudios.the_game.models.Result;

import retrofit2.Call;
import retrofit2.Response;

public class AccountRepo {

    private String TAG = "AccountRepo";

    private AccountService accountService;
    private Result<String> loginResult;
    private MutableLiveData<Result<String>> loginResultLiveData;

    public AccountRepo(){
        this.accountService = new AccountServiceImpl();
        loginResultLiveData = new MutableLiveData<>();

    }

    // Sends a login query to the backend
    public void login(String authorizationToken){
        this.accountService.createTokenUser(authorizationToken).enqueue(new ApiCallback<Account>() {

            @Override
            public void onFailure(Call call, Throwable t) {
                loginResult = Result.error(t);
                loginResultLiveData.postValue(loginResult);
                Log.d(TAG, "login() -> onResponseError -> " + t.getMessage());
            }

            @Override
            public void onResponseSuccess(Call<Account> call, Response<Account> response) {

                Log.d(TAG, "login() -> onResponseSuccess sense tostring -> " + response.body());
                Log.d(TAG, "login() -> onResponseSuccess -> " + response.body().toString());
                String token = response.body().getToken();

                String user_id = response.body().getUser_id();
                Log.d(TAG, "login() -> onResponseSuccess (user_id) -> " + user_id);

                Log.d(TAG, "login() -> onResponseSuccess -> " + token);
                loginResult = Result.success(response.body().getToken());
                Log.d(TAG, "login() -> onResponseSuccess / getResult-> " + loginResult.getResult());
                loginResultLiveData.postValue(loginResult);
                Log.d(TAG, "login() -> onResponseSuccess END");
            }

            @Override
            public void onResponseError(Call<Account> call, Throwable t) {
                loginResult = Result.error(t);
                loginResultLiveData.postValue(loginResult);
                Log.d(TAG, "login() -> onResponseError -> " + t.getMessage());
            }
        });
    }
    // Gets the answer to login query
    public LiveData<Result<String>> getLoginResult(){
        return this.loginResultLiveData;
    }
}
