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
* @date 2015��12��15�� ����9:32:17
*
*/
public class Action {

	/**
	 * ��굽ָ��λ��
	 * @param x
	 * @param y
	 * @throws AWTException 
	 */
	public static void mouseTo(int x, int y) throws AWTException {
		// ��ȡ��ǰλ��
		Point mousepoint = MouseInfo.getPointerInfo().getLocation();
		// �ж�2��֮�����
		int xLong = Math.abs(x - mousepoint.x);
		int yLong = Math.abs(y - mousepoint.y);
		// TODO �������������켣�㷨
		Robot robot = new Robot();
		robot.mouseMove(x, y);// �ƶ���굽ָ��λ��
	}
	
	
}
