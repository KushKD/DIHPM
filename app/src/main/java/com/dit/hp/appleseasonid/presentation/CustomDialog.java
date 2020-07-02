package com.dit.hp.appleseasonid.presentation;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.dit.hp.appleseasonid.Modal.IDCardOwnerServerVerify;
import com.dit.hp.appleseasonid.Modal.IDCardServerObject;
import com.dit.hp.appleseasonid.R;
import com.dit.hp.appleseasonid.json.JsonParse;
import com.dit.hp.appleseasonid.lazyloader.ImageLoader;

import org.json.JSONException;

/**
 * @author Kush.Dhawan
 * @project HPePass
 * @Time 01, 05 , 2020
 */
public class CustomDialog {
    int downloadIdOne;


    public void showDialog(final Activity activity, String msg)  {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_custom);

        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.50);
        dialog.getWindow().setLayout(width, height);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView text = (TextView) dialog.findViewById(R.id.dialog_result);
        text.setText(msg);

        Button dialog_ok = (Button) dialog.findViewById(R.id.dialog_ok);

        dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // activity.finish();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void showDialogCloseActivity(final Activity activity, String msg)  {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_custom);

        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.50);
        dialog.getWindow().setLayout(width, height);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView text = (TextView) dialog.findViewById(R.id.dialog_result);
        text.setText(msg);

        Button dialog_ok = (Button) dialog.findViewById(R.id.dialog_ok);

        dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 activity.finish();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void showDialogHTML(final Activity activity, final String msg, final String msgServer) throws JSONException {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_custom_web);

        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels);
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels);
        dialog.getWindow().setLayout(width, height);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WebView webView = (WebView) dialog.findViewById(R.id.dialog_result);
        final EditText remarks = dialog.findViewById(R.id.remarks);
        webView.setVerticalScrollBarEnabled(true);
        webView.requestFocus();
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        final String mimeType = "text/html";
        final String encoding = "UTF-8";


        webView.loadDataWithBaseURL("", msg, mimeType, encoding, "");

        Button dialog_ok = (Button) dialog.findViewById(R.id.dialog_ok);
        Button dialog_verified = (Button) dialog.findViewById(R.id.dialog_verified);

        dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // activity.finish();
                dialog.dismiss();
            }
        });

        dialog_verified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                //TODO



                Intent intent = new Intent("verifyData");
                intent.setPackage(activity.getPackageName());
                Bundle bundle = new Bundle();

                intent.putExtras(bundle);
                activity.sendBroadcast(intent);
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void showDialogHTMLGeneric(final Activity activity, String msg) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_custom_web_generic);

        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels);
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels);
        dialog.getWindow().setLayout(width, height);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WebView webView = (WebView) dialog.findViewById(R.id.dialog_result);
        webView.setVerticalScrollBarEnabled(true);
        webView.requestFocus();
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        final String mimeType = "text/html";
        final String encoding = "UTF-8";


        webView.loadDataWithBaseURL("", msg, mimeType, encoding, "");

        Button dialog_ok = (Button) dialog.findViewById(R.id.dialog_ok);

        dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // activity.finish();
                dialog.dismiss();
            }
        });


        dialog.show();

    }


//    public void showDialogScanData(final Activity activity, final ScanDataPojo scanData) throws ParseException {
//        final Dialog dialog = new Dialog(activity);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
//        dialog.setContentView(R.layout.dialog_pojo_custom);
//
//        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
//        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.95);
//        dialog.getWindow().setLayout(width, height);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        // TextView name = (TextView)dialog.findViewById(R.id.name);
//        TextView passnumber = (TextView) dialog.findViewById(R.id.passnumber);
//        TextView vehiclenumber = (TextView) dialog.findViewById(R.id.vehiclenumber);
//        TextView mobile = (TextView) dialog.findViewById(R.id.mobile);
//        TextView dateissue = (TextView) dialog.findViewById(R.id.dateissue);
//        TextView district = (TextView) dialog.findViewById(R.id.district);
//        TextView barrier = (TextView) dialog.findViewById(R.id.barrier);
//        TextView datescan = (TextView) dialog.findViewById(R.id.datescan);
//        TextView timescan = (TextView) dialog.findViewById(R.id.timescan);
//        final EditText number_of_passengers = (EditText) dialog.findViewById(R.id.number_of_passengers);
//
//
//        // Log.e("====Manual Entry", scanData.getNumber_of_passengers_manual());
//        DatabaseHandler DB = new DatabaseHandler(activity);
//
//
//        // name.setText(taskPojo.getTask_name());
//        passnumber.setText(scanData.getPassNo());
//        vehiclenumber.setText("-");
//        mobile.setText(scanData.getMobileNumbr());
//        dateissue.setText(scanData.getDateIssueDate());
//        district.setText(DB.getDistrictNameById(scanData.getDistict()));
//        barrier.setText(DB.getBarrierNameById(scanData.getDistict(), scanData.getBarrrier()));
//        datescan.setText(DateTime.Change_Date_Format_second(scanData.getScanDate()));
//        Log.e("ScanDae", DateTime.Change_Date_Format_second(scanData.getScanDate()));
//        timescan.setText(DateTime.changeTime(scanData.getScanDate()));
////        task_completed_by.setText(taskPojo.getTask_completed_by_name());
//
//
//        Button cancel = (Button) dialog.findViewById(R.id.cancel);
//        Button proceed = (Button) dialog.findViewById(R.id.proceed);
//
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // activity.finish();
//                dialog.dismiss();
//            }
//        });
//
//        proceed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (!number_of_passengers.getText().toString().isEmpty() && number_of_passengers.getText().toString() != null) {
//
//                    scanData.setNumber_of_passengers_manual(number_of_passengers.getText().toString());
//                    System.out.println("====Manual Entry" + scanData.getNumber_of_passengers_manual());
//                } else {
//                    scanData.setNumber_of_passengers_manual("0");
//                    System.out.println("====Manual Entry" + scanData.getNumber_of_passengers_manual());
//                }
//
//
//                //Start ASYNC TASK TODO
//                // prepare your parameters that need to be sent back to activity
//                Intent intent = new Intent("UploadServer");
//                intent.setPackage(activity.getPackageName());
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("SCAN_DATA", scanData);
//                intent.putExtras(bundle);
//                activity.sendBroadcast(intent);
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//
//    }

