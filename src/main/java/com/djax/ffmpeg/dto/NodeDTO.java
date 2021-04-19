package com.djax.ffmpeg.dto;

public class NodeDTO {
	private long id;
	private String name;
	private String value;
	private long parentId;
	private short isParent;

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

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public short getIsParent() {
		return isParent;
	}

	public void setIsParent(short isParent) {
		this.isParent = isParent;
	}

	public NodeDTO() {

	}

	public NodeDTO(long id, String name, String value, long parentId, short isParent) {
		this.id = id;
		this.name = name;
		this.value = value;
		this.parentId = parentId;
		this.isParent = isParent;
	}
}
