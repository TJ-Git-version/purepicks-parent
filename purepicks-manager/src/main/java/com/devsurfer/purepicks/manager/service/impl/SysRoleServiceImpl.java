package com.devsurfer.purepicks.manager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.devsurfer.purepicks.manager.mapper.SysRoleMapper;
import com.devsurfer.purepicks.manager.service.SysRoleService;
import com.devsurfer.purepicks.model.dto.system.role.SysRoleDeleteDto;
import com.devsurfer.purepicks.model.dto.system.role.SysRoleInsertDto;
import com.devsurfer.purepicks.model.dto.system.role.SysRoleQueryDto;
import com.devsurfer.purepicks.model.dto.system.role.SysRoleUpdateDto;
import com.devsurfer.purepicks.model.entity.system.SysRole;
import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.model.vo.system.role.SysRoleVo;
import com.devsurfer.purepicks.service.handle.PurePicksException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 17:51
 * description TODO
 */
@Service
@AllArgsConstructor
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleMapper sysRoleMapper;

    @Override
    public PageInfo<SysRoleVo> pageFindRoleList(SysRoleQueryDto sysRoleQueryDto) {
        try (Page<SysRoleVo> page = PageHelper.startPage(sysRoleQueryDto.getPageNum(), sysRoleQueryDto.getPageSize());) {
            sysRoleMapper.findRoleList(sysRoleQueryDto);
            return page.toPageInfo();
        }
    }

    @Override
    public void addRole(SysRoleInsertDto sysRoleInsertDto) {
        // 校验用户名不能重复
        String roleName = sysRoleInsertDto.getRoleName();
        if (sysRoleMapper.selectByRoleName(roleName) != null) {
            PurePicksException.error(ResultCodeEnum.ROLE_NAME_EXISTS_ERROR);
        }
        // 校验角色编号不能重复
        String roleCode = sysRoleInsertDto.getRoleCode();
        if (sysRoleMapper.selectByRoleCode(roleCode) != null) {
            PurePicksException.error(ResultCodeEnum.ROLE_CODE_EXISTS_ERROR);
        }
        sysRoleMapper.insertRole(BeanUtil.copyProperties(sysRoleInsertDto, SysRole.class));
    }

    @Override
    public void editRole(SysRoleUpdateDto sysRoleUpdateDto) {
        // 校验用户名不能重复
        Long id = sysRoleUpdateDto.getId();
        String roleName = sysRoleUpdateDto.getRoleName();
        if (sysRoleMapper.checkByIdAndRoleName(id, roleName)) {
            PurePicksException.error(ResultCodeEnum.ROLE_NAME_EXISTS_ERROR);
        }
        // 校验角色编号不能重复
        String roleCode = sysRoleUpdateDto.getRoleCode();
        if (sysRoleMapper.checkByIdAndRoleCode(id, roleCode)) {
            PurePicksException.error(ResultCodeEnum.ROLE_CODE_EXISTS_ERROR);
        }
        sysRoleMapper.editRole(BeanUtil.copyProperties(sysRoleUpdateDto, SysRole.class));
    }

    @Override
    public void removeRole(SysRoleDeleteDto sysRoleDeleteDto) {
        sysRoleMapper.deleteRoleInId(sysRoleDeleteDto.getRoleIds());
    }
}
