package com.dit.hp.appleseasonid.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.dit.hp.appleseasonid.Adapter.VehicleTypesAdapter;
import com.dit.hp.appleseasonid.Adapter.VehicleUserTypesAdapter;
import com.dit.hp.appleseasonid.Modal.ResponsePojoGet;
import com.dit.hp.appleseasonid.Modal.SuccessResponse;
import com.dit.hp.appleseasonid.Modal.UploadObject;
import com.dit.hp.appleseasonid.Modal.VehicleType;
import com.dit.hp.appleseasonid.Modal.VehicleUserTypePojo;
import com.dit.hp.appleseasonid.R;
import com.dit.hp.appleseasonid.enums.TaskType;
import com.dit.hp.appleseasonid.generic.Generic_Async_Get;
import com.dit.hp.appleseasonid.interfaces.AsyncTaskListenerObjectGet;
import com.dit.hp.appleseasonid.json.JsonParse;
import com.dit.hp.appleseasonid.presentation.CustomDialog;
import com.dit.hp.appleseasonid.utilities.AppStatus;
import com.dit.hp.appleseasonid.utilities.CommonUtils;
import com.dit.hp.appleseasonid.utilities.DateTime;
import com.dit.hp.appleseasonid.utilities.Econstants;
import com.dit.hp.appleseasonid.utilities.Preferences;
import com.doi.spinnersearchable.SearchableSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

import androidx.appcompat.app.AppCompatActivity;

public class GenerateIDCard extends AppCompatActivity implements AsyncTaskListenerObjectGet, View.OnClickListener {

