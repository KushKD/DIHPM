package com.dit.hp.appleseasonid.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dit.hp.appleseasonid.Adapter.VehicleTypesAdapter;
import com.dit.hp.appleseasonid.Adapter.VehicleUserTypesAdapter;
import com.dit.hp.appleseasonid.Modal.IDCardPojo;
import com.dit.hp.appleseasonid.Modal.IDCardServerObject;
import com.dit.hp.appleseasonid.Modal.ResponsePojoGet;
import com.dit.hp.appleseasonid.Modal.SuccessResponse;
import com.dit.hp.appleseasonid.Modal.UploadObject;
import com.dit.hp.appleseasonid.Modal.VehicleOwnerEntries;
import com.dit.hp.appleseasonid.Modal.VehicleType;
import com.dit.hp.appleseasonid.Modal.VehicleUserTypePojo;
import com.dit.hp.appleseasonid.R;
import com.dit.hp.appleseasonid.enums.TaskType;
import com.dit.hp.appleseasonid.generic.Generic_Async_Get;
import com.dit.hp.appleseasonid.generic.Generic_Async_UploadFiles;
import com.dit.hp.appleseasonid.interfaces.AsyncTaskListenerFile;
import com.dit.hp.appleseasonid.interfaces.AsyncTaskListenerObjectGet;
import com.dit.hp.appleseasonid.json.JsonParse;
import com.dit.hp.appleseasonid.presentation.CustomDialog;
import com.dit.hp.appleseasonid.utilities.AppStatus;
import com.dit.hp.appleseasonid.utilities.CommonUtils;
import com.dit.hp.appleseasonid.utilities.DateTime;
import com.dit.hp.appleseasonid.utilities.Econstants;
import com.dit.hp.appleseasonid.utilities.Preferences;
import com.doi.spinnersearchable.SearchableSpinner;

import com.sandrios.sandriosCamera.internal.SandriosCamera;
import com.sandrios.sandriosCamera.internal.configuration.CameraConfiguration;
import com.sandrios.sandriosCamera.internal.ui.model.Media;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import id.zelory.compressor.Compressor;

import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

public class GenerateIDCard extends AppCompatActivity implements AsyncTaskListenerObjectGet, View.OnClickListener, AsyncTaskListenerFile {

    EditText name, mobilenumber, aadhaarnumber, vehicle_number, chassis_number, engine_number, driving_licence_number, remarks;
    TextView districtname, barriername, passvalidfrom, passvalidto, date, time, owneraddress, driveraddress;
    Spinner vehicletype, vehicle_owner_type;
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
    Button submit, back, click_image;
    Media media = null;
    VehicleOwnerEntries vehicleOwnerEntries = null;

    private ImageView compressedImageView;
    private File actualImage;
    private File compressedImage;
    IDCardPojo cardPojo = null;



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
        Log.e("Date", passvalidfrom.getText().toString());

        passvalidto.setText(DateTime.getDateWithOffset(1));
        Log.e("To Date", passvalidto.getText().toString());

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
                vehicleOwnerEntries = new VehicleOwnerEntries();
                vehicleOwnerEntries.setVehicleDistrictId(Integer.parseInt(Preferences.getInstance().district_id));
                vehicleOwnerEntries.setVehicleBarrierId(Integer.parseInt(Preferences.getInstance().barrier_id));
                vehicleOwnerEntries.setVehicleTypeId(Integer.parseInt(globalVehicleId));
                vehicleOwnerEntries.setVehicleOwnerTypeId(Integer.parseInt(globalVehicleUserId));
                vehicleOwnerEntries.setActive(true);

                if(!owneraddress.getText().toString().isEmpty() || owneraddress.getText().toString()!=null){
                    vehicleOwnerEntries.setVehicleOwnerAddress(owneraddress.getText().toString());
                }else{
                    vehicleOwnerEntries.setVehicleOwnerAddress("");
                }

