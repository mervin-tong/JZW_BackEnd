package com.piesat.school.biz.test.codeGenerator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 演示例子，执行 main 方法控tes制台输入模块表名回车自动生成对应项目目录中
 * 项目
 * 需要配置 49行输出路径 73 项目路径 88 自定义mapper
 */
@Slf4j
public class CodeGenerator {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {


        /*==============================================自行修改自己生产的生成代码的信息=========================================================*/
        //模块名称
        String modelName="clockin";
        //数据库表名 区分大小写
        String tableName="t_method";
        //对应子系统名字
        String proj="method";
        //项目路径
        String projPath="com.piesat.school.biz.ds."+proj;
        //模块存储路径
        String modelPath="/piesat-school-biz/src/main/java";
        //mapper.xml路径
        String mapperPath=modelPath+ "/com/piesat/school/biz/ds/" +proj+"/mapper";

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://47.100.53.132:3306/school?useUnicode=true&characterEncoding=utf-8&useSSL=false&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true&serverTimezone=UTC");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL)
                .setTypeConvert(new PostgreSqlTypeConvert(){
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        if(fieldType.toLowerCase().contains("numeric")){
                            return DbColumnType.DOUBLE;
                        }
                        if(fieldType.toLowerCase().contains("datetime")){
                            return DbColumnType.DATE;
                        }
                        if(fieldType.toLowerCase().contains("decimal")){
                            return DbColumnType.DOUBLE;
                        }
                        if(fieldType.toLowerCase().contains("timestamp")){
                            return DbColumnType.DATE;
                        }
                        log.info("数据类型:"+fieldType);
                        return super.processTypeConvert(globalConfig, fieldType);
                    }
                });
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //gc.setOutputDir(projectPath + "/hnswc-common/src/main/java");//输出文件路径
        String projectPath = System.getProperty("user.dir");
        //输出文件路径
        gc.setOutputDir(projectPath + modelPath);
        gc.setOpen(false);
        gc.setFileOverride(true);
        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(false);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        // 作者
        gc.setAuthor("周悦尧");
        mpg.setGlobalConfig(gc);
        mpg.setDataSource(dsc);


        // 包配置
        PackageConfig pc = new PackageConfig();
            //pc.setModuleName(scanner("模块名"));
//            pc.setModuleName(modelName);
        pc.setController(null);
            //修改为自己的项目路径
           pc.setParent("com.piesat.school.biz");
            pc.setParent(projPath);
        mpg.setPackageInfo(pc);
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath +mapperPath  + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
            templateConfig.setEntity("templates/entity.java");
            templateConfig.setService("templates/service.java");
//            templateConfig.setController("templates/controller.java");
            templateConfig.setMapper("templates/mapper.java");
            templateConfig.setServiceImpl("templates/serviceImpl.java");
            templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(tableName);
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("t_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }

}
