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
	/** ��ǰ��ͼλ�� */
	private Location location;
	/** ������ʼ����� */
	private int x, y;

	private final static Logger logger = Logger.getLogger(Coordinate.class
			.getName());

	@Override
	public void run() {
		try {
			// ��������ʱ����ȡ��Ϸ����λ��
			if (this.x == 0 || this.y == 0) {
				getWindowPosition();
			}
			while (true) {
				// ��ȡ��ǰ����λ��
				BufferedImage screenshot = Screen.screenshot(x + 8, y + 9, 141,
						17);
				// ʶ��ǰ����
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
	 * ��ȡ����λ��
	 * 
	 * @throws AWTException
	 */
	private void getWindowPosition() throws AWTException {
		// ��ȡ��Ļ��С
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// ������ȡ��Ϸ����λ�ã��Դ������Ͻ�ͼƬΪ�ο�λ�û�ȡ
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
