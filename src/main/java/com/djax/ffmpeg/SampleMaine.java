package com.djax.ffmpeg;

import com.djax.ffmpeg.util.ConvertDASHUtil;

public class SampleMaine {
	public static void main(String[] args) {
		ConvertDASHUtil util=new ConvertDASHUtil();
		boolean done=util.convert("C:\\Users\\java\\Videos\\","sample.mp4");
		System.out.println(done);
	}
}
