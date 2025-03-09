package com.devsurfer.purepicks.manager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.devsurfer.purepicks.log.annotation.Log;
import com.devsurfer.purepicks.manager.mapper.SysUserMapper;
import com.devsurfer.purepicks.manager.service.SysUserService;
import com.devsurfer.purepicks.model.dto.system.user.SysUserDeleteDto;
import com.devsurfer.purepicks.model.dto.system.user.SysUserInsertDto;
import com.devsurfer.purepicks.model.dto.system.user.SysUserQueryDto;
import com.devsurfer.purepicks.model.dto.system.user.SysUserUpdateDto;
import com.devsurfer.purepicks.model.entity.system.SysUser;
import com.devsurfer.purepicks.model.enums.log.LogBusinessTypeEnum;
import com.devsurfer.purepicks.model.enums.log.LogModuleNameEnum;
import com.devsurfer.purepicks.model.enums.log.LogOperateTypeEnum;
import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.model.vo.system.user.SysUserVo;
import com.devsurfer.purepicks.service.handle.PurePicksException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 20:36
 * description TODO
 */
@Service
@AllArgsConstructor
public class SysUserServiceImpl implements SysUserService {

    private final SysUserMapper sysUserMapper;

    @Override
    public PageInfo<SysUserVo> pageFindUserList(SysUserQueryDto sysUseQueryDto) {
        try (Page<SysUserVo> page = PageHelper.startPage(sysUseQueryDto.getPageNum(), sysUseQueryDto.getPageSize())) {
            sysUserMapper.findUserList(sysUseQueryDto);
            return page.toPageInfo();
        }
    }

    @Override
    @Log(moduleName = LogModuleNameEnum.USER_ADD, operateType = LogOperateTypeEnum.MANAGER_USER, businessType = LogBusinessTypeEnum.INSERT)
    public void addUser(SysUserInsertDto sysUserInsertDto) {
        // 用户名是否已存在
        if (sysUserMapper.selectByUserName(sysUserInsertDto.getUsername()) != null) {
            PurePicksException.error(ResultCodeEnum.USER_NAME_EXISTS_ERROR);
        }
        SysUser sysUser = BeanUtil.copyProperties(sysUserInsertDto, SysUser.class);
        // 生成默认密码：surfer@用户名
        String password = DigestUtils.md5DigestAsHex(StrUtil.format("surfer@{}", sysUserInsertDto.getUsername()).getBytes());
        sysUser.setPassword(password);
        sysUserMapper.insertUser(sysUser);
    }

    @Override
    public void editUser(SysUserUpdateDto sysUserUpdateDto) {
        // 用户名是否已存在
        if (sysUserMapper.selectByIdAndUserName(sysUserUpdateDto.getId(), sysUserUpdateDto.getUsername()) != null) {
            PurePicksException.error(ResultCodeEnum.USER_NAME_EXISTS_ERROR);
        }
        sysUserMapper.updateUser(BeanUtil.copyProperties(sysUserUpdateDto, SysUser.class));
    }

    @Override
    public void removeUser(SysUserDeleteDto sysUserDeleteDto) {
        sysUserMapper.deleteUserInId(sysUserDeleteDto.getRoleIds());
    }

    @Override
    public void updateUserStatus(Long userId, Integer status) {
        sysUserMapper.updateUserStatus(userId, status);
    }

}
