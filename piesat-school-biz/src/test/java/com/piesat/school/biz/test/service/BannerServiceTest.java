package com.piesat.school.biz.test.service;

import com.piesat.school.biz.ds.datainf.entity.Contact;
import com.piesat.school.biz.test.SchoolBizTestApplication;
import com.piesat.school.datainf.iservice.IRDataInfService;
import com.piesat.school.datainf.param.DataInfSaveParamData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Date;


@SpringBootTest(classes = SchoolBizTestApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SuppressWarnings("all")
@Slf4j
public class BannerServiceTest {


    @Test
    public void testString() throws IOException {
        Resource resource = new ClassPathResource("biz/config.yml");
        System.out.println(resource.getURL().toString());
    }

    /**
     * 获取days后的日期
     * @param afterDays
     * @return
     * @throws InterruptedException
     */
    public Date getDateAfterDays(int afterDays) throws InterruptedException {
        Thread.sleep(afterDays * 24 * 3600 * 1000l);
        return new Date();
    }
    @Test
    public void test(){

    }
}
