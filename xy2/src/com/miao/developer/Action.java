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
//		// ��ȡ�������
//		Point mousepoint = MouseInfo.getPointerInfo().getLocation();
//		int startX = mousepoint.x;
//		int startY = mousepoint.y;
//		// ��ȡ�����
//		int longX = Math.abs(x - startX);
//		int longY = Math.abs(y - startY);
//		// ��ȡ�е�����
//		int centerX = x > startX ? startX + longX / 2 : x + longX;
//		int centerY = y > startY ? startY + longY / 2 : y + longY;
//		// ����뾶
//		double r = Math.sqrt(Math.pow(longX, 2) + Math.pow(longY, 2));
//		double sin = Math.sin(60*Math.PI/180);
//		double cos = Math.cos(60*Math.PI/180);
//		System.out.println(r*sin+","+r*cos);
		
		//TODO ��ӹ켣�㷨
		Robot robot = new Robot();
		robot.mouseMove(x, y);// �ƶ���굽ָ��λ��
	}

}
