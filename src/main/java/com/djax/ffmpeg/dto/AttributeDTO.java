package com.djax.ffmpeg.dto;

public class AttributeDTO {
	
	private long id;
	private String name;
	private String value;
	private long node_id;
	
	public AttributeDTO(long id, String name, String value, long node_id) {
		this.id = id;
		this.name = name;
		this.value = value;
		this.node_id = node_id;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public long getNode_id() {
		return node_id;
	}
	public void setNode_id(long node_id) {
		this.node_id = node_id;
	}
	
	
	
}
