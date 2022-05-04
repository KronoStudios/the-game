package com.kronostudios.the_game.loginUtils.network;

import androidx.annotation.NonNull;


import com.kronostudios.the_game.loginUtils.PreferencesProvider;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ServiceInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        String token = PreferencesProvider.providePreferences().getString("token","");

        if(request.header("No-Authentication") == null){
            request = request.newBuilder()
                    .addHeader("Authorization", token)
                    .build();
        }
        return chain.proceed(request);
    }
}
