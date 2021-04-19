package com.djax.ffmpeg.wrapper;

import com.djax.ffmpeg.wrapper.extension.GeoExtension;
import com.djax.ffmpeg.wrapper.extension.MetricExtension;
import com.djax.ffmpeg.wrapper.extension.TrackingExtension;
import com.djax.ffmpeg.wrapper.extension.WaterFallExtension;

public class Extension {
	
	private GeoExtension geoExtension;
	private MetricExtension metricExtension;
	private TrackingExtension trackingExtension;
	private WaterFallExtension waterFallExtension;
	
	
	public GeoExtension getGeoExtension() {
		return geoExtension;
	}
	public void setGeoExtension(GeoExtension geoExtension) {
		this.geoExtension = geoExtension;
	}
	public MetricExtension getMetricExtension() {
		return metricExtension;
	}
	public void setMetricExtension(MetricExtension metricExtension) {
		this.metricExtension = metricExtension;
	}
	public TrackingExtension getTrackingExtension() {
		return trackingExtension;
	}
	public void setTrackingExtension(TrackingExtension trackingExtension) {
		this.trackingExtension = trackingExtension;
	}
	public WaterFallExtension getWaterFallExtension() {
		return waterFallExtension;
	}
	public void setWaterFallExtension(WaterFallExtension waterFallExtension) {
		this.waterFallExtension = waterFallExtension;
	}
	
	
	
}
