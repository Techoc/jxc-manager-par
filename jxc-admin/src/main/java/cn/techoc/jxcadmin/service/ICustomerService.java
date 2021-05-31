package cn.techoc.jxcadmin.service;

import cn.techoc.jxcadmin.pojo.Customer;
import cn.techoc.jxcadmin.query.CustomerQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Map;

/**
 * <p>
 * 客户表 服务类
 * </p>
 *
 * @author techoc
 * @since 2021-05-31
 */
public interface ICustomerService extends IService<Customer> {

    Map<String, Object> customerList(CustomerQuery customerQuery);

    void saveCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(Integer[] ids);

    Customer findCustomerByName(String name);
}
