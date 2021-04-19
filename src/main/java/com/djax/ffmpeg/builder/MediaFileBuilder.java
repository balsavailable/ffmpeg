package com.djax.ffmpeg.builder;

import com.djax.ffmpeg.wrapper.MediaFile;

public class MediaFileBuilder {

	private MediaFile mediaFile;

	public MediaFileBuilder() {
		mediaFile = new MediaFile();
	}

	public MediaFileBuilder setId(String id) {
		this.mediaFile.setId(id);
		return this;
	}

	public MediaFileBuilder setDelivery(String delivery) {
		this.mediaFile.setDelivery(delivery);
		return this;

	}

	public MediaFileBuilder setWidth(String width) {
		this.mediaFile.setWidth(width);
		return this;
	}

	public MediaFileBuilder setHeight(String height) {
		this.mediaFile.setHeight(height);
		return this;
	}

	public MediaFileBuilder setType(String type) {
		this.mediaFile.setType(type);
		return this;
	}

	public MediaFileBuilder setBitrate(String bitrate) {
		this.mediaFile.setBitrate(bitrate);
		return this;
	}

	public MediaFileBuilder setScalable(String scalable) {
		this.mediaFile.setScalable(scalable);
		return this;
	}

	public MediaFileBuilder setMaintainAspectRatio(String maintainAspectRatio) {
		this.mediaFile.setMaintainAspectRatio(maintainAspectRatio);
		return this;
	}

	public MediaFileBuilder setMediaFile(String mediaFile) {
		this.mediaFile.setMediaFile(mediaFile);
		return this;
	}

	public MediaFile build() {
		return this.mediaFile;
	}

}
