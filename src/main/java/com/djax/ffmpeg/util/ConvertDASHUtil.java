package com.djax.ffmpeg.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("dash")
public class ConvertDASHUtil implements ConvertVideoUtil{
    @Value("${m3u8.ffmpegpath}")
    private String ffmpegpath="D:\\myFiles\\ffmpeg\\bin\\ffmpeg.exe";  // Directory of ffmpeg.exe

    public boolean convert(String folderUrl, String fileName) {
        if (!checkfile(folderUrl + fileName)) {
            System.out.println("file does not exist!");
            return false;
        }

                 //Verify file suffix
        String suffix = StringUtils.substringAfter(fileName, ".");
        String fileFullName = StringUtils.substringBefore(fileName, ".");
        if (!validFileType(suffix)) {
            return false;
        }

        //return  processM3U8(folderUrl,fileName,fileFullName);
        return convertToDASH(folderUrl, fileName, fileFullName, "1280x720", "10");
    }

    /**
           * Verify upload file suffix
     *
     * @param type
     * @return
     */
    private boolean validFileType(String type) {
        if ("mp4".equals(type)) {
            return true;
        }
        return false;
    }

    /**
           * Verify whether the file format
     *
     * @param path
     * @return
     */
    private boolean checkfile(String path) {
        File file = new File(path);
        if (!file.isFile()) {
            return false;
        } else {
            return true;
        }
    }

         // Formats that ffmpeg can parse: (asx, asf, mpg, wmv, 3gp, mp4, mov, avi, flv, etc.)

    private boolean convertToDASH(String folderUrl, String fileName, String fileFullName, String resolution, String time) {
                 //Just write the execution statement here        
    /**	ffmpeg -i main.mp4 
        -profile:v baseline -level 3.0 -s 640x360 
        -start_number 0 -hls_time 10 -hls_list_size 0 
        -f dash main_mpd_output/main.mpd 
   
   
   ffmpeg -i /home/djaxtech/Documents/test/videoplayback.mp4 -map 0:v:0
    -map 0:a:0 -map 0:v:0 -map 0:a:0 -b:v:0 250k -filter:v:0 "scale=-2:240"
     -profile:v:0 baseline
      -b:v:1 750k -filter:v:1 "scale=-2:480" -profile:v:1 main
       -use_timeline 1 -use_template 1 -window_size 5 -adaptation_sets 
       "id=0,streams=v id=1,streams=a" -f dash /home/djaxtech/Documents/test/outputDash/test.mpd
   
 */
    	 List commend = new java.util.ArrayList();
         commend.add(ffmpegpath);
         commend.add("-i");
         commend.add(folderUrl + fileName);
         commend.add("-c:v");
         commend.add("libx264");
         commend.add("-hls_time");
         commend.add("20");
         commend.add("-hls_list_size");
         commend.add("0");
         commend.add("-c:a");
         commend.add("aac");
         commend.add("-strict");
         commend.add("-2");
         commend.add("-f");
         commend.add("dash");
         commend.add(folderUrl + fileFullName + ".mpd");


    	
//    	List commend = new java.util.ArrayList();
//        commend.add(ffmpegpath);
//        commend.add("-i");
//        commend.add(folderUrl + fileName);
//        commend.add("-map");
//        commend.add("0:v:0");
//        commend.add("-map");
//        commend.add("0:a:0");
//        commend.add("-map");
//        commend.add("0:v:0");
//        commend.add("-map");
//        commend.add("0:a:0");
//        commend.add("-b:v:0");
//        commend.add("250k");
//        commend.add("-filter:v:0");
//        commend.add("'scale=-2:240'");
//        commend.add("-profile:v:0");
//        commend.add("baseline");
//        commend.add("-b:v:1");
//        commend.add("750k");
//        commend.add("-filter:v:1");
//        commend.add("'scale=-2:480'");
//        commend.add("-profile:v:1");
//        commend.add("main");
//        commend.add("-use_timeline");
//        commend.add("main");
//        commend.add("1");
//        commend.add("-use_template");
//        commend.add("1");
//        commend.add("-window_size");
//        commend.add("5");
//        commend.add("-adaptation_sets");
//        commend.add("'id=0,streams=v id=1,streams=a'");
//        commend.add("-f");
//        commend.add("dash");
//        commend.add(folderUrl + fileFullName + ".mpd");
        try {
            //java
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commend);
            Process p = builder.start();
            int i = doWaitFor(p);
            System.out.println("------>" + i);
            p.destroy();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
           * Monitor the running process of ffmpeg
     *
     * @param p
     * @return
     */
    public int doWaitFor(Process p) {
        InputStream in = null;
        InputStream err = null;
        int exitValue = -1; // returned to caller when p is finished
        try {
            System.out.println("comeing");
            in = p.getInputStream();
            err = p.getErrorStream();
            boolean finished = false; // Set to true when p is finished

            while (!finished) {
                try {
                    while (in.available() > 0) {
                        Character c = new Character((char) in.read());
                        System.out.print(c);
                    }
                    while (err.available() > 0) {
                        Character c = new Character((char) err.read());
                        System.out.print(c);
                    }

                    exitValue = p.exitValue();
                    finished = true;

                } catch (IllegalThreadStateException e) {
                    Thread.currentThread().sleep(500);
                }
            }
        } catch (Exception e) {
            System.err.println("doWaitFor();: unexpected exception - "
                    + e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            if (err != null) {
                try {
                    err.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return exitValue;
    }

    
    
}