package com.djax.ffmpeg.wrapper.extension;

import com.djax.ffmpeg.wrapper.CustomTracking;

public class TrackingExtension {

	private String type;
	private CustomTracking customTracking;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CustomTracking getCustomTracking() {
		return customTracking;
	}

	public void setCustomTracking(CustomTracking customTracking) {
		this.customTracking = customTracking;
	}

}
