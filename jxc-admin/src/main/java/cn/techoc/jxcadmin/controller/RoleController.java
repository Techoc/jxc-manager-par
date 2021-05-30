package cn.techoc.jxcadmin.controller;


import cn.techoc.jxcadmin.model.RespBean;
import cn.techoc.jxcadmin.pojo.Role;
import cn.techoc.jxcadmin.pojo.User;
import cn.techoc.jxcadmin.query.RoleQuery;
import cn.techoc.jxcadmin.service.IRoleService;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author techoc
 * @since 2021-05-28
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Resource
    private IRoleService roleService;

    /**
     * 角色管理主页
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "role/role";
    }

    /**
     * 角色列表查询
     *
     * @param roleQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> roleList(RoleQuery roleQuery) {
        return roleService.roleList(roleQuery);
    }

    /**
     * 添加|更新角色信息
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateRolePage")
    public String addOrUpdatePage(Integer id, Model model) {
        if (null != id) {
            model.addAttribute("role", roleService.getById(id));
        }
        return "role/add_update";
    }

    /**
     * 角色添加接口
     *
     * @param role
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean saveRole(Role role) {
        roleService.saveRole(role);
        return RespBean.success("角色记录添加成功!");
    }

    /**
     * 角色记录更新接口
     *
     * @param role
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public RespBean updateRole(Role role) {
        roleService.updateRole(role);
        return RespBean.success("角色记录更新成功!");
    }

    /**
     * 角色记录删除接口
     *
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteRole(Integer id) {
        roleService.deleteRole(id);
        return RespBean.success("角色记录删除成功!");
    }

    /**
     * 通过id查询所有用户角色
     *
     * @param userId
     * @return
     */
    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map<String, Object>> queryAllRoles(Integer userId) {
        return roleService.queryAllRoles(userId);
    }
}
