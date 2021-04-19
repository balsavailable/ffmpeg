package com.djax.ffmpeg.wrapper;
import java.util.List;
public class Linear {

	private String Duration;
	private VideoClicks videoClicks;
    private List<Tracking> TrackingEvents;
	private List<MediaFile> mediaFiles;
	
	
    public String getDuration() {
		return Duration;
	}
	public void setDuration(String duration) {
		Duration = duration;
	}
	public List<Tracking> getTrackingEvents() {
		return TrackingEvents;
	}
	public void setTrackingEvents(List<Tracking> trackingEvents) {
		TrackingEvents = trackingEvents;
	}
	public List<MediaFile> getMediaFiles() {
		return mediaFiles;
	}
	public void setMediaFiles(List<MediaFile> mediaFiles) {
		this.mediaFiles = mediaFiles;
	}
	public VideoClicks getVideoClicks() {
		return videoClicks;
	}
	public void setVideoClicks(VideoClicks videoClicks) {
		this.videoClicks = videoClicks;
	}	
	
	
    
    
}
