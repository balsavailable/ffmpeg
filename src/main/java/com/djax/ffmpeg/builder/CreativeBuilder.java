package com.djax.ffmpeg.builder;

import com.djax.ffmpeg.wrapper.Creative;
import com.djax.ffmpeg.wrapper.Linear;

public class CreativeBuilder {

	private Creative creative;

	private LinearBuilder linearBuilder;
	private CompanionAdsBuilder companionAdsBuilder;

	public CreativeBuilder() {

	}

	public CreativeBuilder(Creative creative) {
        this.creative=creative;
		linearBuilder = new LinearBuilder(creative);
		companionAdsBuilder = new CompanionAdsBuilder(creative);
	}

	public CreativeBuilder setId(String id) {
		this.creative.setId(id);
		return this;
	}

	public CreativeBuilder setSequence(String sequence) {
		this.creative.setSequence(sequence);
		return this;
	}

	public LinearBuilder getLinearBuilder() {
		return this.linearBuilder;
	}

	public CompanionAdsBuilder getCompanionBuilder() {
		return this.companionAdsBuilder;
	}

	public Creative build() {
		return this.creative;
	}

}
