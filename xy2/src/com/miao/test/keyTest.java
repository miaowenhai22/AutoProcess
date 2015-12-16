/**
 * 
 */
package com.miao.test;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.miao.developer.Action;
import com.miao.developer.Log;

/**
* @Description: 
* @author Miao
* @date 2015��12��14�� ����5:33:30
*
*/
public class keyTest {
	public static void main(String[] args) throws AWTException, IOException {

		Robot robot = new Robot(); // ����һ��robot����

		Runtime.getRuntime().exec("notepad"); // ��һ�����±�����

		robot.delay(2000); // �ȴ� 2��

		// �������

		keyPressWithAlt(robot, KeyEvent.VK_SPACE); // ���� alt+ �ո�

		keyPress(robot, KeyEvent.VK_X); // ����x��

		robot.delay(1000); // �ȴ� 1��

		keyPressString(robot, "��Һã�����һ��С�����ˣ����кܶ౾���� ��"); // �����ַ���

		robot.delay(3000); // �ȴ� 3��

		keyPress(robot, KeyEvent.VK_ENTER); // ���� enter ����

		keyPressString(robot, "���ڣ��Ҿ�����չʾһ��.....����"); // �����ַ���

		robot.delay(3000); // �ȴ� 3��

		keyPress(robot, KeyEvent.VK_ENTER); // ���� enter ����

		keyPressString(robot, "���ȣ����ܰ��� ���̵��κ�һ����������,�ҵ�������a,b,c,d��"); // �����ַ���

		keyPress(robot, KeyEvent.VK_ENTER); // ���� enter ����

		robot.delay(3000); // �ȴ� 3��

		keyPress(robot, KeyEvent.VK_A); // ���� a ��

		robot.delay(2000); // �ȴ� 2��

		keyPress(robot, KeyEvent.VK_B); // ���� b ��

		robot.delay(2000); // �ȴ� 2��

		keyPress(robot, KeyEvent.VK_C); // ���� c ��

		robot.delay(2000); // �ȴ� 2��

		keyPress(robot, KeyEvent.VK_D); // ���� d ��

		robot.delay(2000); // �ȴ� 2��

		keyPress(robot, KeyEvent.VK_ENTER); // ���� enter ����

		keyPressString(robot, "�Ǻǣ�����ˡ�������  ");

		robot.delay(3000); // �ȴ� 3��

		keyPress(robot, KeyEvent.VK_ENTER); // ���� enter ����

		keyPressString(robot, "��������    ���� ���ֺܶ�  �ǲ��� �е� �����أ�����     ������ �������һ�� ");

		robot.delay(2000); // �ȴ� 2��

		keyPressWithCtrl(robot, KeyEvent.VK_A); // ���� ctrl+A ȫѡ

		robot.delay(2000); // �ȴ� 2��

		keyPress(robot, KeyEvent.VK_DELETE); // ���

		robot.delay(3000); // �ȴ� 3��

		keyPressString(robot, "�������� �ǲ��� ���� ��ˬ����              ���� �һ��ᰴ ��ϼ��� ...");

		keyPress(robot, KeyEvent.VK_ENTER); // ���� enter ����

		robot.delay(3000); // �ȴ� 3��

		keyPressString(robot, "................�����Ѿ� ��ʾ���� �� ���Ǻ�    ");

		keyPress(robot, KeyEvent.VK_ENTER); // ���� enter ����

		robot.delay(3000); // �ȴ� 3��

		keyPressString(robot, "��ʵ���һ��кܶ౾����                           ���ھͲ�����չʾ�� .....");

		keyPress(robot, KeyEvent.VK_ENTER); // ���� enter ����

		robot.delay(3000); // �ȴ� 3��

		keyPressString(robot, "лл��ң���������");
	}

	// shift+ ����

	public static void keyPressWithShift(Robot r, int key) {

		r.keyPress(KeyEvent.VK_SHIFT);

		r.keyPress(key);

		r.keyRelease(key);

		r.keyRelease(KeyEvent.VK_SHIFT);

		r.delay(10);

	}

	// ctrl+ ����

	public static void keyPressWithCtrl(Robot r, int key) {

		r.keyPress(KeyEvent.VK_CONTROL);

		r.keyPress(key);

		r.keyRelease(key);

		r.keyRelease(KeyEvent.VK_CONTROL);

		r.delay(10);

	}

	// alt+ ����

	public static void keyPressWithAlt(Robot r, int key) {

		r.keyPress(KeyEvent.VK_ALT);

		r.keyPress(key);

		r.keyRelease(key);

		r.keyRelease(KeyEvent.VK_ALT);

		r.delay(10);

	}

	// ��ӡ���ַ���

	public static void keyPressString(Robot r, String str) {

		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();// ��ȡ���а�

		Transferable tText = new StringSelection(str);

		clip.setContents(tText, null); // ���ü��а�����

		keyPressWithCtrl(r, KeyEvent.VK_V);// ճ��

		r.delay(10);

	}

	// ���� ����

	public static void keyPress(Robot r, int key) {

		r.keyPress(key);

		r.keyRelease(key);

		r.delay(10);

	}
}
