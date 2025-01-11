package com.devsurfer.purepicks.manager;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.lang.Console;
import org.junit.jupiter.api.Test;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 1:52
 * description TODO
 */
public class TestCaptchaUtil {

    /**
     * 线段干扰的验证码
     */
    @Test
    public void test() {
        //
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        // 图形验证码写出，可以写出到文件，也可以写出到流
        lineCaptcha.write("E:\\内容中心\\夸克资源\\2024尚硅谷全端工程师\\阶段5_尚品甄选项目\\day03\\课件\\line.png");
        Console.log(lineCaptcha.getCode());
        // 输出code
        Console.log(lineCaptcha.getCode());
        // 验证图形验证码的有效性，返回boolean值
        lineCaptcha.verify("1234");

        //重新生成验证码
        lineCaptcha.createCode();
        lineCaptcha.write("E:\\内容中心\\夸克资源\\2024尚硅谷全端工程师\\阶段5_尚品甄选项目\\day03\\课件\\line1.png");
        //新的验证码
        Console.log(lineCaptcha.getCode());
        //验证图形验证码的有效性，返回boolean值
        lineCaptcha.verify("1234");
    }

    /**
     * 圆圈干扰验证码
     */
    @Test
    public void testCircleCaptcha() {
        // 定义图形验证码的长、宽、验证码字符数、干扰元素个数
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(200, 100, 6, 20);
        //图形验证码写出，可以写出到文件，也可以写出到流
        circleCaptcha.write("E:\\内容中心\\夸克资源\\2024尚硅谷全端工程师\\阶段5_尚品甄选项目\\day03\\课件\\circle.png");
        //验证图形验证码的有效性，返回boolean值
        boolean verify = circleCaptcha.verify("1234");
        System.out.println(verify);
    }

    /**
     * 扭曲干扰验证码
     */
    @Test
    public void testShearCaptcha() {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
        //ShearCaptcha captcha = new ShearCaptcha(200, 100, 4, 4);
        //图形验证码写出，可以写出到文件，也可以写出到流
        captcha.write("E:\\内容中心\\夸克资源\\2024尚硅谷全端工程师\\阶段5_尚品甄选项目\\day03\\课件\\shear.png");
        //验证图形验证码的有效性，返回boolean值
        captcha.verify("1234");
    }

    /**
     * 自定义验证码
     */
    @Test
    public void testRandomGenerator() {
        // 自定义纯数字的验证码（随机4位数字，可重复）
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        lineCaptcha.setGenerator(randomGenerator);
        lineCaptcha.write("E:\\内容中心\\夸克资源\\2024尚硅谷全端工程师\\阶段5_尚品甄选项目\\day03\\课件\\randomGenerator.png");
        // 重新生成code
        lineCaptcha.createCode();

        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 45, 4, 4);
        // 自定义验证码内容为四则运算方式
        captcha.setGenerator(new MathGenerator());
        captcha.write("E:\\内容中心\\夸克资源\\2024尚硅谷全端工程师\\阶段5_尚品甄选项目\\day03\\课件\\MathGenerator.png");
        // 重新生成code
        captcha.createCode();

    }
}
