package com.light.common;

import java.util.Random;

public class UidMake {

	public static String getRandomCharAndNumr(Integer length){
		String string="";
		Random random = new Random();
		for(int i=0;i<length;i++){
			boolean b = random.nextBoolean();
			if(b){//字符串
				string += (char)(65+random.nextInt(26));//大写字母
			}else{
				string += String.valueOf(random.nextInt(10));
			}
		}
		return string;
	}
	
	public static String getUid(){
		return FormatUtil.timeStampInt()+getRandomCharAndNumr(4);
	}
	
	public static void main(String args[]){
		System.out.println(getUid());
	}
}
