package cn.techoc.jxcadmin.service.impl;

import cn.techoc.jxcadmin.dto.TreeDto;
import cn.techoc.jxcadmin.pojo.GoodsType;
import cn.techoc.jxcadmin.mapper.GoodsTypeMapper;
import cn.techoc.jxcadmin.service.IGoodsTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品类别表 服务实现类
 * </p>
 *
 * @author techoc
 * @since 2021-05-31
 */
@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsType>
    implements IGoodsTypeService {

    @Override
    public List<TreeDto> queryAllGoodsTypes(Integer typeId) {
        List<TreeDto> treeDtos = this.baseMapper.queryAllGoodsTypes();
        if(null !=typeId){
            for (TreeDto treeDto : treeDtos) {
                if(treeDto.getId().equals(typeId)){
                    // 设置节点选中
                    treeDto.setChecked(true);
                    break;
                }
            }
        }
        return treeDtos;
    }

    @Override
    public List<Integer> queryAllSubTypeIdsByTypeId(Integer typeId) {
        GoodsType goodsType = this.getById(typeId);
        if (goodsType.getPId() == -1) {
            // 所有类别
            return this.list().stream().map(GoodsType::getId).collect(Collectors.toList());
        }

        List<Integer> result = new ArrayList<Integer>();
        result.add(typeId);
        return getSubTypeIds(typeId, result);
    }

    private List<Integer> getSubTypeIds(Integer typeId, List<Integer> result) {
        List<GoodsType> goodsTypes =
            this.baseMapper.selectList(new QueryWrapper<GoodsType>().eq("p_id", typeId));
        if (CollectionUtils.isNotEmpty(goodsTypes)) {
            goodsTypes.forEach(gt -> {
                result.add(gt.getId());
                getSubTypeIds(gt.getId(), result);
            });
        }
        return result;
    }
}