                if(!driveraddress.getText().toString().isEmpty() || driveraddress.getText().toString()!=null){
                    vehicleOwnerEntries.setVehicleDriverAddress(driveraddress.getText().toString());
                }else{
                    vehicleOwnerEntries.setVehicleDriverAddress("");
                }







                    vehicleOwnerEntries.setIsValidFrom(passvalidfrom.getText().toString().trim());
                    vehicleOwnerEntries.setIsValidUpto(passvalidto.getText().toString().trim());




                if (vehicle_number.getText().toString() == null || vehicle_number.getText().toString().isEmpty()) {
                    vehicleOwnerEntries.setVehicleOwnerVehicleNumber("");

                } else {

                    vehicleOwnerEntries.setVehicleOwnerVehicleNumber(vehicle_number.getText().toString().trim());
                }

                if (chassis_number.getText().toString() == null || chassis_number.getText().toString().isEmpty()) {
                    vehicleOwnerEntries.setVehicleOwnerChassisNumber("");

                } else {

                    vehicleOwnerEntries.setVehicleOwnerChassisNumber(chassis_number.getText().toString().trim());
                }

                if (engine_number.getText().toString() == null || engine_number.getText().toString().isEmpty()) {
                    vehicleOwnerEntries.setVehicleOwnerEngineNumber("");

                } else {

                    vehicleOwnerEntries.setVehicleOwnerEngineNumber(engine_number.getText().toString().trim());
                }

                if (driving_licence_number.getText().toString() == null || driving_licence_number.getText().toString().isEmpty()) {
                    vehicleOwnerEntries.setVehicleOwnerDrivingLicence("");

                } else {

                    vehicleOwnerEntries.setVehicleOwnerDrivingLicence(driving_licence_number.getText().toString().trim());
                }

