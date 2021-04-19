package com.djax.ffmpeg.builder;

import com.djax.ffmpeg.wrapper.CustomTracking;
import com.djax.ffmpeg.wrapper.Tracking;

public class CustomTrackingBuilder extends TrackingExtensionBuilder{
	
	private Tracking tracking;
	
	public CustomTrackingBuilder(CustomTracking customTracking) {
		this.tracking = new Tracking();
		customTracking.setTracking(tracking);
	}

	public CustomTrackingBuilder setStart(String start) {
		this.tracking.setStart(start);
		return this;
	}

	public CustomTrackingBuilder setFirstQuartile(String firstQuartile) {
		this.tracking.setFirstQuartile(firstQuartile);
		return this;
	}

	public CustomTrackingBuilder setMidpoint(String midpoint) {
		this.tracking.setMidpoint(midpoint);
		return this;
	}

	public CustomTrackingBuilder setThirdQuartile(String thirdQuartile) {
		this.tracking.setThirdQuartile(thirdQuartile);
		return this;
	}

	public CustomTrackingBuilder setComplete(String complete) {
		this.tracking.setComplete(complete);
		return this;
	}

	public CustomTrackingBuilder setMute(String mute) {
		this.tracking.setMute(mute);
		return this;
	}

	public CustomTrackingBuilder setUnmute(String unmute) {
		this.tracking.setUnmute(unmute);
		return this;
	}

	public CustomTrackingBuilder setRewind(String rewind) {
		this.tracking.setRewind(rewind);
		return this;
	}

	public CustomTrackingBuilder setPause(String pause) {
		this.tracking.setPause(pause);
		return this;
	}

	public CustomTrackingBuilder setResume(String resume) {
		this.tracking.setResume(resume);
		return this;
	}

	public CustomTrackingBuilder setCreativeView(String creativeView) {
		this.tracking.setCreativeView(creativeView);
		return this;
	}

	public CustomTrackingBuilder setFullscreen(String fullscreen) {
		this.tracking.setFullscreen(fullscreen);
		return this;
	}

	public CustomTrackingBuilder setAcceptInvitationLinear(String acceptInvitationLinear) {
		this.tracking.setAcceptInvitationLinear(acceptInvitationLinear);
		return this;
	}

	public CustomTrackingBuilder setCloseLinear(String closeLinear) {
		this.tracking.setCloseLinear(closeLinear);
		return this;
	}

	public CustomTrackingBuilder setExitFullscreen(String exitFullscreen) {
		this.tracking.setExitFullscreen(exitFullscreen);
		return this;
	}

	public CustomTrackingBuilder setShowAd(String showAd) {
		this.tracking.setShowAd(showAd);
		return this;
	}

	public CustomTrackingBuilder setLoaded(String loaded) {
		this.tracking.setLoaded(loaded);
		return this;
	}

}
