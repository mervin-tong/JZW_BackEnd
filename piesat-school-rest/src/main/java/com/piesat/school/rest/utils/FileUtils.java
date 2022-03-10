package com.piesat.school.rest.utils;

import java.io.*;
import java.text.DecimalFormat;

public class FileUtils {
    //文件名正则校验
    public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";

    public static void writeBytes(String filePath, OutputStream os) {
        FileInputStream fi = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException(filePath);
            }
            fi = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fi.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(os != null) {
                try {
                    os.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fi != null) {
                try {
                    fi.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 文件数据量获取
     *
     *
     */
    public static String getAmount(long size) {
        StringBuffer bytes = new StringBuffer();
        DecimalFormat format = new DecimalFormat("###.0");
        if (size >= 1024 * 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0 * 1024.0));
            bytes.append(format.format(i)).append("GB");
        }
        else if (size >= 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0));
            bytes.append(format.format(i)).append("MB");
        }
        else if (size >= 1024) {
            double i = (size / (1024.0));
            bytes.append(format.format(i)).append("KB");
        }
        else if (size < 1024) {
            if (size <= 0) {
                bytes.append("0B");
            }
            else {
                bytes.append((int) size).append("B");
            }
        }
        return bytes.toString();
    }

    /**
     * 删除文件
     * @param filePath 文件路径
     * @return 是否成功
     */
    public static boolean deleteFile(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 文件名校验
     * @param fileName 文件名
     * @return true 正常， false 非法
     */
    public static boolean isValidName(String fileName) {
        return fileName.matches(FILENAME_PATTERN);
    }

//    /**
//     * 下载文件名重新编码
//     *
//     * @param request 请求对象
//     * @param fileName 文件名
//     * @return 编码后的文件名
//     */
//    public static String setFileDownloadHeader(HttpServletRequest request, String fileName)
//            throws UnsupportedEncodingException
//    {
//        final String agent = request.getHeader("USER-AGENT");
//        String filename = fileName;
//        if (agent.contains("MSIE"))
//        {
//            // IE浏览器
//            filename = URLEncoder.encode(filename, "utf-8");
//            filename = filename.replace("+", " ");
//        }
//        else if (agent.contains("Firefox"))
//        {
//            // 火狐浏览器
//            filename = new String(fileName.getBytes(), "ISO8859-1");
//        }
//        else if (agent.contains("Chrome"))
//        {
//            // google浏览器
//            filename = URLEncoder.encode(filename, "utf-8");
//        }
//        else
//        {
//            // 其它浏览器
//            filename = URLEncoder.encode(filename, "utf-8");
//        }
//        return filename;
//    }
}
