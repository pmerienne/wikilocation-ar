package com.pmerienne.ar.wikilocation.client.model;

import java.io.Serializable;

import com.pmerienne.gwt.ar.device.Location;

public class Article implements Serializable {

	private static final long serialVersionUID = -7761576243271550072L;

	private String id;

	private String lat;

	private String lng;

	private String alt;

	private String type;

	private String title;

	private String url;

	private String mobileurl;

	private String distance;

	public Article() {
		super();
	}

	public Article(String id, String lat, String lng, String alt, String type, String title, String url, String mobileurl, String distance) {
		super();
		this.id = id;
		this.lat = lat;
		this.lng = lng;
		this.alt = alt;
		this.type = type;
		this.title = title;
		this.url = url;
		this.mobileurl = mobileurl;
		this.distance = distance;
	}

	public Location getLocation() {
		Location location;
		try {
			Double latitude = this.lat == null ? null : Double.valueOf(this.lat);
			Double longitude = this.lng == null ? null : Double.valueOf(this.lng);
			Double altitude = this.alt == null ? null : Double.valueOf(this.alt);

			location = new Location(latitude, longitude, altitude);
		} catch (Exception ex) {
			location = null;
		}
		return location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMobileurl() {
		return mobileurl;
	}

	public void setMobileurl(String mobileurl) {
		this.mobileurl = mobileurl;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", lat=" + lat + ", lng=" + lng + ", alt=" + alt + ", type=" + type + ", title=" + title + ", url=" + url + ", mobileurl="
				+ mobileurl + ", distance=" + distance + "]";
	}

}
