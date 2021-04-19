package com.djax.ffmpeg.builder;

import com.djax.ffmpeg.wrapper.Ad;

public class AdBuilder {
	private Ad ad;
	private InLineBuilder inLineBuilder;

	public AdBuilder() {
		
	}
	
	public AdBuilder(Ad ad)
	{
		this.ad = ad;
		this.inLineBuilder = new InLineBuilder(ad);
	}

	public AdBuilder setId(String id) {
		this.ad.setId(id);
		return this;
	}

	public InLineBuilder getInlineBuilder() {
		return this.inLineBuilder;
	}

	public Ad build() {
		return this.ad;
	}
}
