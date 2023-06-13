package co.yiiu.pybbs.controller.front;

import co.yiiu.pybbs.controller.api.BaseApiController;
import co.yiiu.pybbs.util.FileUtil;
import co.yiiu.pybbs.util.captcha.Captcha;
import co.yiiu.pybbs.util.captcha.GifCaptcha;
import edu.ucr.cs.riple.taint.ucrtainting.qual.RUntainted;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** Created by tomoya. Copyright (c) 2018, All Rights Reserved. https://atjiu.github.io */
@Controller
@RequestMapping("/common")
public class CommonController extends BaseApiController {

  @Resource private FileUtil fileUtil;

  // gif 验证码
  @GetMapping("/captcha")
  public void captcha(HttpServletResponse response, HttpSession session) throws IOException {
    Captcha captcha = new GifCaptcha();
    captcha.out(response.getOutputStream());
    @RUntainted String text = captcha.text();
    session.setAttribute("_captcha", text);
  }

  @GetMapping("/show_img")
  public String showOssImg(String name) {
    return "redirect:" + fileUtil.generatorOssUrl(name);
  }
}
