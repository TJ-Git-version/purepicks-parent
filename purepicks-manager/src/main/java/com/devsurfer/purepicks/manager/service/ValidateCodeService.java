package com.devsurfer.purepicks.manager.service;

import com.devsurfer.purepicks.model.vo.system.ValidateCodeVo;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 2:07
 * description 生成验证码业务代码
 */
public interface ValidateCodeService {

    ValidateCodeVo generateValidateCode();


}
