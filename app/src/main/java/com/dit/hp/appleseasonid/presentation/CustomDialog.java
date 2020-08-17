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
import com.dit.hp.appleseasonid.Modal.IdCardScanPojo;
import com.dit.hp.appleseasonid.Modal.SaarthiObject;
import com.dit.hp.appleseasonid.Modal.VahanObject;
import com.dit.hp.appleseasonid.Modal.VehicleDetailsObject;
import com.dit.hp.appleseasonid.Modal.VehicleInOutTrans;
import com.dit.hp.appleseasonid.R;
import com.dit.hp.appleseasonid.activities.MainActivity;
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


    public void showDialog(final Activity activity, String msg) {
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

    public void showDialogCloseActivity(final Activity activity, String msg) {
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


    public void showDialogSearchByPassId(final Activity activity) {

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_search_id);

        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.95);
        dialog.getWindow().setLayout(width, height);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // TextView name = (TextView)dialog.findViewById(R.id.name);
        final EditText mobilenumber = (EditText) dialog.findViewById(R.id.mobilenumber);
        final EditText vehiclenumber = (EditText) dialog.findViewById(R.id.vehiclenumber);


        Button cancel = (Button) dialog.findViewById(R.id.cancel);
        Button proceed = (Button) dialog.findViewById(R.id.proceed);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // activity.finish();
                dialog.dismiss();
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!mobilenumber.getText().toString().isEmpty() && mobilenumber.getText().toString() != null
                        && !vehiclenumber.getText().toString().isEmpty() && vehiclenumber.getText().toString() != null) {
                    IdCardScanPojo scanData = new IdCardScanPojo();
                    scanData.setMobile_number(Long.parseLong(mobilenumber.getText().toString()));
                    scanData.setVehicle_number(vehiclenumber.getText().toString());

                    Intent intent = new Intent("SEARCHID");
                    intent.setPackage(activity.getPackageName());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("SEARCH_DATA", scanData.toJSON());
                    intent.putExtras(bundle);
                    activity.sendBroadcast(intent);
                    dialog.dismiss();
                } else {
                    showDialog(activity, "Please Enter Valid Mobile Number and Vehicle Number");
                }


            }
        });

        dialog.show();

    }

    public void showIdCard(final Activity activity, final IDCardServerObject object) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_custom_id);

        ImageLoader il = new ImageLoader(activity);

        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels);
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels);
        dialog.getWindow().setLayout(width, height);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView name = (TextView) dialog.findViewById(R.id.name);
        TextView id_card = (TextView) dialog.findViewById(R.id.id_card);
        ImageView id_photo = (ImageView) dialog.findViewById(R.id.id_photo);
        Button sms = dialog.findViewById(R.id.sms);

        il.DisplayImage(object.getImageUrl(), id_photo, null, null, false);


        name.setText(object.getDriverName());
        id_card.setText(object.getIdCardNumber());

        Button dialog_ok = (Button) dialog.findViewById(R.id.dialog_ok);

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage(object.getPhoneNumber(), null, object.getGenerateIDCardUrl_(), null, null);
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

    public void displayIdCardDetails(final Activity activity, final IDCardServerObject object) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_custom_id);

        ImageLoader il = new ImageLoader(activity);

        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels);
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels);
        dialog.getWindow().setLayout(width, height);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView name = (TextView) dialog.findViewById(R.id.name);
        TextView id_card = (TextView) dialog.findViewById(R.id.id_card);
        ImageView id_photo = (ImageView) dialog.findViewById(R.id.id_photo);
        Button sms = dialog.findViewById(R.id.sms);

        il.DisplayImage(object.getImageUrl(), id_photo, null, null, false);


        name.setText(object.getDriverName());
        id_card.setText(object.getIdCardNumber());

        Button dialog_ok = (Button) dialog.findViewById(R.id.dialog_ok);

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage(object.getPhoneNumber(), null, object.getGenerateIDCardUrl_(), null, null);
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
    public void displayIdCardDetailsComplete(final Activity activity, final IDCardOwnerServerVerify object, String userLocation) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_custom_id_complete);

        ImageLoader il = new ImageLoader(activity);

        final VehicleInOutTrans vehicleInOutTrans = new VehicleInOutTrans();


        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels);
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels);
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
        final EditText remarks = (EditText) dialog.findViewById(R.id.remarksnew);
        TextView id_card = (TextView) dialog.findViewById(R.id.id_card);
        ImageView id_photo = (ImageView) dialog.findViewById(R.id.compressed_image);
        Button verify = dialog.findViewById(R.id.verify);

        il.DisplayImage(object.getImageurl(), id_photo, null, null, false);

        String mobile = String.valueOf(object.getVehicleOwnerMobileNumber());

        name.setText(object.getVehicleOwnerName());
        mobilenumber.setText(mobile);
        passvalidfrom.setText(object.getIsValidFrom());
        passvalidto.setText(object.getIsValidUpto());
        aadhaarnumber.setText(object.getVehicleOwnerAadhaarNumber());
        vehicletype.setText(object.getVehicleTypeName());
        vehicle_owner_type.setText(object.getVehicleOwnerType());
        vehicle_number.setText(object.getVehicleOwnerVehicleNumber());
        chassis_number.setText(object.getVehicleOwnerChassisNumber());
        engine_number.setText(object.getVehicleOwnerEngineNumber());
        driving_licence_number.setText(object.getVehicleOwnerDrivingLicence());
        districtname.setText(object.getVehicleDistrictName());
        barriername.setText(object.getVehicleBarrierName());
        id_card.setText(object.getIdCardNumber());
        //Set Remarks


        vehicleInOutTrans.setActive(true);
        vehicleInOutTrans.setBarrierId(object.getVehicleBarrierId());
        vehicleInOutTrans.setCapturedBy(object.getDataEnteredBy());
        vehicleInOutTrans.setVehicleOwnerId(object.getVehicleOwnerId());

        if (!userLocation.isEmpty()) {
            try {
                String[] locations = userLocation.split(",");
                vehicleInOutTrans.setLatitude(Double.valueOf(locations[0]));
                vehicleInOutTrans.setLongitude(Double.valueOf(locations[1]));
            } catch (Exception ex) {
                showDialog(activity, "Unable to get the Location.");
            }
        } else {
            vehicleInOutTrans.setLatitude(Double.valueOf("0"));
            vehicleInOutTrans.setLongitude(Double.valueOf("0"));
        }

        Button dialog_ok = (Button) dialog.findViewById(R.id.back);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String remarks_ = remarks.getText().toString();
                vehicleInOutTrans.setRemarks(remarks_);

                //TODO  vehicleInOutTrans
                System.out.println("====Manual Entry" + vehicleInOutTrans.toJSON());
                Intent intent = new Intent("verifyData");
                intent.setPackage(activity.getPackageName());
                Bundle bundle = new Bundle();
                bundle.putSerializable("VEHICLE_TRANSACTION", vehicleInOutTrans.toJSON());
                intent.putExtras(bundle);
                activity.sendBroadcast(intent);
                dialog.dismiss();

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


    public void showVahanDetails(final Activity activity, final VehicleDetailsObject object) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_custom_vahan);

        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.95);
        dialog.getWindow().setLayout(width, height);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageLoader il = new ImageLoader(activity);



        TextView name = (TextView) dialog.findViewById(R.id.name);
        TextView status = (TextView) dialog.findViewById(R.id.status);
        TextView fitupto = (TextView) dialog.findViewById(R.id.fitupto);
        TextView registeredat = (TextView) dialog.findViewById(R.id.registeredat);
        TextView chassis = (TextView) dialog.findViewById(R.id.chassis);
        TextView engine = (TextView) dialog.findViewById(R.id.engine);
        TextView registration = (TextView) dialog.findViewById(R.id.registration);
        Button sms = dialog.findViewById(R.id.sms);

        name.setText(object.getRcOwnerName());
        status.setText(object.getRcStatus());
        fitupto.setText(object.getRcFitUpto());
        registeredat.setText(object.getRcRegisteredAt());
        chassis.setText(object.getRcChassisNo());
        engine.setText(object.getRcEngineNumber());

        registration.setText(object.getRcRegistrationNo());


        Button dialog_ok = (Button) dialog.findViewById(R.id.ok);



        dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void showSaarthiDetails(final Activity activity, final SaarthiObject object) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_custom_saarthi);

        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.95);
        dialog.getWindow().setLayout(width, height);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageLoader il = new ImageLoader(activity);


        TextView name = (TextView) dialog.findViewById(R.id.name);
        TextView status = (TextView) dialog.findViewById(R.id.status);
        TextView dlnum = (TextView) dialog.findViewById(R.id.dlnum);
        TextView issueauth = (TextView) dialog.findViewById(R.id.issueauth);
        TextView nontrans = (TextView) dialog.findViewById(R.id.nontrans);


        name.setText(object.getDlLicName());
        status.setText(object.getDlLicStatus());
        dlnum.setText(object.getDlLicNum());
        issueauth.setText(object.getIssuing_authority());
        nontrans.setText(object.getDlNonTransValidTill());



        Button dialog_ok = (Button) dialog.findViewById(R.id.ok);



        dialog_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

}
