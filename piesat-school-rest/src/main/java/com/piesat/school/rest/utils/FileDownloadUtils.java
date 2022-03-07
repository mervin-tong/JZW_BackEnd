package com.piesat.school.rest.utils;

import com.piesat.school.datainf.iservice.IRDataInfService;
import com.piesat.school.datainf.vto.DataInfVTO;
import org.apache.commons.io.IOUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author suweipeng
 * @data 2022/3/7 17:55
 */
public class FileDownloadUtils {
    public static Boolean download(DataInfVTO datainf, HttpServletResponse response) throws IOException {
        try {
            File file = new File(datainf.getContent());
            //获取文件输入流
            FileInputStream is = new FileInputStream(file);
            //附件下载
            response.setHeader("Content-Disposition","attachment; fileName=" + URLEncoder.encode(file.getName(),"UTF-8"));
            //获取相应输出流
            ServletOutputStream os = response.getOutputStream();
            //文件拷贝
            IOUtils.copy(is,os);
            is.close();
            os.close();

        }catch (Exception e){
            return false;

        }
        return true;

    }
}
