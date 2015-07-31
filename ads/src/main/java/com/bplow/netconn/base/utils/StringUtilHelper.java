package com.bplow.netconn.base.utils;

/**
 * 
 * 
 * @author wb-wangxiaolei.xl
 * @version $Id: StringUtilHelper.java, v 0.1 2015年4月29日 下午4:19:32 wb-wangxiaolei.xl Exp $
 */
public class StringUtilHelper {
	
	public static String converter(String str){
		StringBuilder sb =new StringBuilder();
		char[] cr = str.toCharArray();
		for(int i = 0;i<cr.length;i++){
			if('_' == cr[i] && (i+1) < cr.length ){
				char tmp = cr[i+1];
				sb.append(Character.toUpperCase(tmp));
				i++;
			}else if(i+1 < cr.length){
				sb.append(cr[i]);
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(StringUtilHelper.converter("open_book_read_Open_DDDD_"));
	}

}
