package com.miao.xy2.map;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

import net.sourceforge.tess4j.TesseractException;

import org.opencv.core.Mat;
import org.opencv.core.Point;

import com.miao.xy2.map.bean.Location;
import com.miao.xy2.utils.Converter;
import com.miao.xy2.utils.ImgSpot;
import com.miao.xy2.utils.Ocr;
import com.miao.xy2.utils.screen.Screen;

/**
 * ��ͼ������
 * @author mc
 *
 */
public class Maps {
	private final static Logger logger = Logger.getLogger(Maps.class.getName());
	/** ������ʼ�����x */
	private static int winX;
	/** ������ʼ�����y */
	private static int winY;


	/**
	 * ��ȡ��ǰ����λ��
	 * 
	 * @return
	 * @throws TesseractException
	 * @throws AWTException
	 */
	public static Location getLocation() throws TesseractException, AWTException {
		// ��������ʱ����ȡ��Ϸ����λ��
		if (winX == 0 || winY == 0) {
			getWindowPosition();
		}
		// ��ȡ��ǰ����λ��
		BufferedImage screenshot = Screen.screenshot(winX + 8, winY + 9, 141, 17);
		// ʶ��ǰ����
		String str = Ocr.getLocation(screenshot);
		int start = str.indexOf("[");
		int indexOf = str.indexOf(",");
		int end = str.indexOf("]");

		if (start != -1 && indexOf != -1 && end != -1) {
			String name = str.substring(0, start);
			int x = Integer.parseInt(str.substring(start + 1, indexOf));
			int y = Integer.parseInt(str.substring(indexOf + 1, end));
			return new Location(name, x, y);
		}
		return null;
	}

	/**
	 * ��ȡ����λ��
	 * 
	 */
	private static void getWindowPosition() throws AWTException {
		// ��ȡ��Ļ��С
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// ������ȡ��Ϸ����λ�ã��Դ������Ͻ�ͼƬΪ�ο�λ�û�ȡ
		Mat screenImg = Converter.bufferedImageToMat(Screen.screenshot(0, 0, screenSize.width, screenSize.height));
		Point window = ImgSpot.getLocationForTemplate(screenImg, Dw.minIcon);
		winX = (int) (window.x);
		winY = (int) (window.y + 23);
	}
}
