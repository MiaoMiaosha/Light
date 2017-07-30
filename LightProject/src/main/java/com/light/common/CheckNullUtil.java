package com.light.common;

import java.util.List;


/**
 * 检测非空工具
 * @ClassName: CheckNullUtil 
 * @author TobyHan
 * @date 2017年2月14日 上午11:23:09 
 *
 */
public class CheckNullUtil {
/**
 *
 */
	/**
	 * 检测integer类型是否为空
	 * @Title: checkInteger 
	 * @author TobyHan
	 * Date : 2017年2月14日 上午11:24:29
	 */
	public static boolean integerNull(Integer num){
		return (num == null)?true:false;
	}
	public static boolean integerNotNull(Integer num){
		return (num == null)?false:true;
	}
	/**
	 * 检测字符串是否为空
	 * @Title: isEmpty 
	 * @author TobyHan
	 * Date : 2017年2月14日 上午11:28:10
	 */
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    public static boolean listNotNull(List list){
    	if(list != null && list.size() > 0)
    		return true;
    	return false;
    }
}
