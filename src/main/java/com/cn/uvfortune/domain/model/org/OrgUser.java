package com.cn.uvfortune.domain.model.org;

import com.cn.uvfortune.domain.model.security.RoleUser;
import com.cn.uvfortune.domain.repository.sysrepo.QueryUserRepo;
import com.cn.uvfortune.domain.repository.sysrepo.UserRepo;
import com.cn.uvfortune.infrastructure.repository.entity.SysUser;
import com.cn.uvfortune.infrastructure.repository.entity.parameter.Pager;
import com.cn.uvfortune.infrastructure.repository.entity.Pages;

import java.util.List;

/**
 * @Auther: hancunyan
 * @Date: 2018/7/24 18:43
 * @Description: 用户模型
 */
public class OrgUser {
    private Integer id; // 主键id

    private String username; // 用户名

    private String password; // 密码

    private String realname; // 姓名

    private String usernumber; // 人员编号

    private String sex; // 性别

    private String cardid; // 身份证号

    private String phone; // 电话

    private String email; // 邮箱

    private String status; // 人员状态

    private String image; // 头像

    private String adress; // 地址

    private String descption; // 描述

    public OrgUser() {

    }

    public OrgUser(Integer id, String username, String password, String realname, String usernumber, String sex, String cardid, String phone, String email, String status, String image, String adress, String descption) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.usernumber = usernumber;
        this.sex = sex;
        this.cardid = cardid;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.image = image;
        this.adress = adress;
        this.descption = descption;
    }

    // 用户功能模型
    UserRepo userRepo;

    // 用户信息查询
    QueryUserRepo queryUserRepo;

    // 添加用户信息
    public int addUser(OrgUser orgUser) {
        return userRepo.addUser(orgUser);
    }

    // 批量删除用户信息
    public int deletebatch(String ids) {
        return userRepo.deleteBatch(ids);
    }

    // 修改用户信息
    public int upUser(OrgUser orgUser) {
        return userRepo.upUser(orgUser);
    }

    // 删除用户信息
    public int delUser(Integer id) {
        return userRepo.delUser(id);
    }

    // 重置密码
    public int upPassword(OrgUser orgUser) {
        return userRepo.upPass(orgUser);
    }

    // 根据id 查询实体
    public OrgUser selectUserById(Integer id) {
        return queryUserRepo.queryPersonById(id);
    }

    //分页查询用户信息
    public Pages selectAll(Pager pager, String sex, String status, String userNumber) {
        return queryUserRepo.queryAllUser(pager, sex, status, userNumber);
    }

    //查询用户信息
    public List<SysUser> selectAllUser(int sysAgencyId) {
        return queryUserRepo.queryAllUser(sysAgencyId);
    }

    public SysUser queryUser(String username) {
        return queryUserRepo.queryUser(username);
    }

    // 查询用户所拥有的角色
    public List<RoleUser> queryRole(int userid) {
        return queryUserRepo.queryRoleByUserId(userid);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(String usernumber) {
        this.usernumber = usernumber == null ? null : usernumber.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid == null ? null : cardid.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress == null ? null : adress.trim();
    }

    public String getDescption() {
        return descption;
    }

    public void setDescption(String descption) {
        this.descption = descption;
    }

    public UserRepo getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public QueryUserRepo getQueryUserRepo() {
        return queryUserRepo;
    }

    public void setQueryUserRepo(QueryUserRepo queryUserRepo) {
        this.queryUserRepo = queryUserRepo;
    }
}
