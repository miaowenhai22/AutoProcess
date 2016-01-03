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
 * @date 2015��12��15�� ����9:32:17
 *
 */
public class Action {

	/**
	 * ��굽ָ��λ��
	 * 
	 * @param x
	 * @param y
	 * @throws AWTException
	 */
	public static void mouseTo(int x, int y) throws AWTException {
		// ��ȡ�������
		Point mousepoint = MouseInfo.getPointerInfo().getLocation();
		int startX = mousepoint.x;
		int startY = mousepoint.y;
		System.out.println(startX + "|" + startY);
		// // ��ȡ�����
		// int longX = Math.abs(x - startX);
		// int longY = Math.abs(y - startY);
		// // ��ȡ�е�����
		// int centerX = x > startX ? startX + longX / 2 : x + longX;
		// int centerY = y > startY ? startY + longY / 2 : y + longY;
		// // ����뾶
		// double r = Math.sqrt(Math.pow(longX, 2) + Math.pow(longY, 2));
		// double sin = Math.sin(60*Math.PI/180);
		// double cos = Math.cos(60*Math.PI/180);
		// System.out.println(r*sin+","+r*cos);

		// TODO ��ӹ켣�㷨
		// Robot robot = new Robot();
		// robot.mouseMove(x, y);// �ƶ���굽ָ��λ��
	}

	// shift+ ����

	public static void keyPressWithShift(Robot r, int key) {

		r.keyPress(KeyEvent.VK_SHIFT);

		r.keyPress(key);

		r.keyRelease(key);

		r.keyRelease(KeyEvent.VK_SHIFT);

		r.delay(100);

	}

	// ctrl+ ����

	public static void keyPressWithCtrl(Robot r, int key) {

		r.keyPress(KeyEvent.VK_CONTROL);

		r.keyPress(key);

		r.keyRelease(key);

		r.keyRelease(KeyEvent.VK_CONTROL);

		r.delay(100);

	}

	// alt+ ����

	public static void keyPressWithAlt(Robot r, int key) {

		r.keyPress(KeyEvent.VK_ALT);

		r.keyPress(key);

		r.keyRelease(key);

		r.keyRelease(KeyEvent.VK_ALT);

		r.delay(100);

	}

	// ��ӡ���ַ���

	public static void keyPressString(Robot r, String str) {

		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();// ��ȡ���а�

		Transferable tText = new StringSelection(str);

		clip.setContents(tText, null); // ���ü��а�����

		keyPressWithCtrl(r, KeyEvent.VK_V);// ճ��

		r.delay(100);

	}

	// ���� ����

	public static void keyPress(Robot r, int key) {

		r.keyPress(key);

		r.keyRelease(key);

		r.delay(100);

	}

}
