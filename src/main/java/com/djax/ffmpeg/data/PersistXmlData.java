package com.djax.ffmpeg.data;

import org.springframework.stereotype.Repository;

import com.djax.ffmpeg.dto.XmlData;


public interface PersistXmlData {

	public boolean persistXmlData(XmlData xmlData);
	
}
