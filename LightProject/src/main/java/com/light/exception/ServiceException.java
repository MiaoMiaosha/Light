package com.light.exception;
/**
 * service层异常
 * @ClassName: ServiceException 
 * @author TobyHan
 * @date 2017年2月15日 下午7:23:22 
 *
 */
public class ServiceException extends RuntimeException {
	
	public ServiceException(String msg){
		super(msg);
	}
	public ServiceException(String msg, Throwable cause){
		super(msg,cause);
	}
}
