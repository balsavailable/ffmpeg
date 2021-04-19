
package com.djax.ffmpeg.builder;

import com.djax.ffmpeg.wrapper.Companion;
import com.djax.ffmpeg.wrapper.Tracking;

public class TrackingBuilder {

	private Tracking tracking;

	public TrackingBuilder() {
		this.tracking = new Tracking();
	}
	
	public TrackingBuilder(Companion companion)	{
		companion.setTrackingEvents(new Tracking());
		this.tracking=companion.getTrackingEvents();
	}

	public TrackingBuilder setStart(String start) {
		this.tracking.setStart(start);
		return this;
	}

	public TrackingBuilder setFirstQuartile(String firstQuartile) {
		this.tracking.setFirstQuartile(firstQuartile);
		return this;
	}

	public TrackingBuilder setMidpoint(String midpoint) {
		this.tracking.setMidpoint(midpoint);
		return this;
	}

	public TrackingBuilder setThirdQuartile(String thirdQuartile) {
		this.tracking.setThirdQuartile(thirdQuartile);
		return this;
	}

	public TrackingBuilder setComplete(String complete) {
		this.tracking.setComplete(complete);
		return this;
	}

	public TrackingBuilder setMute(String mute) {
		this.tracking.setMute(mute);
		return this;
	}

	public TrackingBuilder setUnmute(String unmute) {
		this.tracking.setUnmute(unmute);
		return this;
	}

	public TrackingBuilder setRewind(String rewind) {
		this.tracking.setRewind(rewind);
		return this;
	}

	public TrackingBuilder setPause(String pause) {
		this.tracking.setPause(pause);
		return this;
	}

	public TrackingBuilder setResume(String resume) {
		this.tracking.setResume(resume);
		return this;
	}

	public TrackingBuilder setCreativeView(String creativeView) {
		this.tracking.setCreativeView(creativeView);
		return this;
	}

	public TrackingBuilder setFullscreen(String fullscreen) {
		this.tracking.setFullscreen(fullscreen);
		return this;
	}

	public TrackingBuilder setAcceptInvitationLinear(String acceptInvitationLinear) {
		this.tracking.setAcceptInvitationLinear(acceptInvitationLinear);
		return this;
	}

	public TrackingBuilder setCloseLinear(String closeLinear) {
		this.tracking.setCloseLinear(closeLinear);
		return this;
	}

	public TrackingBuilder setExitFullscreen(String exitFullscreen) {
		this.tracking.setExitFullscreen(exitFullscreen);
		return this;
	}

	public TrackingBuilder setShowAd(String showAd) {
		this.tracking.setShowAd(showAd);
		return this;
	}

	public TrackingBuilder setLoaded(String loaded) {
		this.tracking.setLoaded(loaded);
		return this;
	}

	public Tracking build() {
		return this.tracking;
	}

}
