package com.kronostudios.the_game.loginUtils;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kronostudios.the_game.activities.RegisterUserActivity;
import com.kronostudios.the_game.activities.MainMenu;
import com.kronostudios.the_game.core.APIController;
import com.kronostudios.the_game.loginUtils.repositories.AccountRepo;

public class RegisterViewModel extends ViewModel {


    private String TAG = "RegisterViewModel";

    public MutableLiveData<String> emailLiveData;
    public MutableLiveData<String> errorEmailLiveData;
    public MutableLiveData<String> userLiveData;
    public MutableLiveData<String> errorUserLiveData;
    public MutableLiveData<String> passLiveData;
    public MutableLiveData<String> passErrorLiveData;
    public MutableLiveData<String> pass2LiveData;
    public MutableLiveData<String> pass2ErrorLiveData;
    public MutableLiveData<Boolean> exists;


    private AccountRepo accountRepo;


    public RegisterViewModel(){
        this.emailLiveData = new MutableLiveData<>();
        this.errorEmailLiveData = new MutableLiveData<>();
        this.userLiveData = new MutableLiveData<>();
        this.errorUserLiveData = new MutableLiveData<>();
        this.passLiveData = new MutableLiveData<>();
        this.passErrorLiveData = new MutableLiveData<>();
        this.pass2LiveData = new MutableLiveData<>();
        this.pass2ErrorLiveData = new MutableLiveData<>();
        this.exists = new MutableLiveData<>();

        this.accountRepo = new AccountRepo();
    }

    public MutableLiveData<String> getPass2LiveData() {
        return pass2LiveData;
    }

    public void setPass2LiveData(MutableLiveData<String> pass2LiveData) {
        this.pass2LiveData = pass2LiveData;
    }

    public MutableLiveData<String> getPass2ErrorLiveData() {
        return pass2ErrorLiveData;
    }

    public void setPass2ErrorLiveData(MutableLiveData<String> pass2ErrorLiveData) {
        this.pass2ErrorLiveData = pass2ErrorLiveData;
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

    public MutableLiveData<String> getUserLiveData() {
        return userLiveData;
    }

    public void setUserLiveData(MutableLiveData<String> userLiveData) {
        this.userLiveData = userLiveData;
    }

    public MutableLiveData<String> getErrorUserLiveData() {
        return errorUserLiveData;
    }

    public void setErrorUserLiveData(MutableLiveData<String> errorUserLiveData) {
        this.errorUserLiveData = errorUserLiveData;
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

    public MutableLiveData<Boolean> getExists() {
        return exists;
    }

    public void setExists(MutableLiveData<Boolean> exists) {
        this.exists = exists;
    }

    public AccountRepo getAccountRepo() {
        return accountRepo;
    }

    public void setAccountRepo(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }


    public void create(){
        // Get the data from fields
        String email = emailLiveData.getValue();
        String password = passLiveData.getValue();
        String username = userLiveData.getValue();
        String pass2 = passLiveData.getValue();

        Log.d(TAG, "email -> " + email);
        Log.d(TAG, "password -> " + password);

        Boolean valid = isFormValid(email,password, pass2, username);
        // Form validator
        if (valid) {
            // Shows the progress bar, telling the user that we are making the communication with the API
            exists.postValue(true);

            // Call the repo passing the authorization token obtained from email and password
            APIController.User_Post(RegisterUserActivity.context,username, email, password);
           //logeamos
            this.accountRepo.login(AccountUtils.getAuthorizationToken(email, password));
            RegisterUserActivity.userActivity.goTo(MainMenu.class);
        }
        //else show error
    }


    private Boolean isFormValid(String email, String password, String pass2, String username){
        boolean isValid = true;

        String validEmail= AccountUtils.isEmailValid(email);
        if ( validEmail != null){
            isValid = false;
            errorEmailLiveData.postValue(validEmail);
        };
        if(!password.equals(pass2)){
            isValid = false;
            passErrorLiveData.postValue("Passwords do not match");
            pass2ErrorLiveData.postValue("Passwords do not match");
        }
        if(password.equals("")){
            isValid = false;
            passErrorLiveData.postValue("Password cannot be empty");
        }
       if(username.equals("")){
            isValid = false;
            errorUserLiveData.postValue("Username cant be empty");
        }
        return isValid;
    }

}
