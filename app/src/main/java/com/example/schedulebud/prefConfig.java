package com.example.schedulebud;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.schedulebud.main_activity_fragments.schedule.ToDoTask;
import com.example.schedulebud.main_activity_fragments.schedule.ToDoTaskList;
import com.google.gson.Gson;

import java.util.ArrayList;

public class prefConfig {
    private static final String MY_PREFERENCE_NAME = "com.example.schedulebud";
    private static final String PREF_NO_LOGIN_KEY = "pref_no_login_key";
    private static final String PREF_LOGIN_TOKEN_KEY = "pref_login_token_key";
    private static final String PREF_EMAIL_KEY = "pref_email_key";
    private static final String PREF_TO_DO_TASK_LIST_KEY = "pref_to_do_task_list_key";
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

    public static void saveLoginTokenPref(Context context, String token)
    {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_LOGIN_TOKEN_KEY, token);
        editor.apply();
    }

    public static String loadLoginTokenFromPref(Context context)
    {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, context.MODE_PRIVATE);
        return pref.getString(PREF_LOGIN_TOKEN_KEY,"");
    }

    public static void saveEmailPref(Context context, String email)
    {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_EMAIL_KEY, email);
        editor.apply();
    }

    public static String loadEmailFromPref(Context context)
    {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, context.MODE_PRIVATE);
        return pref.getString(PREF_EMAIL_KEY,"guest");
    }

    public static void saveToDoTaskListPref(Context context, ToDoTaskList toDoTaskList)
    {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(toDoTaskList);
        editor.putString(PREF_TO_DO_TASK_LIST_KEY, json);
        editor.apply();
    }

    public static ToDoTaskList loadToDoTaskListFromPref(Context context)
    {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, context.MODE_PRIVATE);
        Gson gson = new Gson();
        String emptyToDoTaskList = gson.toJson(new ToDoTaskList(new ArrayList<ToDoTask>()));
        String json = pref.getString(PREF_TO_DO_TASK_LIST_KEY, emptyToDoTaskList);
        ToDoTaskList toDoTaskList = gson.fromJson(json, ToDoTaskList.class);
        return toDoTaskList;
    }

    public static String getServer()
    {
        return SERVER;
    }
}
