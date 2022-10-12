package com.example.schedulebud;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.schedulebud.main_activity_fragments.schedule.ToDoTask;
import com.example.schedulebud.main_activity_fragments.schedule.ToDoTaskList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class prefConfig {
    private static final String MY_PREFERENCE_NAME = "com.example.schedulebud";
    private static final String PREF_NO_LOGIN_KEY = "pref_no_login_key";
    private static final String PREF_LOGIN_TOKEN_KEY = "pref_login_token_key";
    private static final String PREF_EMAIL_KEY = "pref_email_key";
    private static final String PREF_TO_DO_TASK_LIST_KEY = "pref_to_do_task_list_key";
    private static final String PREF_SCHEDULE_KEY = "pref_schedule_key";
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
        String emptyToDoTaskList = gson.toJson(new ToDoTaskList(new ArrayList<>()));
        String json = pref.getString(PREF_TO_DO_TASK_LIST_KEY, emptyToDoTaskList);
        return gson.fromJson(json, ToDoTaskList.class);
    }

    public static void saveSchedulePref(Context context, ArrayList<ArrayList<String>> schedule)
    {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(schedule);
        editor.putString(PREF_SCHEDULE_KEY, json);
        editor.apply();
    }

    public static ArrayList<ArrayList<String>> loadScheduleFromPref(Context context)
    {
        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, context.MODE_PRIVATE);
        Gson gson = new Gson();
        ArrayList<ArrayList<String>> emptySchedule = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            emptySchedule.add(new ArrayList<>());
        }
        String emptyScheduleString = gson.toJson(emptySchedule);
        String json = pref.getString(PREF_SCHEDULE_KEY, emptyScheduleString);
        Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
        return gson.fromJson(json, type);
    }

    public static String getServer()
    {
        return SERVER;
    }

    public static String getMonthFormat(int month) {
        switch(month) {
            case 0:
                return "JAN";
            case 1:
                return "FEB";
            case 2:
                return "MAR";
            case 3:
                return "APR";
            case 4:
                return "MAY";
            case 5:
                return "JUN";
            case 6:
                return "JUL";
            case 7:
                return "AUG";
            case 8:
                return "SEP";
            case 9:
                return "OCT";
            case 10:
                return "NOV";
            case 11:
                return "DEC";
            default:
                return "NOT A MONTH";
        }
    }

    public static int getIntFromMonthString(String month) {
        switch(month) {
            case "JAN":
                return 0;
            case "FEB":
                return 1;
            case "MAR":
                return 2;
            case "APR":
                return 3;
            case "MAY":
                return 4;
            case "JUN":
                return 5;
            case "JUL":
                return 6;
            case "AUG":
                return 7;
            case "SEP":
                return 8;
            case "OCT":
                return 9;
            case "NOV":
                return 10;
            case "DEC":
                return 11;
            default:
                return -1;
        }
    }

    public static String makeDateString(int day, int month, int year) {
        String dayString = Integer.toString(day);
        if (day < 10) {
            dayString= "0"+day;
        }
        return dayString + " " + getMonthFormat(month) + " " + year;
    }

    public static String makeTimeString(int hour, int minute) {
        String minuteString = String.valueOf(minute);
        if (minute < 10) {
            minuteString = "0"+minute;
        }
        return hour + ":" + minuteString;
    }

    public static String makeDateTimeString(int day, int month, int year, int hour, int minute) {
        return makeDateString(day, month, year) + " " + makeTimeString(hour, minute);
    }

    public static int[] getDateFromString(String dateString) {
        String[] splitDate = dateString.split(" ");
        int day = Integer.parseInt(splitDate[0]);
        int month = getIntFromMonthString(splitDate[1]);
        int year = Integer.parseInt(splitDate[2]);
        return new int[]{day, month, year};
    }

    public static int[] getTimeFromString(String timeString) {
        String[] splitTime = timeString.split(":");
        int hour = Integer.parseInt(splitTime[0]);
        int minute = Integer.parseInt(splitTime[1]);
        return new int[]{hour, minute};
    }

    public static int[] getDateTimeFromString(String dateTimeString) {
        String[] splitDateTime = dateTimeString.split(" ");
        String[] splitTime = splitDateTime[3].split(":");
        int day = Integer.parseInt(splitDateTime[0]);
        int month = getIntFromMonthString(splitDateTime[1]);
        int year = Integer.parseInt(splitDateTime[2]);
        int hour = Integer.parseInt(splitTime[0]);
        int minute = Integer.parseInt(splitTime[1]);
        return new int[]{day, month, year, hour, minute};
    }
}
