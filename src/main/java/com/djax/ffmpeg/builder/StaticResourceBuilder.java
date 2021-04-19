package com.djax.ffmpeg.builder;

import com.djax.ffmpeg.wrapper.Companion;
import com.djax.ffmpeg.wrapper.StaticResource;

public class StaticResourceBuilder extends CompanionAdsBuilder {

	private StaticResource staticResource;

	public StaticResourceBuilder(Companion companion) {

		companion.setStaticResource(new StaticResource());
		this.staticResource = companion.getStaticResource();
	}

	public StaticResourceBuilder setCreativeType(String creativeType) {
		this.staticResource.setCreativeType(creativeType);
		return this;
	}

	public StaticResourceBuilder setText(String text) {
		this.staticResource.setText(text);
		return this;
	}

}
