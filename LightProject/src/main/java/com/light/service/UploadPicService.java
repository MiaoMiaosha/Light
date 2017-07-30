package com.light.service;

import java.util.Map;

import com.light.exception.ServiceException;

public interface UploadPicService {
	
	Map getUploadToken() throws ServiceException;
	/**
	 * 获取新的七牛上传token
	 * @Title: getNewUploadToken 
	 * @author TobyHan
	 * Date : 2017年2月16日 下午8:34:57
	 */
	Map getNewUploadToken() throws ServiceException;
	
	Map deleteFileByKey(String key) throws ServiceException;
}
