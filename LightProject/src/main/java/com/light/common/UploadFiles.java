package com.light.common;

import com.qiniu.util.Auth;

public class UploadFiles {
	
    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public static String getUploadToken(){
	    Auth auth = Auth.create(ConstantVal.QN_ACCESS_KEY, ConstantVal.QN_SECRET_KEY);
        return auth.uploadToken(ConstantVal.QN_BUCKET_NAME);
	}
}
