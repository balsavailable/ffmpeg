package com.djax.ffmpeg.data;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.djax.ffmpeg.dto.XmlData;

@Repository
public class PersistXmlDataImpl implements PersistXmlData{

	
     private DataSource dataSource;
 
	 private JdbcTemplate jdbcTemplate;
     
    

	public PersistXmlDataImpl(DataSource dataSource) {
		this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	@Override
	public boolean persistXmlData(XmlData xmlData) {	
		String sqlQuery="INSERT INTO xml_data(type,jsonData) VALUES(?,?)";
		int i=jdbcTemplate.update(sqlQuery,xmlData.getType(),xmlData.getJson());
	//	System.out.println(i);
		return i!=0;
	}

}