    EditText name, mobilenumber, aadhaarnumber, vehicle_number, chassis_number, engine_number, driving_licence_number, remarks;
    TextView districtname, barriername, passvalidfrom, passvalidto, date, time;
    SearchableSpinner vehicletype, vehicle_owner_type;
    CustomDialog CD = new CustomDialog();
    List<VehicleType> vehicleTypes = null;
    List<VehicleUserTypePojo> vehicleUserTypes = null;
    VehicleTypesAdapter vehicleTypesAdapter = null;
    VehicleUserTypesAdapter vehicleUserTypesAdapter = null;
    private String globalVehicleId, globalVehicleUserId;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private String GetFromDate = null;
    Button submit,back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_id_card);
        init();


        try {
            if (AppStatus.getInstance(GenerateIDCard.this).isOnline()) {
                UploadObject object = new UploadObject();
                object.setUrl(Econstants.url);
                object.setMethordName(Econstants.methordGetVehicleType);
                object.setTasktype(TaskType.GET_VEHICLE_TYPE);
                object.setParam(Econstants.blank);
                new Generic_Async_Get(
                        GenerateIDCard.this,
                        GenerateIDCard.this,
                        TaskType.GET_VEHICLE_TYPE).
                        execute(object);
            } else {
                CD.showDialog(GenerateIDCard.this, Econstants.internetNotAvailable);
            }

        } catch (Exception ex) {
            CD.showDialog(GenerateIDCard.this, "Something Bad happened . Please reinstall the application and try again.");
        }

        try {
            if (AppStatus.getInstance(GenerateIDCard.this).isOnline()) {
                UploadObject object = new UploadObject();
                object.setUrl(Econstants.url);
                object.setMethordName(Econstants.methodVehicleUserType);
                object.setTasktype(TaskType.GET_VEHICLE_TYPE_USER);
                object.setParam(Econstants.blank);
                new Generic_Async_Get(
                        GenerateIDCard.this,
                        GenerateIDCard.this,
                        TaskType.GET_VEHICLE_TYPE_USER).
                        execute(object);
            } else {
                CD.showDialog(GenerateIDCard.this, Econstants.internetNotAvailable);
            }

        } catch (Exception ex) {
            CD.showDialog(GenerateIDCard.this, "Something Bad happened . Please reinstall the application and try again.");
        }

        SimpleDateFormat fromdateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        passvalidfrom.setText(fromdateFormat.format(new Date()));

        SimpleDateFormat todateFormat = new SimpleDateFormat("dd-MM-yyyy");
        passvalidto.setText(DateTime.getDateWithOffset(1));

        dateFormatter = new SimpleDateFormat(Econstants.Date_Format, Locale.US);
        setDateTimeField();


        vehicletype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                VehicleType item = vehicleTypesAdapter.getItem(position);

                globalVehicleId = item.getVehicleId();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        vehicle_owner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                VehicleUserTypePojo item = vehicleUserTypesAdapter.getItem(position);

                globalVehicleUserId = item.getVehicleOwnerTypeId();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GenerateIDCard.this.finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO TODAY
            }
        });
    }

    private void setDateTimeField() {
        passvalidfrom.setOnClickListener(this);
        passvalidto.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {


            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                passvalidfrom.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                passvalidto.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onClick(View v) {
        if (v == passvalidfrom) {
            fromDatePickerDialog.show();
        } else if (v == passvalidto) {
            toDatePickerDialog.show();
        }
    }


    private void init() {

        name = findViewById(R.id.name);
        mobilenumber = findViewById(R.id.mobilenumber);
        aadhaarnumber = findViewById(R.id.aadhaarnumber);
        vehicle_number = findViewById(R.id.vehicle_number);
        chassis_number = findViewById(R.id.chassis_number);
        engine_number = findViewById(R.id.engine_number);
        driving_licence_number = findViewById(R.id.driving_licence_number);
        remarks = findViewById(R.id.remarks);
        districtname = findViewById(R.id.districtname);
        barriername = findViewById(R.id.barriername);
        passvalidfrom = findViewById(R.id.passvalidfrom);
        passvalidto = findViewById(R.id.passvalidto);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        vehicletype = findViewById(R.id.vehicletype);
        vehicle_owner_type = findViewById(R.id.vehicle_owner_type);
        submit = findViewById(R.id.submit);
        back = findViewById(R.id.back);

        try {
            date.setText(DateTime.Change_Date_Format_second(CommonUtils.getCurrentDate()));
            time.setText(DateTime.changeTime(CommonUtils.getCurrentDate()));
        } catch (ParseException e) {
            time.setText("-");
            e.printStackTrace();
        }
        districtname.setText(Preferences.getInstance().districtName);
        barriername.setText(Preferences.getInstance().barrierName);


    }

    @Override
    public void onTaskCompleted(ResponsePojoGet result, TaskType taskType) throws JSONException {

        if (TaskType.GET_VEHICLE_TYPE == taskType) {
            if (result.getResponseCode().equalsIgnoreCase(Integer.toString(HttpsURLConnection.HTTP_OK))) {
                SuccessResponse response = JsonParse.getSuccessResponse(result.getResponse());
                if (response.getStatus().equalsIgnoreCase("OK")) {
                    JSONArray jsonArray = new JSONArray(response.getResponse());
                    if (jsonArray.length() != 0) {
                        vehicleTypes = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            VehicleType vehicle = new VehicleType();
                            JSONObject object = jsonArray.getJSONObject(i);
                            vehicle.setVehicleId(object.optString("vehicleId"));
                            vehicle.setVehicleName(object.optString("vehicleName"));
                            vehicle.setActive(object.optString("active"));

                            vehicleTypes.add(vehicle);
                        }
                        vehicleTypesAdapter = new VehicleTypesAdapter(this, android.R.layout.simple_spinner_item, vehicleTypes);
                        vehicletype.setAdapter(vehicleTypesAdapter);
                    } else {
                        CD.showDialog(GenerateIDCard.this, response.getMessage());
                    }
                } else {
                    CD.showDialog(GenerateIDCard.this, response.getMessage());
                }
            }
        } else if (TaskType.GET_VEHICLE_TYPE_USER == taskType) {
            if (result.getResponseCode().equalsIgnoreCase(Integer.toString(HttpsURLConnection.HTTP_OK))) {
                SuccessResponse response = JsonParse.getSuccessResponse(result.getResponse());
                if (response.getStatus().equalsIgnoreCase("OK")) {
                    JSONArray jsonArray = new JSONArray(response.getResponse());
                    if (jsonArray.length() != 0) {
                        vehicleUserTypes = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            VehicleUserTypePojo usetTypes = new VehicleUserTypePojo();
                            JSONObject object = jsonArray.getJSONObject(i);
                            usetTypes.setVehicleOwnerTypeId(object.optString("vehicleOwnerTypeId"));
                            usetTypes.setVehicleOwnerTypeName(object.optString("vehicleOwnerTypeName"));
                            usetTypes.setActive(object.optString("active"));
                            vehicleUserTypes.add(usetTypes);
                        }

                        vehicleUserTypesAdapter = new VehicleUserTypesAdapter(GenerateIDCard.this, android.R.layout.simple_spinner_item, vehicleUserTypes);
                        vehicle_owner_type.setAdapter(vehicleUserTypesAdapter);
                    } else {
                        CD.showDialog(GenerateIDCard.this, "No Barrier found for the specific District");
                        vehicleUserTypesAdapter = null;
                        vehicle_owner_type.setAdapter(vehicleUserTypesAdapter);
                    }
                } else {
                    CD.showDialog(GenerateIDCard.this, response.getMessage());
                }
            }
        }
    }
}
