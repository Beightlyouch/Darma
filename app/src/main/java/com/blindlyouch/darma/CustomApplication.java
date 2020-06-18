package com.blindlyouch.darma;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class CustomApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this); //ライブラリ初期化
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);

        CustomApplication.context = getApplicationContext();
    }

    public static Context getContext(){
        return CustomApplication.context;
    }
}

