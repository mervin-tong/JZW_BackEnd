对于BPaas相关信息：

##############   测试环境   ###############
数据库：
mysql: 47.100.53.132 3306
root
root

服务端：

#部署目录：/apps/pie+/logs/piesat-school（pie+）/ 目录下rpc和rest子目录，打包jar替换掉，运行run.sh
#rest svc:  piesat-school-rest.pie-engine-application.svc.cluster.local:8080
#日志目录:/home/pie+/logs/log/piesat-pieplu

#接口文档地址：http://68.79.6.90:30825/piesat-school/doc.html
http://68.79.6.90:30825/piesat-school/doc.html

前端：

#部署目录：/home/pie+/logs/web/plus/
#访问前缀：https://cloudtest.piesat.cn/pie/plus/




##############   正式环境   ###############



数据库请联系管理

服务端：

#部署目录：/home/application/pie/piesat-school（pie+） 目录下rpc和rest子目录，打包jar替换掉，运行run.sh
#rest:  piesat-school-rest.pie-engine-application.svc.cluster.local:8080
#日志目录：/data/resources/school/log/piesat-school/

前端：

#部署目录在 /data/resources/school/web/plus/
#访问前缀：https://engine.piesat.cn/pie/plus/

