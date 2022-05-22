package com.kronostudios.the_game.loginUtils.network;

import com.kronostudios.the_game.core.APIController;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    /*
    Comento aquesta linia per a que apunti a APIController.HOST, així nomès hem de canviar 1 IP.
    private static final String BASE_URL = "http://192.168.1.91:8000";
    */
    private static OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new ServiceInterceptor())
            .build();

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    //.baseUrl(BASE_URL)
                    .baseUrl(APIController.HOST + APIController.PORT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .callFactory((Call.Factory) client)
                    .build();
        }
        return retrofit;
    }
}
