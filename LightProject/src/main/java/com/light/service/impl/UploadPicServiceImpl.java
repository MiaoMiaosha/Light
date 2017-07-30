package com.light.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.light.common.CheckNullUtil;
import com.light.common.ConstantVal;
import com.light.common.FormatUtil;
import com.light.common.ReturnResult;
import com.light.dto.QNInfo;
import com.light.exception.ServiceException;
import com.light.mapper.LgConfigMapper;
import com.light.mapper.LgTokenMapper;
import com.light.pojo.LgConfig;
import com.light.pojo.LgConfigExample;
import com.light.pojo.LgToken;
import com.light.pojo.LgTokenExample;
import com.light.service.ConfigService;
import com.light.service.UploadPicService;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
@Service
public class UploadPicServiceImpl implements UploadPicService {

	@Autowired LgTokenMapper LgTokenMapper;
	@Autowired ConfigService ConfigService;
	//获取新的上传凭证
	@Override
    @Transactional(propagation=Propagation.REQUIRED)
	public Map getNewUploadToken() throws ServiceException {
		QNInfo qnObj = ConfigService.getQninfo();
		Auth auth = Auth.create(qnObj.getAccessKey(), qnObj.getSecretKey());
		String magicValue=
				"{" +
						"    \"status\": {" +
						"        \"timestamp\": "+FormatUtil.timeStampInt()+"," +
						"        \"code\": 200," +
						"        \"msg\": \"success\"," +
						"    }," +
						"    \"data\": " +
						"        {" +
						"            \"key\": $(key)," +
						"            \"hash\": $(etag)," +
						"            \"w\": $(imageInfo.width)," +
						"            \"h\": $(imageInfo.height)," +
						"            \"mimeType\": $(mimeType)," +
						"            \"ext\": $(ext)" +
						"           " +
						"        }" +
						"}";
		
        StringMap policy = new StringMap().putNotEmpty("returnBody", magicValue);
        String tempToken = auth.uploadToken(qnObj.getBucketName(), null, 3600,policy);
        
        LgTokenExample tokenExample =  new LgTokenExample();
        	tokenExample.createCriteria().andTypeEqualTo("upload_pic");
        LgToken record = new LgToken();
          Integer expire = FormatUtil.timeStampInt()+3550;
        	record.setToken(tempToken);
        	record.setTokenExpire(expire);
        int updateSta = LgTokenMapper.updateByExampleSelective(record, tokenExample);
        if(updateSta < 1)
        	throw new ServiceException("更新token失败");
        Map rsMap = new HashMap<>();
         rsMap.put("token", tempToken);
         rsMap.put("expire", expire);
		return ReturnResult.ok(rsMap);
	}

	//从数据库获取
	@Override
	public Map getUploadToken() throws ServiceException {
		Map<Object, Object> rsMap = new HashMap<>();
		boolean wrongFlag = false;//false为正确
		LgTokenExample example =  new LgTokenExample();
		 example.createCriteria().andTypeEqualTo("upload_pic");
		 List<LgToken> list = LgTokenMapper.selectByExample(example);
		 if(CheckNullUtil.listNotNull(list)){
			 LgToken tempObj = list.get(0);
			 if(tempObj.getTokenExpire()!= null && tempObj.getTokenExpire() > FormatUtil.timeStampInt()){
				 if(CheckNullUtil.isNotEmpty(tempObj.getToken())){
					 rsMap = new HashMap<>();
					 rsMap.put("token", tempObj.getToken());
					 rsMap.put("expire", tempObj.getTokenExpire());
				 }
				 else
					 wrongFlag=true;//throw new ServiceException("token为空");
			 }
			 else
				 wrongFlag=true;//throw new ServiceException("token已过期");
		}else{
			wrongFlag=true;//throw new ServiceException("无token记录");
		}
		 //如果token错误，则重新获取
		 if(wrongFlag){
				QNInfo qnObj = ConfigService.getQninfo();

			 Auth auth = Auth.create(qnObj.getAccessKey(), qnObj.getSecretKey());
				String magicValue=
						"{" +
								"\"url\":$(key),		"+
								"\"state\":\"SUCCESS\",   "+
								
								"    \"status\": {" +
								"        \"timestamp\": "+FormatUtil.timeStampInt()+"," +
								"        \"code\": 200," +
								"        \"msg\": \"success\"" +
								"    }," +
								"    \"data\": " +
								"        {" +
								"            \"key\": $(key)," +
								"            \"hash\": $(etag)," +
								"            \"w\": $(imageInfo.width)," +
								"            \"h\": $(imageInfo.height)," +
								"            \"mimeType\": $(mimeType)," +
								"            \"ext\": $(ext)" +
								"           " +
								"        }" +
								"}";
				
		        StringMap policy = new StringMap().putNotEmpty("returnBody", magicValue);
		        String tempToken = auth.uploadToken(qnObj.getBucketName(), null, 3600,policy);
		        
		        LgTokenExample tokenExample =  new LgTokenExample();
		        	tokenExample.createCriteria().andTypeEqualTo("upload_pic");
		        LgToken record = new LgToken();
		          Integer expire = FormatUtil.timeStampInt()+3550;
		        	record.setToken(tempToken);
		        	record.setTokenExpire(expire);
		        int updateSta = LgTokenMapper.updateByExampleSelective(record, tokenExample);
		        if(updateSta < 1)
		        	throw new ServiceException("更新token失败");
		         rsMap.put("token", tempToken);
		         rsMap.put("expire", expire);
		 }
		 
		return ReturnResult.ok(rsMap);
		
	}
	//删除空间中的key
	@Override
	public Map deleteFileByKey(String key) throws ServiceException {
		//构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Zone.zone0());
		//...其他参数参考类注释
		QNInfo qnObj = ConfigService.getQninfo();

		String accessKey = qnObj.getAccessKey();
		String secretKey = qnObj.getSecretKey();
		String bucket = qnObj.getBucketName();
		Auth auth = Auth.create(accessKey, secretKey);
		BucketManager bucketManager = new BucketManager(auth, cfg);
		try {
		    bucketManager.delete(bucket, key);
		} catch (QiniuException ex) {
		    //如果遇到异常，说明删除失败
		   // System.err.println(ex.code());
		   // System.err.println(ex.response.toString());
		    return ReturnResult.build(ex.code(), ex.response.toString(), null, null, null, null);
		}
		return ReturnResult.ok();
	}

}

