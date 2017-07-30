package com.light.common;


import com.light.exception.ServiceException;

public class LengthUtil {

	/**
	 * 检测中文字符长度
	 * @Title: checkCNStrLength 
	 * @author TobyHan
	 * Date : 2017年2月16日 下午5:47:26
	 */
	public static void checkCNStrLength(String checkChar,int length){
		if(checkChar != null && checkChar.trim() != ""){
			if(checkChar.length() > length)
				throw new ServiceException("字符长度错误");
		}
	}
	public static void main(String args[]){
		checkCNStrLength("你好", 1);
	}
}
