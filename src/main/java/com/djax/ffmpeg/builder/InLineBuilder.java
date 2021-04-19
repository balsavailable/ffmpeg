package com.djax.ffmpeg.builder;

import java.util.ArrayList;

import com.djax.ffmpeg.wrapper.Ad;
import com.djax.ffmpeg.wrapper.Creative;
import com.djax.ffmpeg.wrapper.InLine;

public class InLineBuilder extends AdBuilder {

	private InLine inline;
	private ExtensionBuilder extensionBuilder;
	
	public InLineBuilder() {
	
	}

	public InLineBuilder(Ad ad) {
		ad.setInLine(new InLine());
		inline = ad.getInLine();
		inline.setCreatives(new ArrayList<>());
		extensionBuilder=new ExtensionBuilder(inline);
	}

	public InLineBuilder setAdSystem(String adSystem) {
		this.inline.setAdSystem(adSystem);
		return this;
	}

	public InLineBuilder setAdTitle(String adTitle) {
		this.inline.setAdTitle(adTitle);
		return this;
	}

	public InLineBuilder setDescription(String description) {
		this.inline.setDescription(description);
		return this;
	}

	public InLineBuilder setError(String error) {
		this.inline.setError(error);
		return this;
	}
	
	public InLineBuilder setImpression(String impression) {
		this.inline.setImpression(impression);
		return this;
	}

	public InLineBuilder addCreatives(Creative creative) {
		this.inline.getCreatives().add(creative);
		return this;
	}

	public ExtensionBuilder getExtensionBuilder() {
		return this.extensionBuilder;	
	}

}
