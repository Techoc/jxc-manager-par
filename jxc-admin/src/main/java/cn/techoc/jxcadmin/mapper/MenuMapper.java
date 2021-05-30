package cn.techoc.jxcadmin.mapper;

import cn.techoc.jxcadmin.dto.TreeDto;
import cn.techoc.jxcadmin.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author techoc
 * @since 2021-05-30
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<TreeDto> queryAllMenus();
}
