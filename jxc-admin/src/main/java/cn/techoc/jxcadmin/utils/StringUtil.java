package cn.techoc.jxcadmin.utils;

/**
 * <P>字符串工具类</P>
 *
 * @author techoc
 * @since 2021/5/26
 */
public class StringUtil {

    /**
     * 判断是否是空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否不是空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return (str != null) && !"".equals(str.trim());
    }


    /**
     * 生成四位编号
     *
     * @param code
     * @return
     */
    public static String formatCode(String code) {
        try {
            int length = code.length();
            int num = Integer.parseInt(code.substring(length - 4, length)) + 1;
            String codenum = Integer.toString(num);
            int codelength = codenum.length();
            for (int i = 4; i > codelength; i--) {
                codenum = "0" + codenum;
            }
            return codenum;
        } catch (NumberFormatException e) {
            return "0100";
        }
    }

}
