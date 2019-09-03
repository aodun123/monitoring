package com.cn.uvfortune.infrastructure.repository.mapper.sysmapper;


import com.cn.uvfortune.domain.model.org.OrgUser;
import com.cn.uvfortune.domain.model.security.RoleUser;
import com.cn.uvfortune.infrastructure.repository.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysUserMapper {

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 修改用户信息
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(SysUser record);

    /**
     * 分页查询条件查用户信息
     *
     * @return
     */
    List<SysUser> getAllUser(@Param("sKey") String sKey, @Param("sex") String sex, @Param("status") String status, @Param("usernumber") String usernumber);

    /**
     * 查询用户信息
     *
     * @return
     */
    List<SysUser> getAllUserList(int sysAgencyId);

    /**
     * 根据id查询实体
     *
     * @param id
     * @return
     */
    OrgUser getPersonById(Integer id);

    /**
     * 添加用户
     *
     * @param record
     * @return
     */
    int insert(SysUser record);


    /**
     * 批量删除用户信息
     *
     * @param list
     * @return
     */
    int deleteBatch(List list);

    // 查询用户拥有的角色
    List<RoleUser> queryRoleByUserId(@Param("userid") Integer userid);

    // 根据名称查询用户信息
    SysUser queryUser(@Param("username") String username);

    // 重置密码
    int upPass(SysUser sysUser);

    /**
     * 查询所有联系人(用户id,姓名,手机号)
     */
    List<Map<String, Object>> contactList();

}