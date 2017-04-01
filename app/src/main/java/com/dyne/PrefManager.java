package com.dyne;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Divya on 3/17/2017.
 */

public class PrefManager {

    private static SharedPreferences pref;
    private static SharedPreferences.Editor editor;
    private static Context _context;

    static int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "dyne-app";

    private static final String IS_GOOGLE_IN_COMPLETE = "IsFirstTimeLaunch";
    private static final String USERNAME = "username";
    private static final String USER_EMAIL = "user_email";


    public static void initialise(Context context) {
        _context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static void setGoogleSignInComplete(boolean done) {
        editor.putBoolean(IS_GOOGLE_IN_COMPLETE, done);
        editor.commit();
    }

    public static boolean isGoogleSignInDone() {
        return pref.getBoolean(IS_GOOGLE_IN_COMPLETE, false);
    }


    public static void setUserName(String name) {
        editor.putString(USERNAME, name);
        editor.commit();
    }

    public static String getUserName() {
        return pref.getString(USERNAME, "");
    }

    public static void setUserEmail(String email) {
        editor.putString(USER_EMAIL, email);
        editor.commit();
    }

    public static String getUserEmail() {
        return pref.getString(USER_EMAIL, "");
    }

    public static void clearSession() {
        editor.clear();
        editor.commit();
    }

}