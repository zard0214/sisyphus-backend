package com.sisyphus.auth.core.validate.code.image;

import com.sisyphus.auth.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 16:42
 */
public class ImageCode extends ValidateCode implements Serializable {

    private static final long serialVersionUID = -703011095085705839L;
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
