package org.synapse.oauth.captcha;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class CaptchaGenerator {

    private static final long EXPIRE = 300;

    public void out(String code, OutputStream outputStream)
            throws IOException {
        out(5, code, outputStream);
    }

    public void out(int len, String code, OutputStream outputStream)
            throws IOException {
        out(130, 48, len, code, outputStream);
    }

    public void out(int len, Font font, String code, OutputStream outputStream)
            throws IOException {
        out(130, 48, len, font, code, outputStream);
    }

    public void out(int width, int height, int len, String code, OutputStream outputStream)
            throws IOException {
        out(width, height, len, null, code, outputStream);
    }

    public void out(int width, int height, int len, Font font, String code, OutputStream outputStream)
            throws IOException {
        outCaptcha(width, height, len, font, 1, code, outputStream);
    }
    public void out(int width, int height, int len, Font font, int type, String code, OutputStream outputStream)
            throws IOException {
        outCaptcha(width, height, len, font, type, code, outputStream);
    }

    public void outPng(String code, OutputStream outputStream)
            throws IOException {
        outPng(5, code, outputStream);
    }

    public void outPng(int len, String code, OutputStream outputStream)
            throws IOException {
        outPng(130, 48, len, code, outputStream);
    }

    public void outPng(int len, Font font, String code, OutputStream outputStream)
            throws IOException {
        outPng(130, 48, len, font, code, outputStream);
    }

    public void outPng(int width, int height, int len, String code, OutputStream outputStream)
            throws IOException {
        outPng(width, height, len, null, code, outputStream);
    }

    public void outPng(int width, int height, int len, Font font, String code, OutputStream outputStream)
            throws IOException {
        outCaptcha(width, height, len, font, 0, code, outputStream);
    }

    public void outCaptcha(int width, int height, int len, Font font, int cType, String code, OutputStream outputStream) {
        Captcha captcha;
        if (cType == 0) {
            captcha = new PngCaptcha(width, height, len);
        } else {
            captcha = new GifCaptcha(width, height, len);
        }
        if (font != null) {
            captcha.setFont(font);
        }
//        redisTemplate.opsForValue().set(code + "_CAPTCHA", captcha.text().toLowerCase());
//        redisTemplate.expire(code, EXPIRE, TimeUnit.SECONDS);
        captcha.out(outputStream);
    }

}
