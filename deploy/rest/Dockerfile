FROM base/jre1.8-chinese

MAINTAINER cml

##设置控制台字符集编码
ENV LANG C.UTF-8

ENV HOME /app

RUN mkdir -p ${HOME} && mkdir -p ${HOME}/config

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ADD piesat-school-rest.jar ${HOME}/

WORKDIR ${HOME}

ENTRYPOINT ["java","-jar","piesat-school-rest.jar"]
