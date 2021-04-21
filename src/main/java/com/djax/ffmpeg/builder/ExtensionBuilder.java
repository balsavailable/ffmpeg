package com.djax.ffmpeg.builder;

import com.djax.ffmpeg.wrapper.Extension;
import com.djax.ffmpeg.wrapper.InLine;

public class ExtensionBuilder extends InLineBuilder{

	private Extension extension;
	private GeoExtensionBuilder geoExtensionBuilder;
	private MetricExtensionBuilder metricExtensionBuilder;
	private WaterFallExtensionBuilder waterFallExtensionBuilder;
	private TrackingExtensionBuilder showAdTrackingExtensionBuilder;
	private TrackingExtensionBuilder videoAdTrackingExtensionBuilder;

	public ExtensionBuilder() {
	
	}
	
	public ExtensionBuilder(InLine inline) {
		inline.setExtension(new Extension());
		this.extension = inline.getExtension();
		this.geoExtensionBuilder = new GeoExtensionBuilder(extension);
		this.metricExtensionBuilder = new MetricExtensionBuilder(extension);
		this.showAdTrackingExtensionBuilder = new TrackingExtensionBuilder(extension,"showAdTrack");
		this.videoAdTrackingExtensionBuilder =new TrackingExtensionBuilder(extension,"videoAdTrack");
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

	public TrackingExtensionBuilder getShowAdTrackingExtensionBuilder() {
		return showAdTrackingExtensionBuilder;
	}


	public TrackingExtensionBuilder getVideoAdTrackingExtensionBuilder() {
		return videoAdTrackingExtensionBuilder;
	}


	
	

}
