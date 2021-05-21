package com.sisyphus.auth.validate.code.img;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhecheng.zhao
 * @date Created in 18/05/2021 09:42
 */
@Data
public class ImageCode implements Serializable {

    private BufferedImage image;

    private String code;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image, String code, int expireIn){
        this.image=image;
        this.code=code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

}