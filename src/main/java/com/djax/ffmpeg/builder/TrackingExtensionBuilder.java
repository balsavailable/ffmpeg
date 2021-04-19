package com.djax.ffmpeg.builder;

import com.djax.ffmpeg.wrapper.CustomTracking;
import com.djax.ffmpeg.wrapper.Extension;
import com.djax.ffmpeg.wrapper.extension.TrackingExtension;

public class TrackingExtensionBuilder extends ExtensionBuilder {

	private TrackingExtension trackingExtension;

	private CustomTrackingBuilder trackingBuilder;

	public TrackingExtensionBuilder() {

	}

	public TrackingExtensionBuilder(Extension extension) {
		trackingExtension = new TrackingExtension();
		trackingExtension.setCustomTracking(new CustomTracking());
		trackingBuilder = new CustomTrackingBuilder(trackingExtension.getCustomTracking());
		extension.setTrackingExtension(trackingExtension);

	}

	public CustomTrackingBuilder getCustomTrackingBuilder() {
		return trackingBuilder;
	}

	public TrackingExtensionBuilder setType(String type) {
		this.trackingExtension.setType(type);
		return this;
	}

	
}
