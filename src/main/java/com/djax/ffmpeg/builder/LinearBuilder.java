package com.djax.ffmpeg.builder;

import java.util.ArrayList;

import com.djax.ffmpeg.wrapper.Creative;
import com.djax.ffmpeg.wrapper.Linear;
import com.djax.ffmpeg.wrapper.MediaFile;
import com.djax.ffmpeg.wrapper.Tracking;

public class LinearBuilder extends CreativeBuilder {

	private Linear linear;
	private VideoClicksBuilder builder;

	public LinearBuilder(Creative creative) {
		linear = creative.getLinear();
		linear.setTrackingEvents(new ArrayList<>());
		linear.setMediaFiles(new ArrayList<>());
		builder = new VideoClicksBuilder(linear);
	}

	public LinearBuilder() {
	}

	public LinearBuilder setDuration(String duration) {
		linear.setDuration(duration);
		return this;
	}

	public LinearBuilder addTrackingEvent(Tracking trackingEvent) {
		linear.getTrackingEvents().add(trackingEvent);
		return this;
	}

	public LinearBuilder addMediaFile(MediaFile mediaFile) {
		linear.getMediaFiles().add(mediaFile);
		return this;
	}

	public VideoClicksBuilder getVideoClickBuilder() {
		return builder;
	}

}
