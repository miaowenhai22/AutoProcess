/**
 * 
 */
package com.miao.developer;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

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
	 * 
	 * @param x
	 * @param y
	 * @throws AWTException
	 */
	public static void mouseTo(int x, int y) throws AWTException {
		// 获取起点坐标
		Point mousepoint = MouseInfo.getPointerInfo().getLocation();
		int startX = mousepoint.x;
		int startY = mousepoint.y;
		System.out.println(startX + "|" + startY);
		// // 获取坐标差
		// int longX = Math.abs(x - startX);
		// int longY = Math.abs(y - startY);
		// // 获取中点坐标
		// int centerX = x > startX ? startX + longX / 2 : x + longX;
		// int centerY = y > startY ? startY + longY / 2 : y + longY;
		// // 计算半径
		// double r = Math.sqrt(Math.pow(longX, 2) + Math.pow(longY, 2));
		// double sin = Math.sin(60*Math.PI/180);
		// double cos = Math.cos(60*Math.PI/180);
		// System.out.println(r*sin+","+r*cos);

		// TODO 添加轨迹算法
		// Robot robot = new Robot();
		// robot.mouseMove(x, y);// 移动鼠标到指定位置
	}

	// shift+ 按键

	public static void keyPressWithShift(Robot r, int key) {

		r.keyPress(KeyEvent.VK_SHIFT);

		r.keyPress(key);

		r.keyRelease(key);

		r.keyRelease(KeyEvent.VK_SHIFT);

		r.delay(100);

	}

	// ctrl+ 按键

	public static void keyPressWithCtrl(Robot r, int key) {

		r.keyPress(KeyEvent.VK_CONTROL);

		r.keyPress(key);

		r.keyRelease(key);

		r.keyRelease(KeyEvent.VK_CONTROL);

		r.delay(100);

	}

	// alt+ 按键

	public static void keyPressWithAlt(Robot r, int key) {

		r.keyPress(KeyEvent.VK_ALT);

		r.keyPress(key);

		r.keyRelease(key);

		r.keyRelease(KeyEvent.VK_ALT);

		r.delay(100);

	}

	// 打印出字符串

	public static void keyPressString(Robot r, String str) {

		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();// 获取剪切板

		Transferable tText = new StringSelection(str);

		clip.setContents(tText, null); // 设置剪切板内容

		keyPressWithCtrl(r, KeyEvent.VK_V);// 粘贴

		r.delay(100);

	}

	// 单个 按键

	public static void keyPress(Robot r, int key) {

		r.keyPress(key);

		r.keyRelease(key);

		r.delay(100);

	}

}
