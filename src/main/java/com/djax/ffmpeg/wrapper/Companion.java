package com.djax.ffmpeg.wrapper;

public class Companion 
{
	private String id;
	private String width;
	private String height;
	private StaticResource StaticResource;
    private Tracking trackingEvents;
    private String CompanionClickThrough;
	
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public StaticResource getStaticResource() {
		return StaticResource;
	}

	public void setStaticResource(StaticResource staticResource) {
		StaticResource = staticResource;
	}

	public Tracking getTrackingEvents() {
		return trackingEvents;
	}

	public void setTrackingEvents(Tracking trackingEvents) {
		this.trackingEvents = trackingEvents;
	}

	public String getCompanionClickThrough() {
		return CompanionClickThrough;
	}

	public void setCompanionClickThrough(String companionClickThrough) {
		CompanionClickThrough = companionClickThrough;
	}
	
	


}
