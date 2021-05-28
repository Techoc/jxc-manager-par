package cn.techoc.jxcadmin.controller;


import cn.techoc.jxcadmin.query.RoleQuery;
import cn.techoc.jxcadmin.service.IRoleService;
import java.util.Map;
import javax.annotation.Resource;
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
        System.out.println("+++++++++++++++++++++++++++++");
        return roleService.roleList(roleQuery);
    }
}
