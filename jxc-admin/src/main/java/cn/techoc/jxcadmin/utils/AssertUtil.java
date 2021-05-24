package cn.techoc.jxcadmin.utils;


import cn.techoc.jxcadmin.exceptions.ParamsException;

/**
 * @author techoc
 */
public class AssertUtil {


    public  static void isTrue(Boolean flag,String msg){
        if(flag){
            throw  new ParamsException(msg);
        }
    }

}