//    public void showDialogSearchByPassId(final Activity activity) throws ParseException {
//        final ScanDataPojo scanData = new ScanDataPojo();
//        scanData.setScannedByPhoneNumber(Preferences.getInstance().phone_number);
//        scanData.setDistict(Preferences.getInstance().district_id);
//        scanData.setBarrrier(Preferences.getInstance().barrier_id);
//        scanData.setLatitude("0");
//        scanData.setLongitude("0");
//        scanData.setPassNo("-");
//        scanData.setMobileNumbr("-");
//        scanData.setPrsonNo("-");
//        scanData.setDateIssueDate("-");
//        scanData.setScanDate(CommonUtils.getCurrentDate());
//        final Dialog dialog = new Dialog(activity);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
//        dialog.setContentView(R.layout.dialog_pojopassid_custom);
//
//        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
//        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.95);
//        dialog.getWindow().setLayout(width, height);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        // TextView name = (TextView)dialog.findViewById(R.id.name);
//        final EditText passnumber = (EditText) dialog.findViewById(R.id.passnumber);
//
//
//        TextView district = (TextView) dialog.findViewById(R.id.district);
//        TextView barrier = (TextView) dialog.findViewById(R.id.barrier);
//        TextView datescan = (TextView) dialog.findViewById(R.id.datescan);
//        TextView timescan = (TextView) dialog.findViewById(R.id.timescan);
//        final EditText number_of_passengers = (EditText) dialog.findViewById(R.id.number_of_passengers);
//
//
//        // Log.e("====Manual Entry", scanData.getNumber_of_passengers_manual());
//        DatabaseHandler DB = new DatabaseHandler(activity);
//
//
//        // name.setText(taskPojo.getTask_name());
//
//
//        district.setText(DB.getDistrictNameById(scanData.getDistict()));
//        barrier.setText(DB.getBarrierNameById(scanData.getDistict(), scanData.getBarrrier()));
//        datescan.setText(DateTime.Change_Date_Format_second(scanData.getScanDate()));
//
//        timescan.setText(DateTime.changeTime(scanData.getScanDate()));
////        task_completed_by.setText(taskPojo.getTask_completed_by_name());
//
//
//        Button cancel = (Button) dialog.findViewById(R.id.cancel);
//        Button proceed = (Button) dialog.findViewById(R.id.proceed);
//
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // activity.finish();
//                dialog.dismiss();
//            }
//        });
//
//        proceed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (!number_of_passengers.getText().toString().isEmpty() && number_of_passengers.getText().toString() != null) {
//
//                    scanData.setNumber_of_passengers_manual(number_of_passengers.getText().toString());
//                    System.out.println("====Manual Entry" + scanData.getNumber_of_passengers_manual());
//                } else {
//                    scanData.setNumber_of_passengers_manual("0");
//                }
//
//
//                if (!passnumber.getText().toString().isEmpty() && passnumber.getText().toString() != null) {
//
//                    scanData.setPassNo(passnumber.getText().toString());
//                    System.out.println("====Manual Entry" + scanData.getPassNo());
//                    Intent intent = new Intent("UploadServerManual");
//                    intent.setPackage(activity.getPackageName());
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("SCAN_DATA", scanData);
//                    intent.putExtras(bundle);
//                    activity.sendBroadcast(intent);
//                    dialog.dismiss();
//                } else {
//                    showDialog(activity, "Please Enter Pass Number");
//                }
//
//
//            }
//        });
//
//        dialog.show();
//
//    }

    public void showIdCard(final Activity activity, final IDCardServerObject object)  {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_custom_id);

        ImageLoader il = new ImageLoader(activity);

        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels );
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels );
        dialog.getWindow().setLayout(width, height);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView name = (TextView) dialog.findViewById(R.id.name);
        TextView id_card = (TextView) dialog.findViewById(R.id.id_card);
        ImageView id_photo =  (ImageView)dialog.findViewById(R.id.id_photo);
        Button sms = dialog.findViewById(R.id.sms);

        il.DisplayImage(object.getImageUrl(), id_photo, null,null, false);


        name.setText(object.getDriverName());
        id_card.setText(object.getIdCardNumber());

        Button dialog_ok = (Button) dialog.findViewById(R.id.dialog_ok);

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage(object.getPhoneNumber(), null, object.getGenerateIDCardUrl_(), null,null);
            }
        });

        dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void displayIdCardDetails(final Activity activity, final IDCardServerObject object)  {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_custom_id);

        ImageLoader il = new ImageLoader(activity);

        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels );
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels );
        dialog.getWindow().setLayout(width, height);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView name = (TextView) dialog.findViewById(R.id.name);
        TextView id_card = (TextView) dialog.findViewById(R.id.id_card);
        ImageView id_photo =  (ImageView)dialog.findViewById(R.id.id_photo);
        Button sms = dialog.findViewById(R.id.sms);

        il.DisplayImage(object.getImageUrl(), id_photo, null,null, false);


        name.setText(object.getDriverName());
        id_card.setText(object.getIdCardNumber());

        Button dialog_ok = (Button) dialog.findViewById(R.id.dialog_ok);

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage(object.getPhoneNumber(), null, object.getGenerateIDCardUrl_(), null,null);
            }
        });

        dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
                dialog.dismiss();
            }
        });

        dialog.show();

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void displayIdCardDetailsComplete(final Activity activity, final IDCardOwnerServerVerify object)  {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_custom_id_complete);

        ImageLoader il = new ImageLoader(activity);



        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels );
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels );
        dialog.getWindow().setLayout(width, height);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView name = (TextView) dialog.findViewById(R.id.name);
        TextView mobilenumber = (TextView) dialog.findViewById(R.id.mobilenumber);
        TextView passvalidfrom = (TextView) dialog.findViewById(R.id.passvalidfrom);
        TextView passvalidto = (TextView) dialog.findViewById(R.id.passvalidto);
        TextView aadhaarnumber = (TextView) dialog.findViewById(R.id.aadhaarnumber);
        TextView vehicletype = (TextView) dialog.findViewById(R.id.vehicletype);
        TextView vehicle_owner_type = (TextView) dialog.findViewById(R.id.vehicle_owner_type);
        TextView vehicle_number = (TextView) dialog.findViewById(R.id.vehicle_number);
        TextView chassis_number = (TextView) dialog.findViewById(R.id.chassis_number);
        TextView engine_number = (TextView) dialog.findViewById(R.id.engine_number);
        TextView driving_licence_number = (TextView) dialog.findViewById(R.id.driving_licence_number);
        TextView districtname = (TextView) dialog.findViewById(R.id.districtname);
        TextView barriername = (TextView) dialog.findViewById(R.id.barriername);
        TextView id_card = (TextView) dialog.findViewById(R.id.id_card);
        ImageView id_photo =  (ImageView)dialog.findViewById(R.id.compressed_image);
        Button verify = dialog.findViewById(R.id.verify);

        il.DisplayImage(object.getImageurl(), id_photo, null,null, false);


        name.setText(object.getVehicleOwnerName());
      //  mobilenumber.setText(Long.valueOf(object.getVehicleOwnerMobileNumber()));
        passvalidfrom.setText(object.getIsValidFrom());
        passvalidto.setText(object.getIsValidUpto());
        aadhaarnumber.setText(object.getVehicleOwnerAadhaarNumber());
        //vehicletype.setText(object.getVehicleTypeId());
       // vehicle_owner_type.setText(object.getVehicleOwnerId());
        vehicle_number.setText(object.getVehicleOwnerVehicleNumber());
        chassis_number.setText(object.getVehicleOwnerChassisNumber());
        engine_number.setText(object.getVehicleOwnerEngineNumber());
        driving_licence_number.setText(object.getVehicleOwnerDrivingLicence());
      //  districtname.setText(object.getVehicleDistrictId());
       // barriername.setText(object.getVehicleBarrierId());
        id_card.setText(object.getIdCardNumber());

        Button dialog_ok = (Button) dialog.findViewById(R.id.back);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Verify
            }
        });

        dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // activity.finish();
                dialog.dismiss();
            }
        });

        dialog.show();

    }



}
