package com.djax.ffmpeg.builder;

import com.djax.ffmpeg.wrapper.Extension;
import com.djax.ffmpeg.wrapper.InLine;

public class ExtensionBuilder extends InLineBuilder{

	private Extension extension;
	private GeoExtensionBuilder geoExtensionBuilder;
	private MetricExtensionBuilder metricExtensionBuilder;
	private WaterFallExtensionBuilder waterFallExtensionBuilder;
	private TrackingExtensionBuilder trackingExtensionBuilder;

	public ExtensionBuilder() {
	
	}
	
	public ExtensionBuilder(InLine inline) {
		inline.setExtension(new Extension());
		this.extension = inline.getExtension();
		this.geoExtensionBuilder = new GeoExtensionBuilder(extension);
		this.metricExtensionBuilder = new MetricExtensionBuilder(extension);
		this.trackingExtensionBuilder = new TrackingExtensionBuilder(extension);
		this.waterFallExtensionBuilder = new WaterFallExtensionBuilder(extension);
	}

	public GeoExtensionBuilder getGeoExtensionBuilder() {
		return geoExtensionBuilder;
	}

	public MetricExtensionBuilder getMetricExtensionBuilder() {
		return metricExtensionBuilder;
	}

	public WaterFallExtensionBuilder getWaterFallExtensionBuilder() {
		return waterFallExtensionBuilder;
	}

	public TrackingExtensionBuilder getTrackingExtensionBuilder() {
		return trackingExtensionBuilder;
	}
	
	

}
