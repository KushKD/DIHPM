package com.dit.hp.appleseasonid.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Kush.Dhawan
 * @project HPePass
 * @Time 01, 05 , 2020
 */
public class Preferences {

    private static Preferences instance;
        private  String preferenceName = "com.dit.himachal.appleseasonidpass";
        private SharedPreferences preferences;
        private SharedPreferences.Editor editor;
        private String KEY_DISTRICTID = "district_id";
        private String KEY_BARRIERID = "barrier_id";
        private String KEY_PHONENUMBER = "phone_number";
        private String KEY_IS_LOGED_IN = "isLoggedIn";
        private String KEY_NAME = "name";
        private String KEY_NAME_DEPARTMENT = "dept_name";
        private String KEY_USER_ID = "userid";
    private String KEY_BARRIER_NAME = "barrierName";
    private String KEY_DISTRICT_NAME = "districtName";



        public String district_id;
    public String barrier_id;
    public String phone_number;
    public String name;
    public String dept_name;
    public String barrierName;
    public String districtName;
    public String userid;
        public boolean isLoggedIn;


        private Preferences()
        {

        }

        public synchronized static Preferences getInstance()
        {
            if(instance == null)
                instance = new Preferences();
            return instance;
        }

        public void loadPreferences(Context c)
        {
            preferences = c.getSharedPreferences(preferenceName, Activity.MODE_PRIVATE);
            district_id = preferences.getString(KEY_DISTRICTID, "");
            barrier_id = preferences.getString(KEY_BARRIERID, "");
            phone_number = preferences.getString(KEY_PHONENUMBER, "");
            userid = preferences.getString(KEY_USER_ID,"");
            isLoggedIn = preferences.getBoolean(KEY_IS_LOGED_IN, isLoggedIn);
            name = preferences.getString(KEY_NAME,"");
            dept_name = preferences.getString(KEY_NAME_DEPARTMENT,"");
            districtName = preferences.getString(KEY_DISTRICT_NAME,"");
            barrierName = preferences.getString(KEY_BARRIER_NAME,"");


        }

        public void savePreferences(Context c)
        {
            preferences = c.getSharedPreferences(preferenceName, Activity.MODE_PRIVATE);
            editor = preferences.edit();
            editor.putString(KEY_DISTRICTID, district_id);
            editor.putString(KEY_BARRIERID, barrier_id);
            editor.putString(KEY_PHONENUMBER, phone_number);
            editor.putString(KEY_NAME, name);
            editor.putString(KEY_NAME_DEPARTMENT, dept_name);
            editor.putString(KEY_DISTRICT_NAME, districtName);
            editor.putString(KEY_BARRIER_NAME, barrierName);
            editor.putBoolean(KEY_IS_LOGED_IN, isLoggedIn);
            editor.putString(KEY_USER_ID,userid);
            //editor.clear();
            editor.commit();
        }

}