                if (name.getText().toString() != null && !name.getText().toString().isEmpty()) {
                    vehicleOwnerEntries.setVehicleOwnerName(name.getText().toString());

                    if (mobilenumber.getText().toString() != null && !mobilenumber.getText().toString().isEmpty() && mobilenumber.getText().toString().length()==10) {
                        vehicleOwnerEntries.setVehicleOwnerMobileNumber(Long.parseLong(mobilenumber.getText().toString().trim()));

                        if (aadhaarnumber.getText().toString() != null && !aadhaarnumber.getText().toString().isEmpty() && aadhaarnumber.getText().toString().length()==12  ) {
                            vehicleOwnerEntries.setVehicleOwnerAadhaarNumber(aadhaarnumber.getText().toString().trim());

                            if (actualImage != null) {
                                vehicleOwnerEntries.setVehicleOwnerImageName(compressedImage.getName().trim());
                                vehicleOwnerEntries.setOtherInformation("");
                                vehicleOwnerEntries.setMobileInformation("");
                                System.out.println("===" + Preferences.getInstance().userid);
                                vehicleOwnerEntries.setDataEnteredBy(Integer.parseInt(Preferences.getInstance().userid));
                                System.out.println(vehicleOwnerEntries.toJSON());

                                cardPojo = new IDCardPojo();
                                cardPojo.setFunctionName(Econstants.methordUploadData);
                                cardPojo.setVahicleEntries(vehicleOwnerEntries);
                                cardPojo.setFilePath(compressedImage.getPath());
                                cardPojo.setTaskType(TaskType.UPLOAD_DATA);
                                cardPojo.setUrl(Econstants.url);

                                if (AppStatus.getInstance(GenerateIDCard.this).isOnline()) {
                                    new Generic_Async_UploadFiles(GenerateIDCard.this,
                                            GenerateIDCard.this,
                                            TaskType.UPLOAD_DATA).execute(cardPojo);
                                } else {
                                    CD.showDialog(GenerateIDCard.this, Econstants.internetNotAvailable);
                                }


                            } else {
                                CD.showDialog(GenerateIDCard.this, "Please Click the Photo of the Person.");
                            }
                            System.out.println(vehicleOwnerEntries.toJSON());
                        } else {

                            CD.showDialog(GenerateIDCard.this,"Please enter a valid 12 digit Aadhaar number.");
                        }

                    } else {
                        CD.showDialog(GenerateIDCard.this, "Please Enter a valid 10 digit mobile number");
                    }

                } else {
                    CD.showDialog(GenerateIDCard.this, "Please Enter Name of the Person");
                }


            }
        });

        compressedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCamera();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK
                && requestCode == SandriosCamera.RESULT_CODE
                && data != null) {
            if (data.getSerializableExtra(SandriosCamera.MEDIA) instanceof Media) {
                media = (Media) data.getSerializableExtra(SandriosCamera.MEDIA);

                Log.e("File", "" + media.getPath());
                Log.e("Type", "" + media.getType());

                File imgFile = new File(media.getPath());
                Log.e("File", String.valueOf(imgFile.length()));
                actualImage = new File(media.getPath());

                clearImage();

                Compressor.getDefault(this)
                        .compressToFileAsObservable(actualImage)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<File>() {
                            @Override
                            public void call(File file) {
                                compressedImage = file;
                                setCompressedImage();
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                showError(throwable.getMessage());
                            }
                        });


                Bitmap myBitmap = BitmapFactory.decodeFile(media.getPath());
                clearImage();
                Toast.makeText(getApplicationContext(), "Media captured.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void launchCamera() {
        SandriosCamera
                .with()
                .setShowPicker(false)
                .setMediaAction(CameraConfiguration.MEDIA_ACTION_PHOTO)
                .enableImageCropping(false)
                .launchCamera(GenerateIDCard.this);


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
        compressedImageView = findViewById(R.id.compressed_image);
        owneraddress = findViewById(R.id.owneraddress);
        driveraddress = findViewById(R.id.driveraddress);

        clearImage();

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


    public static Bitmap getRotateImage(String photoPath, Bitmap bitmap) throws IOException {
        ExifInterface ei = new ExifInterface(photoPath);
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);

        Bitmap rotatedBitmap = null;
        switch (orientation) {

            case ExifInterface.ORIENTATION_ROTATE_90:
                rotatedBitmap = rotateImage(bitmap, 90);
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                rotatedBitmap = rotateImage(bitmap, 180);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                rotatedBitmap = rotateImage(bitmap, 270);
                break;

            case ExifInterface.ORIENTATION_NORMAL:
            default:
                rotatedBitmap = bitmap;
        }

        return rotatedBitmap;

    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

    private void clearImage() {

        compressedImageView.setImageDrawable(null);

    }

    private void setCompressedImage() {
        compressedImageView.setImageBitmap(BitmapFactory.decodeFile(compressedImage.getAbsolutePath()));
       // compressedImageView.setImageBitmap(getRotateImage(media.getPath(), myBitmap));

    }

    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    private int getRandomColor() {
        Random rand = new Random();
        return Color.argb(100, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    public String getReadableFileSize(long size) {
        if (size <= 0) {
            return "0";
        }
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    @Override
    public void onTaskCompleted(IDCardPojo result, TaskType taskType) throws JSONException {
        SuccessResponse response = null;
        if(taskType == result.getTaskType()){
            Log.e("Result",result.getReponse());
            if (Integer.toString(result.getResponseCode()).equalsIgnoreCase(Integer.toString(HttpsURLConnection.HTTP_OK))) {
                response = JsonParse.getSuccessResponse(result.getReponse());
                if (response.getStatus().equalsIgnoreCase("OK")) {
                    IDCardServerObject object = JsonParse.getIdCardUserServerDetails(response.getResponse());
                    Log.e("Object@#$%^",object.toString());
                    CD.showIdCard(GenerateIDCard.this,object);

                    } else {
                        CD.showDialog(GenerateIDCard.this, response.getMessage());

                    }
                } else {
                    CD.showDialog(GenerateIDCard.this, response.getMessage());
                }
            }
        }


}
