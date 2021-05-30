package cn.techoc.jxcadmin.service.impl;

import cn.techoc.jxcadmin.dto.TreeDto;
import cn.techoc.jxcadmin.pojo.Menu;
import cn.techoc.jxcadmin.mapper.MenuMapper;
import cn.techoc.jxcadmin.service.IMenuService;
import cn.techoc.jxcadmin.service.IRoleMenuService;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author techoc
 * @since 2021-05-30
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Resource
    private IRoleMenuService roleMenuService;

    @Override
    public List<TreeDto> queryAllMenus(Integer roleId) {
        List<TreeDto> treeDtos = this.baseMapper.queryAllMenus();
        List<Integer> roleHasMenuIds = roleMenuService.queryRoleHasAllMenusByRoleId(roleId);
        if (CollectionUtils.isNotEmpty(roleHasMenuIds)) {
            treeDtos.forEach(treeDto -> {
                if (roleHasMenuIds.contains(treeDto.getId())) {
                    treeDto.setChecked(true);
                }
            });
        }
        return treeDtos;
    }
}
