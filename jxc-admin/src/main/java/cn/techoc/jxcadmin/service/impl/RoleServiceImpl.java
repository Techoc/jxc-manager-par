package cn.techoc.jxcadmin.service.impl;

import cn.techoc.jxcadmin.pojo.Role;
import cn.techoc.jxcadmin.mapper.RoleMapper;
import cn.techoc.jxcadmin.pojo.User;
import cn.techoc.jxcadmin.query.RoleQuery;
import cn.techoc.jxcadmin.service.IRoleService;
import cn.techoc.jxcadmin.utils.PageResultUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author techoc
 * @since 2021-05-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public Map<String, Object> roleList(RoleQuery roleQuery) {
        IPage<Role> page = new Page<>(roleQuery.getPage(), roleQuery.getLimit());
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_del", 0);
        if (StringUtils.isNotBlank(roleQuery.getRoleName())) {
            queryWrapper.like("name", roleQuery.getRoleName());
        }
        page = this.baseMapper.selectPage(page, queryWrapper);
        return PageResultUtil.getResult(page.getTotal(), page.getRecords());
    }
}
