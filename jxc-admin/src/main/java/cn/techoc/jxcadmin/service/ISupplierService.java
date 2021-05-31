package cn.techoc.jxcadmin.service;

import cn.techoc.jxcadmin.pojo.Supplier;
import cn.techoc.jxcadmin.query.SupplierQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Map;

/**
 * <p>
 * 供应商表 服务类
 * </p>
 *
 * @author techoc
 * @since 2021-05-31
 */
public interface ISupplierService extends IService<Supplier> {

    Map<String, Object> supplierList(SupplierQuery supplierQuery);

    Object findSupplierByName(String name);

    void saveSupplier(Supplier supplier);

    void updateSupplier(Supplier supplier);

    void deleteSupplier(Integer[] ids);
}
