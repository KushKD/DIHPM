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
    GET_VEHICLE_TYPE_USER(7);

    int value; private TaskType(int value) { this.value = value; }
}
