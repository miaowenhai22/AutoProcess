package com.miao.xy2;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;

import com.miao.developer.Converter;
import com.miao.developer.ImgSpot;
import com.miao.developer.Screen;

public class Coordinate extends Thread {
	private final String name = "Coordinate";
	private Point current;
	/** ������ʼ����� */
	private int x, y;

	@Override
	public void run() {
		try {
			// ��������ʱ����ȡ��Ϸ����λ��
			if (this.x == 0 || this.y == 0) {
				getWindowPosition();
			}

			BufferedImage screenshot = Screen
					.screenshot(x + 8, y + 9, 141, 17);

			Imgcodecs.imwrite("d:/1.jpg",
					Converter.bufferedImageToMat(screenshot));

		} catch (AWTException e) {
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
