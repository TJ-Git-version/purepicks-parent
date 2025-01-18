package com.devsurfer.purepicks.manager.mapper;

import com.devsurfer.purepicks.model.dto.system.user.SysUserQueryDto;
import com.devsurfer.purepicks.model.entity.system.SysUser;
import com.devsurfer.purepicks.model.vo.system.user.SysUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/11 15:13
 * description TODO
 */
public interface SysUserMapper {

    SysUser selectByUserName(String username);

    List<SysUserVo> findUserList(SysUserQueryDto sysUseQueryDto);

    void insertUser(SysUser sysUser);

    SysUser selectByIdAndUserName(@Param("id") Long id, @Param("username") String username);

    void updateUser(SysUser sysUser);

    void deleteUserInId(@Param("roleIds") List<Long> roleIds);

    void updateUserStatus(@Param("userId") Long userId, @Param("status") Integer status);

}
