package com.miao.xy2;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

import net.sourceforge.tess4j.TesseractException;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;

import com.miao.developer.Converter;
import com.miao.developer.ImgSpot;
import com.miao.developer.Screen;
import com.miao.developer.ocr.Location;
import com.miao.developer.ocr.Ocr;

public class Coordinate extends Thread {
	private final String name = "Coordinate";
	/** 当前地图位置 */
	private Location location;
	/** 窗口起始坐标点 */
	private int x, y;

	private final static Logger logger = Logger.getLogger(Coordinate.class
			.getName());

	@Override
	public void run() {
		try {
			// 初次启动时，获取游戏窗口位置
			if (this.x == 0 || this.y == 0) {
				getWindowPosition();
			}
			while (true) {
				// 截取当前坐标位置
				BufferedImage screenshot = Screen.screenshot(x + 8, y + 9, 141,
						17);
				// 识别当前坐标
				String str = Ocr.getLocation(screenshot);
				int start = str.indexOf("[");
				int indexOf = str.indexOf(",");
				int end = str.indexOf("]");

				if (start != -1 && indexOf != -1 && end != -1) {
					String name = str.substring(0, start);
					System.out.println(name + "|"
							+ str.substring(start + 1, indexOf) + "|"
							+ str.substring(indexOf + 1, end));
				}
				Thread.sleep(200);
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取窗口位置
	 * 
	 * @throws AWTException
	 */
	private void getWindowPosition() throws AWTException {
		// 获取屏幕大小
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 截屏获取游戏窗口位置，以窗口左上角图片为参考位置获取
		Mat screenImg = Converter.bufferedImageToMat(Screen.screenshot(0, 0,
				screenSize.width, screenSize.height));
		Imgcodecs.imwrite("d:/a.jpg", screenImg);
		Point window = ImgSpot.getLocationForTemplate(screenImg, Dw.minIcon);
		this.x = (int) (window.x);
		this.y = (int) (window.y + 23);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
