package com.djax.ffmpeg.builder;

import com.djax.ffmpeg.wrapper.Extension;
import com.djax.ffmpeg.wrapper.extension.GeoExtension;

public class GeoExtensionBuilder extends ExtensionBuilder{

	private GeoExtension geoExtension;

	
	
	
	public GeoExtensionBuilder(Extension extension) {
		this.geoExtension = new GeoExtension();
		extension.setGeoExtension(geoExtension);
	}

	public GeoExtensionBuilder setType(String type) {
		this.geoExtension.setType(type);
		return this;
	}

	public GeoExtensionBuilder setCountry(String country) {
		this.geoExtension.setCountry(country);
		return this;
	}

	public GeoExtensionBuilder setBandwidth(String bandwidth) {
		this.geoExtension.setBandwidth(bandwidth);
		return this;
	}

	public GeoExtensionBuilder setBandwidthKbps(String bandwidthKbps) {
		this.geoExtension.setBandwidthKbps(bandwidthKbps);
		return this;
	}
	
	

}
