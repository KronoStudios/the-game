package com.kronostudios.the_game.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;

import com.kronostudios.the_game.R;
import com.kronostudios.the_game.databinding.LoginMainBinding;
import com.kronostudios.the_game.loginUtils.LoginViewModel;

public class LoginMainActivity extends AppCompatActivity {


    private LoginViewModel loginViewModel;
    private LoginMainBinding activityLoginBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        loginViewModel = new LoginViewModel();
        initDataBinding();
        loginViewModel.getEmailLiveData().observe(this, new Observer<String>(){
            @Override
            public void onChanged(String s){
                Log.d("DataBind", "getEmailLiveData "+ s);
            }
        });
    }


    private void initDataBinding() {
        activityLoginBinding =
                DataBindingUtil.setContentView(this,R.layout.login_main);
        activityLoginBinding.setLoginViewModel(loginViewModel);
        activityLoginBinding.setLifecycleOwner(this);
    }

}