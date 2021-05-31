package cn.techoc.jxcadmin.controller;


import cn.techoc.jxcadmin.dto.TreeDto;
import cn.techoc.jxcadmin.service.IGoodsTypeService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 商品类别表 前端控制器
 * </p>
 *
 * @author techoc
 * @since 2021-05-31
 */
@Controller
@RequestMapping("/goodsType")
public class GoodsTypeController {
    @Resource
    private IGoodsTypeService goodsTypeService;

    @RequestMapping("queryAllGoodsTypes")
    @ResponseBody
    public List<TreeDto> queryAllGoodsTypes(Integer typeId){
        return goodsTypeService.queryAllGoodsTypes(typeId);
    }
}
