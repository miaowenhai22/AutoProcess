package com.miao.developer;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

/**
 * ÆÁÄ»¹¤¾ß
 * @author mc
 *
 */
public class Screen {

	/**
	 * ½ØÈ¡ÆÁÄ»Í¼Ïñ
	 * @param x 
	 * @param y
	 * @param width
	 * @param height
	 * @return 
	 * @throws AWTException
	 */
	public static BufferedImage screenshot(int x, int y, int width, int height) throws AWTException {
		Robot robot = new Robot();
		Rectangle screenRect = new Rectangle(x, y, width, height);
		return robot.createScreenCapture(screenRect);
	}
}
