package cn.techoc.jxcadmin.utils;


import cn.techoc.jxcadmin.exceptions.ParamsException;

/**
 * <P>断言工具类</P>
 *
 * @author techoc
 * @since 2021/5/26
 */
public class AssertUtil {


    public static void isTrue(Boolean flag, String msg) {
        if (flag) {
            throw new ParamsException(msg);
        }
    }

}
