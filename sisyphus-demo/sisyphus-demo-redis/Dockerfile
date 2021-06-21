# 基础镜像
FROM openjdk:8-jdk-alpine

# 作者信息
MAINTAINER "Zhecheng.Zhao 710838967@qq.com"

# 添加一个存储空间
VOLUME /tmp

# 暴露8080端口
EXPOSE 8080

# 添加变量，如果使用dockerfile-maven-plugin，则会自动替换这里的变量内容
ARG JAR_FILE=target/demo-redis.jar

# 往容器中添加jar包
ADD ${JAR_FILE} app.jar

# 资源路径
ENV MAVEN_HOST=https://repo1.maven.org/maven2 \
    ALPINE_HOST=dl-cdn.alpinelinux.org \
    MIRROR_MAVEN_HOST=https://maven.aliyun.com/repository/public \
    MIRROR_ALPINE_HOST=mirrors.aliyun.com

# 下载arthas到/opt/arthas-boot.jar，
RUN if $MIRROR; then MAVEN_HOST=${MIRROR_MAVEN_HOST} ;ALPINE_HOST=${MIRROR_ALPINE_HOST} ; sed -i "s/dl-cdn.alpinelinux.org/${ALPINE_HOST}/g" /etc/apk/repositories ; fi && \
    # https://github.com/docker-library/openjdk/issues/76
    apk add --no-cache tini && \
    apk add --no-cache busybox-extras && \
    apk add --no-cache curl && \
    apk add --no-cache bash && \
    apk add --no-cache jq && \
    wget "https://arthas.aliyun.com/arthas-boot.jar" -qO /opt/arthas-boot.jar && \
    echo "java -jar /opt/arthas-boot.jar --repo-mirror aliyun" > /usr/local/bin/arthas && \
    chmod a+x /usr/local/bin/arthas

COPY ${APP_PATH} /app/${APP}

WORKDIR /app/${APP}

# 启动镜像自动运行程序
ENTRYPOINT ["/sbin/tini", "--", "java","-Djava.security.egd=file:/dev/urandom","-jar","/app.jar"]

