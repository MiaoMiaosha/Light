package com.light.common;

import javax.servlet.http.HttpServletResponse;

public class DomainUtil {

	public static void setDomain(HttpServletResponse response){
	    response.setHeader("Access-Control-Allow-Origin", ConstantVal.DOMAIN);
	}
}
