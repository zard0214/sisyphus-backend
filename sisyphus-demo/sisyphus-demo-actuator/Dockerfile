# 基础镜像
FROM openjdk:8-jdk-alpine

# 作者信息
MAINTAINER "Zhecheng.Zhao 710838967@qq.com"

# 添加一个存储空间
VOLUME /tmp

# 暴露8080端口
EXPOSE 8080

# 添加变量，如果使用dockerfile-maven-plugin，则会自动替换这里的变量内容
ARG JAR_FILE=target/demo-actuator.jar

# 往容器中添加jar包
ADD ${JAR_FILE} app.jar

# 资源路径
ENV MAVEN_HOST=https://repo1.maven.org/maven2 \
    ALPINE_HOST=dl-cdn.alpinelinux.org \
    MIRROR_MAVEN_HOST=https://maven.aliyun.com/repository/public \
    MIRROR_ALPINE_HOST=mirrors.aliyun.com

COPY ${APP_PATH} /app/${APP}

WORKDIR /app/${APP}

# 启动镜像自动运行程序
ENTRYPOINT ["/sbin/tini", "--", "java","-Djava.security.egd=file:/dev/urandom","-jar","/app.jar"]

