package com.kronostudios.the_game.loginUtils;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kronostudios.the_game.models.Result;
import com.kronostudios.the_game.loginUtils.repositories.AccountRepo;

public class LoginViewModel extends ViewModel {

    private String TAG = "LoginViewModel";

    public MutableLiveData<String> emailLiveData;
    public MutableLiveData<String> errorEmailLiveData;
    public MutableLiveData<String> passLiveData;
    public MutableLiveData<String> passErrorLiveData;
    public MutableLiveData<Boolean> isLogged;


    private AccountRepo accountRepo;


    public LoginViewModel(){
        this.emailLiveData = new MutableLiveData<>();
        this.errorEmailLiveData = new MutableLiveData<>();
        this.passLiveData = new MutableLiveData<>();
        this.passErrorLiveData = new MutableLiveData<>();
        this.isLogged = new MutableLiveData<>();

        this.accountRepo = new AccountRepo();
    }

    public MutableLiveData<String> getEmailLiveData() {
        return emailLiveData;
    }

    public void setEmailLiveData(MutableLiveData<String> emailLiveData) {
        this.emailLiveData = emailLiveData;
    }

    public MutableLiveData<String> getErrorEmailLiveData() {
        return errorEmailLiveData;
    }

    public void setErrorEmailLiveData(MutableLiveData<String> errorEmailLiveData) {
        this.errorEmailLiveData = errorEmailLiveData;
    }

    public MutableLiveData<String> getPassLiveData() {
        return passLiveData;
    }

    public void setPassLiveData(MutableLiveData<String> passLiveData) {
        this.passLiveData = passLiveData;
    }

    public MutableLiveData<String> getPassErrorLiveData() {
        return passErrorLiveData;
    }

    public void setPassErrorLiveData(MutableLiveData<String> passErrorLiveData) {
        this.passErrorLiveData = passErrorLiveData;
    }

    public MutableLiveData<Boolean> getIsLogged() {
        return isLogged;
    }

    public void setIsLogged(MutableLiveData<Boolean> isLogged) {
        this.isLogged = isLogged;
    }


    public void login(){
        // Get the data from fields
        String email = emailLiveData.getValue();
        String password = passLiveData.getValue();

        Log.d(TAG, "email -> " + email);
        Log.d(TAG, "password -> " + password);


        // Form validator
        if (isFormValid(email,password)) {
            // Shows the progress bar, telling the user that we are making the communication with the API
            isLogged.postValue(true);

            // Call the repo passing the authorization token obtained from email and password
            this.accountRepo.login(AccountUtils.getAuthorizationToken(email, password));
        }
        // Shows the progress bar, telling the user that we are making the communication with the API
        isLogged.postValue(true);

        // Call the repo passing the authorization token obtained from email and password
        this.accountRepo.login(AccountUtils.getAuthorizationToken(email, password));

    }


    public LiveData<Result<String>> isUserLogged(){
        if(this.accountRepo.getLoginResult() != null){
            isLogged.postValue(false);
        }
        return this.accountRepo.getLoginResult();
    }

    private Boolean isFormValid(String email, String password){
        boolean isValid = true;

        String validEmail= AccountUtils.isEmailValid(email);
        if ( validEmail != null){
            isValid = false;
            errorEmailLiveData.postValue(validEmail);
        };
/*
        String validPassword= AccountUtils.isPasswordValid(password);
        if ( validPassword != null){
            isValid = false;
            passErrorLiveData.postValue(validPassword);
        };
*/
        return isValid;
    }


}
