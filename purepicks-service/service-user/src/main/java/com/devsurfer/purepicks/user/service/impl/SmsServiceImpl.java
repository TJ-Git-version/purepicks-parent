package com.devsurfer.purepicks.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.devsurfer.purepicks.model.enums.redis.RedisKeyConstantEnum;
import com.devsurfer.purepicks.model.properties.SmSProperties;
import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.service.handle.PurePicksException;
import com.devsurfer.purepicks.user.service.SmsService;
import lombok.AllArgsConstructor;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/23 19:42
 */
@Service
@AllArgsConstructor
@EnableConfigurationProperties(SmSProperties.class)
public class SmsServiceImpl implements SmsService {

    private final RedisTemplate<String, String> redisTemplate;
    private final SmSProperties smSProperties;

    @Override
    @Async
    public void sendValidateCode(String phone) {
        String redisKey = RedisKeyConstantEnum.build(RedisKeyConstantEnum.PHONE_VALIDATE_CODE, phone);
        String cacheValidateCode = redisTemplate.opsForValue().get(redisKey);
        if (StrUtil.isNotBlank(cacheValidateCode)) {
            PurePicksException.error(ResultCodeEnum.PHONE_CODE_VERIFICATION_ERROR);
        }
        String validateCode = RandomStringUtils.random(6, false, true);
        //sendSms(phone, validateCode);
        redisTemplate.opsForValue().set(redisKey, validateCode, 5, TimeUnit.MINUTES);
    }

    /**
     * 发送验证码
     *
     * @param phone        手机号
     * @param validateCode 验证码
     */
    private void sendSms(String phone, String validateCode) {
        Map<String, String> params = new HashMap<>();
        params.put("receive", phone);
        params.put("tag", validateCode);
        params.put("templateId", smSProperties.getTemplateId());

        String result = null;
        try {
            result = postForm(smSProperties.getAppCode(), smSProperties.getUrl(), params);
        } catch (IOException e) {
            PurePicksException.error(ResultCodeEnum.SYSTEM_ERROR);
        }
        System.out.println(result);
        //String host = smSProperties.getUrl();
        //String path = smSProperties.getPath();
        //String method = "POST";
        //String appcode = smSProperties.getAppCode();
        //Map<String, String> headers = new HashMap<>();
        ////最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        //headers.put("Authorization", "APPCODE " + appcode);
        ////根据API的要求，定义相对应的Content-Type
        //headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        //Map<String, String> querys = new HashMap<>();
        //Map<String, String> bodys = new HashMap<>();
        //bodys.put("content", "code:" + validateCode);
        //bodys.put("template_id", "CST_ptdie100");
        //bodys.put("phone_number", phone);

        //try {
        //    HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
        //    System.out.println(response.toString());
        //    //获取response的body
        //    //System.out.println(EntityUtils.toString(response.getEntity()));
        //} catch (Exception e) {
        //    e.printStackTrace();
        //    PurePicksException.error(ResultCodeEnum.SYSTEM_ERROR);
        //}
    }

    private String postForm(String appCode, String url, Map<String, String> params) throws IOException {
        url = url + buildRequestUrl(params);
        OkHttpClient client = new OkHttpClient.Builder().build();
        FormBody.Builder formbuilder = new FormBody.Builder();
        Iterator<String> it = params.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            formbuilder.add(key, params.get(key));
        }
        FormBody body = formbuilder.build();
        Request request = new Request.Builder().url(url).addHeader("Authorization", "APPCODE " + appCode).post(body).build();
        Response response = client.newCall(request).execute();
        System.out.println("返回状态码" + response.code() + ",message:" + response.message());
        return response.body().toString();
    }

    private String buildRequestUrl(Map<String, String> params) {
        StringBuilder url = new StringBuilder("?");
        Iterator<String> it = params.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            url.append(key).append("=").append(params.get(key)).append("&");
        }
        return url.substring(0, url.length() - 1);
    }
}
