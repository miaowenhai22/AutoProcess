/**
 * 
 */
package com.miao.developer;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

import org.apache.log4j.Logger;

/**
* @Description: 
* @author Miao
* @date 2015年12月15日 上午9:32:17
*
*/
public class Action {

	/**
	 * 鼠标到指定位置
	 * @param x
	 * @param y
	 * @throws AWTException 
	 */
	public static void mouseTo(int x, int y) throws AWTException {
//		// 获取起点坐标
//		Point mousepoint = MouseInfo.getPointerInfo().getLocation();
//		int startX = mousepoint.x;
//		int startY = mousepoint.y;
//		// 获取坐标差
//		int longX = Math.abs(x - startX);
//		int longY = Math.abs(y - startY);
//		// 获取中点坐标
//		int centerX = x > startX ? startX + longX / 2 : x + longX;
//		int centerY = y > startY ? startY + longY / 2 : y + longY;
//		// 计算半径
//		double r = Math.sqrt(Math.pow(longX, 2) + Math.pow(longY, 2));
//		double sin = Math.sin(60*Math.PI/180);
//		double cos = Math.cos(60*Math.PI/180);
//		System.out.println(r*sin+","+r*cos);
		
		//TODO 添加轨迹算法
		Robot robot = new Robot();
		robot.mouseMove(x, y);// 移动鼠标到指定位置
	}

}
