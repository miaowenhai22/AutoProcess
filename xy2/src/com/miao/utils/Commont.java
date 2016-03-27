/**
 * 
 */
package com.miao.utils;

import java.util.ArrayList;
import java.util.List;

/**
* @Description: 
* @author Miao
* @date 2016年1月11日 上午9:17:55
*
*/
public class Commont {
	public static List<Integer> countStr(String str1, String str2) {
		List<Integer> list = new ArrayList<>();
		if (str1.indexOf(str2) == -1) {
			return list;
		}
		int index = 0;
		int a = 0 ;
		do {
			index = str1.indexOf(str2);
			list.add(index);
			str1 = str1.substring(index + str2.length());
		} while (index != -1);
		return list;
	}

	public static void main(String[] args) {
		List<Integer> countStr = countStr("abcdabcdefda", "a");
		System.out.println(countStr.size()+"|"+countStr.toString());
	}
}
