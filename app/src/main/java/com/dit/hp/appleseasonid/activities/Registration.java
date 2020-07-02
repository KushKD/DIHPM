package com.dit.hp.appleseasonid.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dit.hp.appleseasonid.Adapter.GenericAdapter;
import com.dit.hp.appleseasonid.Adapter.GenericAdapterBarrier;
import com.dit.hp.appleseasonid.Modal.DistrictBarrierPojo;
import com.dit.hp.appleseasonid.Modal.DistrictPojo;
import com.dit.hp.appleseasonid.Modal.ResponsePojoGet;
import com.dit.hp.appleseasonid.Modal.SuccessResponse;
import com.dit.hp.appleseasonid.Modal.UploadObject;
import com.dit.hp.appleseasonid.Modal.User;
import com.dit.hp.appleseasonid.R;
import com.dit.hp.appleseasonid.enums.TaskType;
import com.dit.hp.appleseasonid.generic.Generic_Async_Get;
import com.dit.hp.appleseasonid.interfaces.AsyncTaskListenerObjectGet;
import com.dit.hp.appleseasonid.json.JsonParse;
import com.dit.hp.appleseasonid.presentation.CustomDialog;
import com.dit.hp.appleseasonid.utilities.AppStatus;
import com.dit.hp.appleseasonid.utilities.Econstants;
import com.dit.hp.appleseasonid.utilities.Preferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Registration extends AppCompatActivity implements AsyncTaskListenerObjectGet {


    private String Global_district_id, globalDistrictName;
    private String Global_barrier_id, globalBarrierName;


    public List<DistrictPojo> districts = null;


    public List<DistrictPojo> districts_sp = null;
    public List<DistrictBarrierPojo> barrirs = null;
    CustomDialog CD = new CustomDialog();

    GenericAdapter adapter_district = null;
    GenericAdapterBarrier adapter_barrier = null;

    com.doi.spinnersearchable.SearchableSpinner sp_district, sp_barrier;
    EditText phone, otp;
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        requestPermissions();

        sp_district = findViewById(R.id.sp_district);
        sp_district.setTitle("Please Select District");
        sp_district.setPrompt("Please Select District");
        sp_barrier = findViewById(R.id.sp_barrier);
        sp_barrier.setTitle("Please Select Barrier");
        sp_barrier.setPrompt("Please Select Barrier");

        phone = findViewById(R.id.phone);
        phone.addTextChangedListener(textWatcher);
        otp = findViewById(R.id.otp);
       // otp.addTextChangedListener(verifyTextWatcher);
        register = findViewById(R.id.register);


        try {
            if (AppStatus.getInstance(Registration.this).isOnline()) {
                UploadObject object = new UploadObject();
                object.setUrl(Econstants.url);
                object.setMethordName(Econstants.methordGetDistrict);
                object.setTasktype(TaskType.GET_DISTRICT);
                object.setParam(Econstants.stateID);
                Log.e("Object", object.toString());
                new Generic_Async_Get(
                        Registration.this,
                        Registration.this,
                        TaskType.GET_DISTRICT).
                        execute(object);
            } else {
                CD.showDialog(Registration.this, Econstants.internetNotAvailable);
            }

        } catch (Exception ex) {
            CD.showDialog(Registration.this, "Something Bad happened . Please reinstall the application and try again.");
        }


        sp_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DistrictPojo item = adapter_district.getItem(position);

                Global_district_id = item.getDistrictId();
                globalDistrictName = item.getDistrictName();

                try {
                    if (AppStatus.getInstance(Registration.this).isOnline()) {
                        UploadObject object = new UploadObject();
                        object.setUrl(Econstants.url);
                        object.setMethordName(Econstants.methordGetBarriers);
                        object.setTasktype(TaskType.GET_BARRIERS);
                        object.setParam(Global_district_id);
                        Log.e("Object", object.toString());
                        new Generic_Async_Get(
                                Registration.this,
                                Registration.this,
                                TaskType.GET_BARRIERS).
                                execute(object);
                    } else {
                        CD.showDialog(Registration.this, Econstants.internetNotAvailable);
                    }

                } catch (Exception ex) {
                    CD.showDialog(Registration.this, "Something Bad happened . Please reinstall the application and try again.");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_barrier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DistrictBarrierPojo item = adapter_barrier.getItem(position);
                Log.e("We are Here", item.getBarrierId());
                Global_barrier_id = item.getBarrierId();
                globalBarrierName = item.getBarrierName();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Test","dfdfd");
                if (otp.getText().toString().length() == 6) {
                    if (AppStatus.getInstance(Registration.this).isOnline()) {
                        UploadObject object = new UploadObject();
                        object.setUrl(Econstants.url);
                        object.setMethordName(Econstants.methordVerifyOtp);
                        object.setTasktype(TaskType.VEREIFY_OTP);
                        object.setParam(phone.getText().toString().trim() + "/" + otp.getText().toString().trim());
                        Log.e("Object", object.toString());
                        new Generic_Async_Get(
                                Registration.this,
                                Registration.this,
                                TaskType.VEREIFY_OTP).
                                execute(object);
                    } else {
                        CD.showDialog(Registration.this, Econstants.internetNotAvailable);
                    }
                }
            else {
                    CD.showDialog(Registration.this, "Please enter a valid OTP Number.");
                }
            }
        });





    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try {
                if (s.length() == 10) {
                    if (AppStatus.getInstance(Registration.this).isOnline()) {
                        UploadObject object = new UploadObject();
                        object.setUrl(Econstants.url);
                        object.setMethordName(Econstants.methordGetOTP);
                        object.setTasktype(TaskType.GET_OTP);
                        object.setParam(s.toString());
                        Log.e("Object", object.toString());
                        new Generic_Async_Get(
                                Registration.this,
                                Registration.this,
                                TaskType.GET_OTP).
                                execute(object);
                    } else {
                        CD.showDialog(Registration.this, Econstants.internetNotAvailable);
                    }
                }
            } catch (Exception ex) {
                Log.e("Da43", ex.getLocalizedMessage().toString());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    TextWatcher verifyTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try {
                if (s.length() == 6) {
                    if (AppStatus.getInstance(Registration.this).isOnline()) {
                        UploadObject object = new UploadObject();
                        object.setUrl(Econstants.url);
                        object.setMethordName(Econstants.methordVerifyOtp);
                        object.setTasktype(TaskType.VEREIFY_OTP);
                        object.setParam(phone.getText().toString().trim()+"/"+s.toString());
                        Log.e("Object", object.toString());
                        new Generic_Async_Get(
                                Registration.this,
                                Registration.this,
                                TaskType.VEREIFY_OTP).
                                execute(object);
                    } else {
                        CD.showDialog(Registration.this, Econstants.internetNotAvailable);
                    }
                }
            } catch (Exception ex) {
                Log.e("Da43", ex.getLocalizedMessage().toString());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };


    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,

                        Manifest.permission.INTERNET,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.CHANGE_NETWORK_STATE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                        Manifest.permission.CAMERA,
                        Manifest.permission.VIBRATE,
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.RECEIVE_SMS


                }, 0);
            }
        }


    }


    @Override
    public void onTaskCompleted(ResponsePojoGet result, TaskType taskType) throws JSONException {


        if (TaskType.GET_DISTRICT == taskType) {
            if (result.getResponseCode().equalsIgnoreCase(Integer.toString(HttpsURLConnection.HTTP_OK))) {
                SuccessResponse response = JsonParse.getSuccessResponse(result.getResponse());
                if (response.getStatus().equalsIgnoreCase("OK")) {
                    JSONArray jsonArray = new JSONArray(response.getResponse());
                    if (jsonArray.length() != 0) {
                        districts = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            DistrictPojo pojo = new DistrictPojo();
                            JSONObject object = jsonArray.getJSONObject(i);
                            pojo.setDistrictId(object.optString("districtId"));
                            pojo.setDistrictName(object.optString("districtName"));
                            pojo.setStateId(object.optString("stateId"));
                            pojo.setActive(object.optString("active"));

                            districts.add(pojo);
                        }
                        adapter_district = new GenericAdapter(this, android.R.layout.simple_spinner_item, districts);
                        sp_district.setAdapter(adapter_district);
                    } else {
                        CD.showDialog(Registration.this, response.getMessage());
                    }
                } else {
                    CD.showDialog(Registration.this, response.getMessage());
                }
            }
        } else if (TaskType.GET_BARRIERS == taskType) {
            if (result.getResponseCode().equalsIgnoreCase(Integer.toString(HttpsURLConnection.HTTP_OK))) {
                SuccessResponse response = JsonParse.getSuccessResponse(result.getResponse());
                if (response.getStatus().equalsIgnoreCase("OK")) {
                    JSONArray jsonArray = new JSONArray(response.getResponse());
                    if (jsonArray.length() != 0) {
                        barrirs = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            DistrictBarrierPojo pojo = new DistrictBarrierPojo();
                            JSONObject object = jsonArray.getJSONObject(i);
                            pojo.setDistrictId(object.optString("districtId"));
                            pojo.setBarrierName(object.optString("barrierName"));
                            pojo.setBarrierId(object.optString("barrierId"));

                            barrirs.add(pojo);
                        }

                        adapter_barrier = new GenericAdapterBarrier(Registration.this, android.R.layout.simple_spinner_item, barrirs);
                        sp_barrier.setAdapter(adapter_barrier);
                    } else {
                        CD.showDialog(Registration.this, "No Barrier found for the specific District");
                        adapter_barrier = null;
                        sp_barrier.setAdapter(adapter_barrier);
                    }
                } else {
                    CD.showDialog(Registration.this, response.getMessage());
                }
            }
        } else if (TaskType.GET_OTP == taskType) {
            SuccessResponse response = null;
            try{
                if (result.getResponseCode().equalsIgnoreCase(Integer.toString(HttpsURLConnection.HTTP_OK))) {
                    response = JsonParse.getSuccessResponse(result.getResponse());
                    if (response.getStatus().equalsIgnoreCase("OK")) {

                        Toast.makeText(Registration.this, response.getResponse(), Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(Registration.this, response.getResponse(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Registration.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }catch(Exception ex){
                Toast.makeText(Registration.this, "Unable to send OTP . Please try again.", Toast.LENGTH_SHORT).show();
            }

        }
        else if (TaskType.VEREIFY_OTP == taskType) {
            SuccessResponse response = null;
            User userData = new User();
            if (result.getResponseCode().equalsIgnoreCase(Integer.toString(HttpsURLConnection.HTTP_OK))) {
                response = JsonParse.getSuccessResponse(result.getResponse());
                if (response.getStatus().equalsIgnoreCase("OK")) {

                    userData = JsonParse.getUSerDetilas(response.getResponse());
                        Preferences.getInstance().loadPreferences(Registration.this);
                        Preferences.getInstance().district_id = Global_district_id;
                        Preferences.getInstance().districtName = globalDistrictName;
                        Preferences.getInstance().barrier_id = Global_barrier_id;
                        Preferences.getInstance().barrierName = globalBarrierName;
                        Preferences.getInstance().phone_number = String.valueOf(userData.getMobileNumber());
                        System.out.println("==="+userData.getUserId());
                       Preferences.getInstance().userid = String.valueOf(userData.getUserId());
                        Preferences.getInstance().isLoggedIn = true;
                        Preferences.getInstance().savePreferences(Registration.this);
                        Intent mainIntent = new Intent(Registration.this, MainActivity.class);
                        Registration.this.startActivity(mainIntent);
                        Registration.this.finish();

                } else {
                    Log.e("User else", response.getResponse());
                    Toast.makeText(Registration.this, response.getResponse(), Toast.LENGTH_SHORT).show();
                }
            } else {
                CD.showDialog(Registration.this, response.getMessage());
            }
        }


    }
}
