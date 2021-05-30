package cn.techoc.jxcadmin.controller;


import cn.techoc.jxcadmin.dto.TreeDto;
import cn.techoc.jxcadmin.service.IMenuService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author techoc
 * @since 2021-05-30
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private IMenuService menuService;

    /**
     * 返回所有菜单数据
     *
     * @return
     */
    @RequestMapping("queryAllMenus")
    @ResponseBody
    public List<TreeDto> queryAllMenus(Integer roleId) {
        return menuService.queryAllMenus(roleId);
    }
}
