package com.example.schedulebud;

import android.content.Context;
import android.content.SharedPreferences;

public class prefConfig {
    private static final String MY_PREFERENCE_NAME = "com.example.schedulebud";
    private static final String PREF_NO_LOGIN_KEY = "pref_no_login_key";
    private static final String PREF_LOGIN_TOKEN_KEY = "pref_login_token_key";
    private static final String SERVER = "http://10.0.2.2:3000/";

    public static void saveNoLoginInPref(Context context, Boolean check)
    {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(PREF_NO_LOGIN_KEY, check);
        editor.apply();
    }

    public static boolean loadNoLoginFromPref(Context context)
    {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, context.MODE_PRIVATE);
        return pref.getBoolean(PREF_NO_LOGIN_KEY,false);
    }

    public static void saveLoginTokenPref(Context context, int token)
    {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(PREF_LOGIN_TOKEN_KEY, token);
        editor.apply();
    }

    public static int loadLoginTokenFromPref(Context context)
    {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, context.MODE_PRIVATE);
        return pref.getInt(PREF_LOGIN_TOKEN_KEY,0);
    }

    public static String getServer()
    {
        return SERVER;
    }
}
