package cn.techoc.jxcadmin.service;

import cn.techoc.jxcadmin.dto.TreeDto;
import cn.techoc.jxcadmin.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author techoc
 * @since 2021-05-30
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 返回所有菜单数据
     * @return
     */
    List<TreeDto> queryAllMenus(Integer roleId);
}
