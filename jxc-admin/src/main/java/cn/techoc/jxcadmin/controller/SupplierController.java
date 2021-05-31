package cn.techoc.jxcadmin.controller;


import cn.techoc.jxcadmin.model.RespBean;
import cn.techoc.jxcadmin.pojo.Supplier;
import cn.techoc.jxcadmin.query.SupplierQuery;
import cn.techoc.jxcadmin.service.ISupplierService;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 供应商表 前端控制器
 * </p>
 *
 * @author techoc
 * @since 2021-05-31
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {

    @Resource
    private ISupplierService supplierService;

    /**
     * 供应商管理页面
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "supplier/supplier";
    }

    /**
     * 供应商列表查询接口
     *
     * @param supplierQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> supplierList(SupplierQuery supplierQuery) {
        return supplierService.supplierList(supplierQuery);
    }

    /**
     * 添加|更新供应商页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateSupplierPage")
    public String addOrUpdateSupplierPage(Integer id, Model model) {
        if (null != id) {
            model.addAttribute("supplier", supplierService.getById(id));
        }
        return "supplier/add_update";
    }

    /**
     * 添加供应商
     * @param supplier
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public RespBean saveSupplier(Supplier supplier){
        supplierService.saveSupplier(supplier);
        return RespBean.success("供应商记录添加成功!");
    }

    /**
     * 更新供应商
     * @param supplier
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public RespBean updateSupplier(Supplier supplier){
        supplierService.updateSupplier(supplier);
        return RespBean.success("供应商记录更新成功!");
    }

    /**
     * 删除供应商
     * @param ids
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteSupplier(Integer[] ids){
        supplierService.deleteSupplier(ids);
        return RespBean.success("供应商记录删除成功!");
    }
}
