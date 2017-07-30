package com.light.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.light.exception.ServiceException;
import com.light.pojo.LgOtherApply;

public interface ApplyService {

	/**
	 * 添加申请
	 * @Title addApply
	 * @author TobyHan
	 * Date : 2017年6月1日 下午9:49:07
	 * @param apply
	 * @return
	 * @throws ServiceException
	 */
	Map addApply(LgOtherApply apply) throws ServiceException;
	/**
	 * 更改申请内容
	 * @Title updateApply
	 * @author TobyHan
	 * Date : 2017年6月1日 下午11:02:22
	 * @param apply
	 * @return
	 * @throws ServiceException
	 */
    Map updateApply(LgOtherApply apply) throws ServiceException;
    /**
     * 分页查询
     * @Title selectApply
     * @author TobyHan
     * Date : 2017年6月1日 下午11:04:18
     * @param apply
     * @param page
     * @param rows
     * @return
     * @throws ServiceException
     */
    PageInfo<LgOtherApply> selectApply(LgOtherApply apply, Integer page,Integer rows) throws ServiceException;
    /**
     * 删除申请
     * @Title deleteApply
     * @author TobyHan
     * Date : 2017年6月1日 下午11:05:19
     * @param id
     * @return
     * @throws ServiceException
     */
    Map deleteApply(Integer id) throws ServiceException;
}
