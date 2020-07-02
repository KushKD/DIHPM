package com.dit.hp.appleseasonid.enums;

/**
 * @author Kush.Dhawan
 * @project HPePass
 * @Time 03, 05 , 2020
 */
public enum TaskType {
    GET_DISTRICT(1),
    VERIFY_DETAILS(2),
    MANUAL_FORM_UPLOAD(3),
    GET_BARRIERS(4),
    UPLOAD_SCANNED_PASS(5),
    GET_VEHICLE_TYPE(6),
    GET_VEHICLE_TYPE_USER(7),
    UPLOAD_DATA(8),
    GET_OTP(9),
    VEREIFY_OTP(10),
    SCAN_ID_CARD(11),
    SEARCH_ID(12);

    int value; private TaskType(int value) { this.value = value; }
}
