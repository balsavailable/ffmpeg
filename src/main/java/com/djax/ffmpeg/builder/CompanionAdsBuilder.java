package com.djax.ffmpeg.builder;

import com.djax.ffmpeg.wrapper.Companion;
import com.djax.ffmpeg.wrapper.Creative;
import com.djax.ffmpeg.wrapper.Tracking;

public class CompanionAdsBuilder extends CreativeBuilder {

	private Companion companionAds;
	private StaticResourceBuilder staticResourceBuilder;

	public CompanionAdsBuilder() {

	}

	public CompanionAdsBuilder(Creative creative) {
		creative.setCompanion(new Companion());
		this.companionAds = creative.getCompanion();
		this.staticResourceBuilder = new StaticResourceBuilder(this.companionAds);
	}

	public CompanionAdsBuilder setId(String id) {
		this.companionAds.setId(id);
		return this;
	}

	public CompanionAdsBuilder setWidth(String width) {
		this.companionAds.setWidth(width);
		return this;
	}

	public CompanionAdsBuilder setHeight(String height) {
		this.companionAds.setHeight(height);
		return this;
	}

	public StaticResourceBuilder getStaticResourceBuilder() {
		return this.staticResourceBuilder;
	}

	public CompanionAdsBuilder setTrackingEvents(Tracking trackingEvents) {
		this.companionAds.setTrackingEvents(trackingEvents);
		return this;
	}

	public CompanionAdsBuilder setCompanionClickThrough(String companionClickThrough) {
		this.companionAds.setCompanionClickThrough(companionClickThrough);
		return this;
	}

}
