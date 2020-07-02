package com.dit.hp.appleseasonid.activities;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.widget.GridView;

import com.dit.hp.appleseasonid.Adapter.HomeGridViewAdapter;
import com.dit.hp.appleseasonid.Adapter.SliderAdapter;
import com.dit.hp.appleseasonid.Modal.IDCardOwnerServerVerify;
import com.dit.hp.appleseasonid.Modal.IdCardScanPojo;
import com.dit.hp.appleseasonid.Modal.ModulesPojo;
import com.dit.hp.appleseasonid.Modal.ResponsePojo;
import com.dit.hp.appleseasonid.Modal.ResponsePojoGet;
import com.dit.hp.appleseasonid.Modal.ScanDataPojo;
import com.dit.hp.appleseasonid.Modal.SuccessResponse;
import com.dit.hp.appleseasonid.Modal.UploadObject;
import com.dit.hp.appleseasonid.R;
import com.dit.hp.appleseasonid.enums.TaskType;
import com.dit.hp.appleseasonid.generic.GenericAsyncPostObject;
import com.dit.hp.appleseasonid.generic.Generic_Async_Get;
import com.dit.hp.appleseasonid.interfaces.AsyncTaskListenerObject;
import com.dit.hp.appleseasonid.json.JsonParse;
import com.dit.hp.appleseasonid.presentation.CustomDialog;
import com.dit.hp.appleseasonid.utilities.AppStatus;
import com.dit.hp.appleseasonid.utilities.Econstants;
import com.dit.hp.appleseasonid.utilities.Preferences;
import com.dit.hp.appleseasonid.utilities.SamplePresenter;
import com.kushkumardhawan.locationmanager.base.LocationBaseActivity;
import com.kushkumardhawan.locationmanager.configuration.Configurations;
import com.kushkumardhawan.locationmanager.configuration.LocationConfiguration;
import com.kushkumardhawan.locationmanager.constants.FailType;
import com.kushkumardhawan.locationmanager.constants.ProcessType;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends LocationBaseActivity implements SamplePresenter.SampleView, AsyncTaskListenerObject {


    private static final int REQUEST_CODE_QR_SCAN = 101;
    CustomDialog CD = new CustomDialog();
    private final String LOGTAG = "QRCScanner-MainActivity";
    HomeGridViewAdapter adapter_modules;
    GridView home_gv;
    SliderView sliderView;
    SliderAdapter adapters = null;
    private ProgressDialog progressDialog;

    private SamplePresenter samplePresenter;
    public String userLocation = null;
    private BroadcastReceiver mReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        home_gv = findViewById(R.id.gv);
        sliderView = findViewById(R.id.imageSlider);
        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        samplePresenter = new SamplePresenter(this);
        getLocation();

        List<ModulesPojo> modules = new ArrayList<>();

        ModulesPojo mp = new ModulesPojo();
        mp.setId("1");
        mp.setName("Generate Id Card");
        mp.setLogo("truck");

        ModulesPojo mp2 = new ModulesPojo();
        mp2.setId("2");
        mp2.setName("Scan Id Card");
        mp2.setLogo("scan");
//
        ModulesPojo mp3 = new ModulesPojo();
        mp3.setId("3");
        mp3.setName("Search ID Card");
        mp3.setLogo("searchid");

        ModulesPojo mp4 = new ModulesPojo();
        mp4.setId("4");
        mp4.setName("Log Out");
        mp4.setLogo("logout");
//
//        ModulesPojo mp4 = new ModulesPojo();
//        mp4.setId("4");
//        mp4.setName("Total Scanned Passes");
//        mp4.setLogo("history");


        modules.add(mp);
        modules.add(mp2);
        modules.add(mp3);
        modules.add(mp4);

        // Log.e("userLocation",userLocation);


        adapter_modules = new HomeGridViewAdapter(this, (ArrayList<ModulesPojo>) modules);
        home_gv.setAdapter(adapter_modules);

        adapters = new SliderAdapter(MainActivity.this);
        sliderView.setSliderAdapter(adapters);

        sliderView.setIndicatorAnimation(IndicatorAnimations.THIN_WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);

            }
        });

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                pullToRefresh.setRefreshing(false);
            }
        });

        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.e("We are Here", intent.getAction());
                   if (intent.getAction() == "verifyData") {

                    //SCAN_DATA
                    Log.e("We are Here 2sd ", intent.getAction());

                    if (AppStatus.getInstance(MainActivity.this).isOnline()) {
                        Bundle extras = intent.getExtras();
                        String data = extras.getString("VEHICLE_TRANSACTION");
                        Log.e("Data From Dialog", data.toString());

                        UploadObject object = new UploadObject();
                        object.setUrl(Econstants.url);
                        object.setTasktype(TaskType.VERIFY_DETAILS);
                        object.setMethordName(Econstants.methordSaveVehicleTransaction);
                        object.setParam(data);
                        Log.e("Data",data);

                        new GenericAsyncPostObject(
                                MainActivity.this,
                                MainActivity.this,
                                TaskType.VERIFY_DETAILS).
                                execute(object);

                    } else {
                        CD.showDialog(MainActivity.this, "Please Connect to Internet and try again.");
                    }


                } else if (intent.getAction() == "SEARCHID") {

                       //SCAN_DATA


                       if (AppStatus.getInstance(MainActivity.this).isOnline()) {
                           Bundle extras = intent.getExtras();
                           String data = extras.getString("SEARCH_DATA");
                           Log.e("Data From Dialog", data.toString());

                           UploadObject object = new UploadObject();
                           object.setUrl(Econstants.url);
                           object.setTasktype(TaskType.SEARCH_ID);
                           object.setMethordName(Econstants.methordSearchId);
                           object.setParam(data);


                           new GenericAsyncPostObject(
                                   MainActivity.this,
                                   MainActivity.this,
                                   TaskType.SEARCH_ID).
                                   execute(object);

                       } else {
                           CD.showDialog(MainActivity.this, "Please Connect to Internet and try again.");
                       }


                   }
            }
        };


    }

    private ScanDataPojo updateLocation(ScanDataPojo scanData) {
        if (!userLocation.isEmpty()) {
            try {
                String[] locations = userLocation.split(",");
                scanData.setLatitude(locations[0]);
                scanData.setLongitude(locations[1]);
            } catch (Exception ex) {
                CD.showDialog(MainActivity.this, "Unable to get the Location.");
            }
        } else {
            scanData.setLatitude("0");
            scanData.setLongitude("0");
        }

        return scanData;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            Log.d("Message", "COULD NOT GET A GOOD RESULT.");
            if (data == null) {
                return;
            } else {
                String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
                Log.e("Rsult", result);
                if (result != null) {
                    CD.showDialog(MainActivity.this, "QR Code could not be scanned!");
                }
            }
        }


        if (requestCode == REQUEST_CODE_QR_SCAN) {

            if (data == null) {
                return;
            } else {
                //Getting the passed result
                String result = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
                Log.d(LOGTAG, "Have scan result in your app activity :" + result);
                // CD.showDialog(MainActivity.this, result);
                try {
                    IdCardScanPojo scanData = JsonParse.getObjectSave(result);
                   // scanData = updateScanData(scanData);
                   // Log.e("UserLocation", userLocation);
                    //Log.e("scanDate", scanData.toString());
                   // CD.showDialogScanData(MainActivity.this, scanData);
                     uploadDataToServer(scanData);

                } catch (JSONException  e) {
                    CD.showDialog(MainActivity.this, result);
                    e.printStackTrace();
                }


            }
        }



    }

    private void uploadDataToServer(IdCardScanPojo scanData) {

        if (AppStatus.getInstance(MainActivity.this).isOnline()) {

            Log.e("Data From Dialog", scanData.toString());

            //TODO Internet Check
            UploadObject object = new UploadObject();
            object.setUrl(Econstants.url);
            object.setMethordName(Econstants.methordverifyVehicle);
            object.setTasktype(TaskType.SCAN_ID_CARD);
            object.setParam(scanData.toJSON());
            Log.e("Object", object.toString());
            new GenericAsyncPostObject(
                    MainActivity.this,
                    MainActivity.this,
                    TaskType.SCAN_ID_CARD).
                    execute(object);




        } else {
            CD.showDialog(MainActivity.this, "Please Connect to Internet and try again.");
        }
    }





    /**
     * Location Interface Methords
     *
     * @return
     */
    @Override
    public LocationConfiguration getLocationConfiguration() {
        return Configurations.defaultConfiguration("Permission Required !", "GPS needs to be turned on.");
    }

    @Override
    public void onLocationChanged(Location location) {
        samplePresenter.onLocationChanged(location);
    }

    @Override
    public void onLocationFailed(@FailType int type) {
        samplePresenter.onLocationFailed(type);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, new IntentFilter("verifyData"));
        registerReceiver(mReceiver, new IntentFilter("SEARCHID"));
        if (getLocationManager().isWaitingForLocation()
                && !getLocationManager().isAnyDialogShowing()) {
            displayProgress();
        }
    }

    @Override
    protected void onPause() {
        unregisterReceiver(mReceiver);
        super.onPause();

        dismissProgress();
    }

    private void displayProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.getWindow().addFlags(Window.FEATURE_NO_TITLE);
            progressDialog.setMessage("Getting location...");
        }

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public String getText() {
        return "";  //locationText.getText().toString()
    }

    @Override
    public void setText(String text) {
        //locationText.setText(text);
        Log.e("Location GPS", text);
        userLocation = text;
    }

    @Override
    public void updateProgress(String text) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.setMessage(text);
        }
    }

    @Override
    public void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onProcessTypeChanged(@ProcessType int processType) {
        samplePresenter.onProcessTypeChanged(processType);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        samplePresenter.destroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onTaskCompleted(ResponsePojoGet result, TaskType taskType) throws JSONException {
        if (taskType == TaskType.SCAN_ID_CARD) {
            Log.e("Data",result.toString());
            if (result.getResponse().isEmpty()) {
                CD.showDialog(MainActivity.this, "Please Connect to Internet and try again.");
            } else {

                try {
                    SuccessResponse response = JsonParse.getSuccessResponse(result.getResponse());

                    if (response.getStatus().equalsIgnoreCase("OK")) {

                        if(Econstants.checkJsonObject(response.getResponse())){
                            try{
                                Log.e("verify",response.getResponse());
                                IDCardOwnerServerVerify  IDCard = JsonParse.getIdCardUserServerDetailsComplete(response.getResponse());
                                Log.e("IDCard",IDCard.toString());
                                CD.displayIdCardDetailsComplete(MainActivity.this,IDCard, userLocation);
                            }catch(Exception ex){
                                CD.showDialog(MainActivity.this, ex.getLocalizedMessage());
                            }

                        }else{
                            CD.showDialog(MainActivity.this, "Something went wrong. Please try again.");
                        }


                    } else {
                      //  CD.showDialog(MainActivity.this, response.getResponse());
                    }
                } catch (Exception ex) {
                   // CD.showDialog(MainActivity.this, result.getResponse());
                }

            }


        }
        if(taskType == TaskType.VERIFY_DETAILS){
            if (result.getResponse().isEmpty()) {
                CD.showDialog(MainActivity.this, "Please Connect to Internet and try again.");
            } else {

                try {
                    SuccessResponse response = JsonParse.getSuccessResponse(result.getResponse());

                    if (response.getStatus().equalsIgnoreCase("OK")) {

                        if(Econstants.checkJsonObject(response.getResponse())){
                            try{
                                Log.e("verify",response.getResponse());
//                                IDCardOwnerServerVerify  IDCard = JsonParse.getIdCardUserServerDetailsComplete(response.getResponse());
//                                Log.e("IDCard",IDCard.toString());
                              //  CD.displayIdCardDetailsComplete(MainActivity.this,IDCard, userLocation);
                            }catch(Exception ex){
                                CD.showDialog(MainActivity.this, ex.getLocalizedMessage());
                            }

                        }else{
                            CD.showDialog(MainActivity.this, "Something went wrong. Please try again.");
                        }


                    } else {
                        //  CD.showDialog(MainActivity.this, response.getResponse());
                    }
                } catch (Exception ex) {
                    // CD.showDialog(MainActivity.this, result.getResponse());
                }

            }
        }
        if(taskType == TaskType.SEARCH_ID){
            if (result.getResponse().isEmpty()) {
                CD.showDialog(MainActivity.this, "Please Connect to Internet and try again.");
            } else {

                try {
                    SuccessResponse response = JsonParse.getSuccessResponse(result.getResponse());

                    if (response.getStatus().equalsIgnoreCase("OK")) {

                        if(Econstants.checkJsonObject(response.getResponse())){
                            try{
                                Log.e("verify",response.getResponse());
//                                IDCardOwnerServerVerify  IDCard = JsonParse.getIdCardUserServerDetailsComplete(response.getResponse());
//                                Log.e("IDCard",IDCard.toString());
//                                CD.displayIdCardDetailsComplete(MainActivity.this,IDCard, userLocation);
                            }catch(Exception ex){
                                CD.showDialog(MainActivity.this, ex.getLocalizedMessage());
                            }

                        }else{
                            CD.showDialog(MainActivity.this, "Something went wrong. Please try again.");
                        }


                    } else {
                        //  CD.showDialog(MainActivity.this, response.getResponse());
                    }
                } catch (Exception ex) {
                    // CD.showDialog(MainActivity.this, result.getResponse());
                }

            }
        }
    }
}
