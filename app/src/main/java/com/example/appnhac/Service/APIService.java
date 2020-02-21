package com.example.appnhac.Service;

public class APIService {

    private static final String Base_URL = "https://perissodactyl-gallo.000webhostapp.com/Server/";

    public static Dataservice getService() {
        return APIRetrofitClient.getClient(Base_URL).create(Dataservice.class);
    }
}
