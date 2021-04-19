package com.djax.ffmpeg.wrapper.extension;

public class GeoExtension {
private String type;
private String Country;
private String Bandwidth;
private String BandwidthKbps;
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getCountry() {
	return Country;
}
public void setCountry(String country) {
	Country = country;
}
public String getBandwidth() {
	return Bandwidth;
}
public void setBandwidth(String bandwidth) {
	Bandwidth = bandwidth;
}
public String getBandwidthKbps() {
	return BandwidthKbps;
}
public void setBandwidthKbps(String bandwidthKbps) {
	BandwidthKbps = bandwidthKbps;
}


}
