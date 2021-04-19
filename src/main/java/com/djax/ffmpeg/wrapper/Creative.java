package com.djax.ffmpeg.wrapper;

public class Creative 
{
	private String id;
	private String sequence;
	private Linear Linear;
    private Companion companion;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public Linear getLinear() {
		return Linear;
	}

	public void setLinear(Linear linear) {
		Linear = linear;
	}

	public Companion getCompanion() {
		return companion;
	}

	public void setCompanion(Companion companion) {
		this.companion = companion;
	}
}
