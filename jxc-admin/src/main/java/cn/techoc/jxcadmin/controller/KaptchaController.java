package cn.techoc.jxcadmin.controller;

import cn.techoc.jxcadmin.model.CaptchaImageModel;
import com.google.code.kaptcha.impl.DefaultKaptcha;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>验证码控制器</p>
 *
 * @author techoc
 * @since 2021/5/27
 */
@RestController
public class KaptchaController {
    @Resource
    public DefaultKaptcha defaultKaptcha;

    /**
     * 生成验证码
     *
     * @param session
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public void kaptcha(HttpSession session, HttpServletResponse response) throws IOException {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        // 验证码文字
        String capText = defaultKaptcha.createText();
        // 将验证码存入session 并设置2分钟后过期
        session.setAttribute("captcha_key", new CaptchaImageModel(capText, 2 * 60));
        ServletOutputStream out = response.getOutputStream();
        BufferedImage bufferedImage = defaultKaptcha.createImage(capText);
        ImageIO.write(bufferedImage, "jpg", out);
        out.flush();
    }
}
