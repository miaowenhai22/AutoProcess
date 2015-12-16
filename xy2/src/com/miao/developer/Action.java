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
		// 获取当前位置
		Point mousepoint = MouseInfo.getPointerInfo().getLocation();
		// 判断2点之间距离
		int xLong = Math.abs(x - mousepoint.x);
		int yLong = Math.abs(y - mousepoint.y);
		// TODO 添加随机生成鼠标轨迹算法
		Robot robot = new Robot();
		robot.mouseMove(x, y);// 移动鼠标到指定位置
	}
	
	
}
