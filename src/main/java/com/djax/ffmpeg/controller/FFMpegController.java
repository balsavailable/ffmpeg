package com.djax.ffmpeg.controller;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.djax.ffmpeg.util.ConvertM3U8Util;
import com.djax.ffmpeg.util.ConvertVideoUtil;
import com.djax.ffmpeg.util.XmlParserUtil2;

@RestController
public class FFMpegController {

	@Autowired
	@Qualifier("hls")
	ConvertVideoUtil convertM3U8Util;

	@Autowired
	@Qualifier("dash")
	ConvertVideoUtil convertDashUtil;

	@Autowired
	XmlParserUtil2 xmlParserUtil2;
	
	
	@PostMapping("/converToHLS")
	public boolean convertToM3U8(@RequestBody Map<String,String> req)
	{
		try
		{
			return this.convertM3U8Util.convert(req.get("folderUrl"),req.get("filename"));
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	
	@PostMapping("/converToDASH")
	public boolean convertToDash(@RequestBody Map<String,String> req)
	{
		try
		{
			return this.convertDashUtil.convert(req.get("folderUrl"),req.get("filename"));
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	
	@GetMapping("/test")
    public void saveXmlData()
    {
	   xmlParserUtil2.main(null);
    }

	
}
