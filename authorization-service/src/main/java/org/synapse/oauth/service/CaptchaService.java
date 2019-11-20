package org.synapse.oauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.synapse.oauth.captcha.CaptchaGenerator;

import java.io.IOException;
import java.io.OutputStream;

@Service
public class CaptchaService {
    @Autowired
    private CaptchaGenerator captchaGenerator;

    public void genCaptcha(int width, int height, int type, String code, OutputStream outputStream) throws IOException {
        captchaGenerator.out(width, height, 5, null, type, code, outputStream);

    }

    public String findCaptcha(String securityCode) {
        return null;
    }

    public void remove(String securityCode) {
    }

}
