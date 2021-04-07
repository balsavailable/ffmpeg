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

@Component("hls")
public class ConvertM3U8Util implements ConvertVideoUtil {
    @Value("${m3u8.ffmpegpath}")
    private String ffmpegpath;  // Directory of ffmpeg.exe

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
        return processM3U8(folderUrl, fileName, fileFullName, "1280x720", "10");
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

    /**
           * ffmpeg program to convert m3u8
     *
     * @param folderUrl
     * @param fileName
     * @param fileFullName
     * @return
     */
    private boolean processM3U8(String folderUrl, String fileName, String fileFullName) {
                 //Just write the execution statement here
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
        commend.add("hls");
        commend.add(folderUrl + fileFullName + ".m3u8");
        try {
            ProcessBuilder builder = new ProcessBuilder();//java
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
     * @param folderUrl
     * @param fileName
     * @param fileFullName
           * @param resolution
     * @param time                  Each ts duration
     * @return
     */
    private boolean processM3U8(String folderUrl, String fileName, String fileFullName, String resolution, String time) {
                 //Just write the execution statement here
        List commend = new java.util.ArrayList();
        commend.add(ffmpegpath);
        commend.add("-i");
        commend.add(folderUrl + fileName);
        commend.add("-profile:v");
        commend.add("baseline");
        commend.add("-level");
        commend.add("3.0");
        commend.add("-s");
        commend.add(resolution);
        commend.add("-start_number");
        commend.add("0");
        commend.add("-hls_time");
        commend.add(time);
        commend.add("-hls_list_size");
        commend.add("0");
        commend.add("-f");
        commend.add("hls");
        commend.add(folderUrl + fileFullName + ".m3u8");
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

    private boolean processM3U8(String ffmpegpath, String folderUrl, String fileName, String fileFullName, String resolution, String time) {
                 //Just write the execution statement here
        List commend = new java.util.ArrayList();
        commend.add(ffmpegpath);
        commend.add("-i");
        commend.add(folderUrl + fileName);
        commend.add("-profile:v");
        commend.add("baseline");
        commend.add("-level");
        commend.add("3.0");
        commend.add("-s");
        commend.add(resolution);
        commend.add("-start_number");
        commend.add("0");
        commend.add("-hls_time");
        commend.add(time);
        commend.add("-hls_list_size");
        commend.add("0");
        commend.add("-f");
        commend.add("hls");
        commend.add(folderUrl + fileFullName + ".m3u8");
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
           * Reasonably compress video
     *
           * @param folderUrl directory
           * @param fileName file name
           * @param newfileUrl new storage file address
     * @param resolution
     * @return
     */
    private boolean reasonableCompression(String folderUrl, String fileName, String newfileUrl, String resolution) {
                 //Just write the execution statement here
        //ffmpeg -i d:/123.mp4 -s 1280x720 -c:v libx264  -preset veryfast -crf 28 -c:a copy d:/xin8.mp4
        List commend = new java.util.ArrayList();
        commend.add(ffmpegpath);
        commend.add("-i");
        commend.add(folderUrl + fileName);
        commend.add("-s");
        commend.add(resolution);
        commend.add("-c:v");
        commend.add("libx264");
        commend.add("-preset");
        commend.add("veryfast");
        commend.add("-crf");
        commend.add("28");
        commend.add("-c:a");
        commend.add("copy");
        commend.add(newfileUrl);
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

    /***
           * Reasonably convert m3u8
     * @param folderUrl
     * @param fileName
     * @param fileFullName
     * @param resolution
     * @param time
     * @return
     */
    private boolean reasonableProcessM3U8(String folderUrl, String fileName, String fileFullName, String resolution, String time) {
                 //Just write the execution statement here
        //ffmpeg -i d:/123.mp4 -s 1280x720 -c:v libx264  -preset veryfast -crf 28 -c:a copy d:/xin8.mp4
        List commend = new ArrayList();
        commend.add(ffmpegpath);
        commend.add("-i");
        commend.add(folderUrl + fileName);
        commend.add("-preset");
        commend.add("veryfast");
        commend.add("-crf");
        commend.add("28");
        commend.add("-s");
        commend.add(resolution);
        commend.add("-start_number");
        commend.add("0");
        commend.add("-hls_time");
        commend.add(time);
        commend.add("-hls_list_size");
        commend.add("0");
        commend.add("-hls_segment_filename");
        commend.add(folderUrl + fileFullName + "-%d.ts");
        commend.add("-f");
        commend.add("hls");
        commend.add(folderUrl + fileFullName + ".m3u8");
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
           * Reasonably take a certain second of time as the picture
     *
           * @param folderUrl directory
           * @param fileName file
           * @param newfileUrl new file address
           * @param startTime starting time in seconds
           * @param resolution image resolution size
     * @return
     */
    private boolean reasonableImage(String folderUrl, String fileName, String newfileUrl, String startTime, String resolution) {
                 //Just write the execution statement here
        //ffmpeg -i d:/123.mp4 -s 1280x720 -c:v libx264  -preset veryfast -crf 28 -c:a copy d:/xin8.mp4
        List<String> cutpic = new ArrayList<String>();
        cutpic.add(ffmpegpath);
        cutpic.add("-i");
        cutpic.add(folderUrl + fileName);  // Same as above (the specified file can be either the file before conversion to flv format or the converted flv file)
        cutpic.add("-y");
        cutpic.add("-f");
        cutpic.add("image2");
        cutpic.add("-ss");  // Add parameter "-ss", which specifies the start time of interception
        cutpic.add(startTime);  // Add the start time as the second second
        cutpic.add("-t");  // Add parameter "-t", which specifies the duration
        cutpic.add("0.001");  // Add a duration of 1 millisecond
        cutpic.add("-s");  // Add the parameter "-s", which specifies the size of the captured image
        cutpic.add(resolution);  // Add the size of the captured image to 350*240
        cutpic.add(newfileUrl);  // Add the save path of the captured image
        try {
            //java
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(cutpic);
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
    private boolean reasonableProcessM3U8(String ffmpegpath,String folderUrl, String fileName, String fileFullName, String resolution, String time) {
                 //Just write the execution statement here
        //ffmpeg -i d:/123.mp4 -s 1280x720 -c:v libx264  -preset veryfast -crf 28 -c:a copy d:/xin8.mp4
        List commend = new ArrayList();
        commend.add(ffmpegpath);
        commend.add("-i");
        commend.add(folderUrl + fileName);
        commend.add("-preset");
        commend.add("veryfast");
        commend.add("-crf");
        commend.add("28");
        commend.add("-s");
        commend.add(resolution);
        commend.add("-start_number");
        commend.add("0");
        commend.add("-hls_time");
        commend.add(time);
        commend.add("-hls_list_size");
        commend.add("0");
        commend.add("-hls_segment_filename");
        commend.add(folderUrl + fileFullName + "-%d.ts");
        commend.add("-f");
        commend.add("hls");
        commend.add(folderUrl + fileFullName + ".m3u8");
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

    /**
           * Get video file duration
     *
     * @param music
     * @return
     */
    /*private String getVideoTime(File music) {
        System.out.println("~~~~~~~~~~~~~~~~ Start to get video duration ~~~~~~~~~~~~~~~~");
        Encoder encoder = new Encoder();
        try {
            MultimediaInfo m = encoder.getInfo(music);
            long ls = m.getDuration();
            int time = (int) ls / 1000;
            int ss = time % 60;
            int mm = time / 60 % 60;
            int hh = time / 60 / 60 % 24;

            String result = "";
            if (ss != 0) {
                String HH = hh == 0 ? "" : getTime(hh) + ":";
                result = HH + getTime(mm) + ":" + getTime(ss);
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "00:00";
    }*/

    /**
           * Convert time to string
     *
     * @param time
     * @return
     */
    private String getTime(int time) {
        if (time == 0) {
            return "00";
        } else {
            if (time <= 9) {
                return "0" + time;
            } else {
                return time + "";
            }
        }
    }

    /**
           * Get video size
     *
     * @param source
     * @return
     */
    private Long readVideoSize(File source) {
        FileChannel fc = null;
        Long size = 0L;
        try {
            @SuppressWarnings("resource")
            FileInputStream fis = new FileInputStream(source);
            fc = fis.getChannel();
            BigDecimal fileSize = new BigDecimal(fc.size());
            size = Long.parseLong(fileSize.toString());
            if (size > 0) {
                size = Long.parseLong(fileSize.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fc) {
                try {
                    fc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return size;
    }

    public static void main(String[] args) {
        /*ConvertM3U8Util util = new ConvertM3U8Util();
        boolean b = util.reasonableProcessM3U8("D:/ffmpeg/bin/ffmpeg.exe", "d:/video/", "xin7.mp4", "xin7","1280x720", "10");
        System.out.println(b);*/
        /*File file = new File("d:/123.mp4");
        String videoTime = util.getVideoTime(file);
        Long videoSize = util.readVideoSize(file);
        System.out.println(videoTime);
        System.out.println(videoSize);
                 //analyse file 
        MultimediaObject object = new MultimediaObject(file);
        ws.schild.jave.MultimediaInfo multimediaInfo = object.getInfo();
        long duration = multimediaInfo.getDuration();
        System.out.println(duration / 1000);
        System.out.println(multimediaInfo.getAudio().getBitRate());
        System.out.println(multimediaInfo.getFormat());
        Sys

         */
    }
}