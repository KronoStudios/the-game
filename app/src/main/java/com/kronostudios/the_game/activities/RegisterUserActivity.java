package com.kronostudios.the_game.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.kronostudios.the_game.R;
import com.kronostudios.the_game.databinding.ActivityRegisterUserBinding;
import com.kronostudios.the_game.loginUtils.PreferencesProvider;
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

        /** codi repetit. Guardo email al loguejar, o al crear usuari **/
        TextView tvEmail = findViewById(R.id.editTextEmail);
        PreferencesProvider.providePreferences().edit().putString("email", tvEmail.getText().toString()).commit();
        Log.d("RegsiterUserActivity","Successfully saved email.");

        Intent intent = new Intent(this, _class);
        startActivity(intent);
    }

}