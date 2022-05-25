package com.kronostudios.the_game.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.kronostudios.the_game.R;
import com.kronostudios.the_game.databinding.LoginMainBinding;
import com.kronostudios.the_game.loginUtils.LoginViewModel;
import com.kronostudios.the_game.loginUtils.PreferencesProvider;
import com.kronostudios.the_game.loginUtils.UIUtils;
import com.kronostudios.the_game.models.Result;

public class LoginMainActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private LoginMainBinding loginMainBinding;
    private String TAG = "AAAAAAAAAAAAAAAAAAAAAAa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        loginViewModel = new LoginViewModel();
        initDataBinding();

        setup();
        data();

        loginViewModel.isUserLogged().observe(this, new Observer<Result<String>>() {
            @Override
            public void onChanged(Result<String> tokenResult) {
                loginViewModel.isLogged.postValue(false);
                if (tokenResult.getResult() != null){
                    Log.d(TAG,"Login successful, token obtained.");

                    PreferencesProvider.providePreferences().edit().putString("token",
                            tokenResult.getResult()).commit();
                    Log.d(TAG,"Login successful, add token to SharedPreferences.");

                    TextView tvEmail = findViewById(R.id.editTextEmail);
                    PreferencesProvider.providePreferences().edit().putString("email", tvEmail.getText().toString()).commit();
                    Log.d(TAG,"Successfully saved email.");

                    goTo(MainMenu.class);
                }
                else{
                    //Display Error
                    Log.d(TAG,"User not logged, token not obtained.");
                    showLoginError(tokenResult.getError().getMessage());
                }
            }
        });

    }

    private void showLoginError(String errorMessage){
        DialogInterface.OnClickListener positiveAction = (dialogInterface, i) -> dialogInterface.cancel();
        UIUtils.showAlert(this,"Error", errorMessage, "Ok",positiveAction ,null,null, false);
    }

    public void goTo (Class _class){
        Intent intent = new Intent(this, _class);
        startActivity(intent);
    }


    private void setup(){
        PreferencesProvider.init(this);
    }

    private void data(){
        String token = PreferencesProvider.providePreferences().getString("token", "");
        Log.d(TAG, "token: " + token);
        if (token.equals("")) {
            // If device has no token -> go to LoginActivity()
            //startActivity(new Intent(this, LoginActivity.class));
            //showLogin();
        } else {
            // If a userToken is stored on sharedPreferences go to MainActivity().
            startActivity(new Intent(this, MainMenu.class));
        }
        // Close the activity, the user don't need to enter again with back functionality
        //finish();
    }

    /*private void showLogin(){
        LoginRouter loginRouter = new LoginRouter();
        loginRouter.launch(this);
    }*/


    private void initDataBinding() {
        loginMainBinding =
                DataBindingUtil.setContentView(this,R.layout.login_main);
        loginMainBinding.setLoginViewModel(loginViewModel);
        loginMainBinding.setLifecycleOwner(this);
    }

    public void moveToRegister(View view) {
        startActivity(new Intent(this, RegisterUserActivity.class));
    }
}