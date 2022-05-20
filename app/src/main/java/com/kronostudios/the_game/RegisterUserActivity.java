package com.kronostudios.the_game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.kronostudios.the_game.databinding.ActivityRegisterUserBinding;
import com.kronostudios.the_game.databinding.LoginMainBinding;
import com.kronostudios.the_game.loginUtils.LoginViewModel;
import com.kronostudios.the_game.loginUtils.RegisterViewModel;

public class RegisterUserActivity extends AppCompatActivity {

    public static RegisterUserActivity userActivity;
    public static Context context;
    private RegisterViewModel registerViewModel;
    private ActivityRegisterUserBinding registerUserBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        userActivity = this;
        context = getApplicationContext();

        registerViewModel = new RegisterViewModel();
        initDataBinding();
    }


    private void initDataBinding() {
        registerUserBinding =
                DataBindingUtil.setContentView(this,R.layout.activity_register_user);
        registerUserBinding.setRegisterViewModel(registerViewModel);
        registerUserBinding.setLifecycleOwner(this);
    }

    public void goTo (Class _class){
        Intent intent = new Intent(this, _class);
        startActivity(intent);
    }

}