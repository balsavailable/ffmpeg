package com.djax.ffmpeg.builder;

import com.djax.ffmpeg.wrapper.Extension;
import com.djax.ffmpeg.wrapper.extension.WaterFallExtension;

public class WaterFallExtensionBuilder extends ExtensionBuilder {

	protected WaterFallExtension waterFallExtension;

	public WaterFallExtensionBuilder(Extension extension) {
		this.waterFallExtension = new WaterFallExtension();
		extension.setWaterFallExtension(waterFallExtension);
	}

	public WaterFallExtensionBuilder setType(String type) {
		this.waterFallExtension.setType(type);
		return this;
	}

	public WaterFallExtensionBuilder setFallbackIndex(String fallbackIndex) {
		this.waterFallExtension.setFallbackIndex(fallbackIndex);
		return this;
	}


}
