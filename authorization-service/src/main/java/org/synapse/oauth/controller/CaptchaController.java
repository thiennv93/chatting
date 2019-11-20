package org.synapse.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.synapse.oauth.service.CaptchaService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {
    @Autowired
    private CaptchaService captchaService;

    @GetMapping
    public void genCaptcha(@RequestParam(value = "code") String code,
                           @RequestParam(name = "width", defaultValue = "130") int width,
                           @RequestParam(name = "height", defaultValue = "48") int height,
                           @RequestParam(name = "type", defaultValue = "0") int type,
                           HttpServletResponse response) throws IOException {
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        Assert.hasText(code, "authorization.error.code.missing");
        captchaService.genCaptcha(width, height, type, code, response.getOutputStream());
    }

}
