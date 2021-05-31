package cn.techoc.jxcadmin.controller;


import cn.techoc.jxcadmin.pojo.GoodsUnit;
import cn.techoc.jxcadmin.service.IGoodsUnitService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 商品单位表 前端控制器
 * </p>
 *
 * @author techoc
 * @since 2021-05-31
 */
@Controller
@RequestMapping("/goodsUnit")
public class GoodsUnitController {
    @Resource
    private IGoodsUnitService goodsUnitService;

    /**
     * 展示所有商品单位
     *
     * @return
     */
    @RequestMapping("allGoodsUnits")
    @ResponseBody
    public List<GoodsUnit> allGoodsUnits() {
        return goodsUnitService.list();
    }
}
