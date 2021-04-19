package com.djax.ffmpeg.wrapper;

import java.util.List;

public class InLine {
	private String AdSystem;
	private String AdTitle;
	private String Description;
	private String Error;
	private String Impression;
	private List<Creative> Creatives;
    private Extension extension;
    
	public String getAdSystem() {
		return AdSystem;
	}

	public void setAdSystem(String adSystem) {
		AdSystem = adSystem;
	}

	public String getAdTitle() {
		return AdTitle;
	}

	public void setAdTitle(String adTitle) {
		AdTitle = adTitle;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getError() {
		return Error;
	}

	public void setError(String error) {
		Error = error;
	}

	public String getImpression() {
		return Impression;
	}

	public void setImpression(String impression) {
		Impression = impression;
	}

	public List<Creative> getCreatives() {
		return Creatives;
	}

	public void setCreatives(List<Creative> creatives) {
		Creatives = creatives;
	}

	public Extension getExtension() {
		return extension;
	}

	public void setExtension(Extension extension) {
		this.extension = extension;
	}
	
	


}
