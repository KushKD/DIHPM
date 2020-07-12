package com.dit.hp.appleseasonid.Modal;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;


public class VehicleInOutTrans implements Serializable {


	private Integer vehicleOwnerId;
	private Integer capturedBy;
	private Integer barrierId;
	private Double latitude;
	private Double longitude;
	private boolean isActive;
	private String remarks;


	public Integer getVehicleOwnerId() {
		return vehicleOwnerId;
	}

	public void setVehicleOwnerId(Integer vehicleOwnerId) {
		this.vehicleOwnerId = vehicleOwnerId;
	}

	public Integer getCapturedBy() {
		return capturedBy;
	}

	public void setCapturedBy(Integer capturedBy) {
		this.capturedBy = capturedBy;
	}

	public Integer getBarrierId() {
		return barrierId;
	}

	public void setBarrierId(Integer barrierId) {
		this.barrierId = barrierId;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "VehicleInOutTrans{" +
				", vehicleOwnerId='" + vehicleOwnerId + '\'' +
				", capturedBy=" + capturedBy +
				", barrierId=" + barrierId +
				", latitude=" + latitude +
				", longitude=" + longitude +
				", isActive=" + isActive +
				", remarks='" + remarks + '\'' +
				'}';
	}

	public String toJSON() {

		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("vehicleOwnerId", getVehicleOwnerId());
			jsonObject.put("capturedBy", getCapturedBy());
			jsonObject.put("barrierId", getBarrierId());
			jsonObject.put("latitude", getLatitude());
			jsonObject.put("longitude", getLongitude());
			jsonObject.put("remarks", getRemarks());
			jsonObject.put("active", true);


			return jsonObject.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
}
