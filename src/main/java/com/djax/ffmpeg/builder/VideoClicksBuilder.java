package com.djax.ffmpeg.builder;

import com.djax.ffmpeg.wrapper.ClickThrough;
import com.djax.ffmpeg.wrapper.Creative;
import com.djax.ffmpeg.wrapper.Linear;
import com.djax.ffmpeg.wrapper.VideoClicks;

public class VideoClicksBuilder extends LinearBuilder {

	private VideoClicks videoClicks;

	private ClickThrough clickThrough;

	
	
	public VideoClicksBuilder() {

	}

	public VideoClicksBuilder(Linear linear) {
		videoClicks = linear.getVideoClicks();
		clickThrough = new ClickThrough();
		videoClicks.setClickThrough(clickThrough);
	}

	public VideoClicksBuilder setId(String id) {
		this.clickThrough.setId(id);
		return this;
	}

	public VideoClicksBuilder setText(String text) {
		this.clickThrough.setText(text);
		return this;
	}
	
	
	
}
