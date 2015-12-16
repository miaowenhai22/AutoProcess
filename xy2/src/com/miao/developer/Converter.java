package com.miao.developer;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * 转换器
 * @author Administrator
 *
 */
public class Converter {
	static {
		// 加载OpenCV库
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	/**
	 * BufferedImage转Mat
	 * @param im
	 * @return
	 */
	public static Mat bufferedImageToMat(BufferedImage img) {
		// Convert INT to BYTE
		// im = new BufferedImage(im.getWidth(),
		// im.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
		// Convert bufferedimage to byte array
		// 当前类型不为TYPE_3BYTE_BGR时，转换其类型，否则无法转换
		BufferedImage cacheImage = null;
		if (img.getType() != BufferedImage.TYPE_3BYTE_BGR) {
			// Create a buffered image
			cacheImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
			// Draw the image onto the new buffer
			Graphics2D g = cacheImage.createGraphics();
			try {
				g.setComposite(AlphaComposite.Src);
				g.drawImage(img, 0, 0, null);
			} finally {
				g.dispose();
			}
		}
		byte[] pixels = ((DataBufferByte) cacheImage.getRaster().getDataBuffer()).getData();
		// Create a Matrix the same size of image
		Mat mat = new Mat(img.getHeight(), img.getWidth(), CvType.CV_8UC3);
		// Fill Matrix with image values
		mat.put(0, 0, pixels);
		return mat;
	}

	/**
	 * 将Mat转为BufferedImage
	* @param mat
	* @return
	*/
	public static BufferedImage matToBufferedImage(Mat mat) {
		if (mat.height() > 0 && mat.width() > 0) {
			BufferedImage cacheImage = new BufferedImage(mat.width(), mat.height(), BufferedImage.TYPE_3BYTE_BGR);
			WritableRaster raster = cacheImage.getRaster();
			DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
			byte[] data = dataBuffer.getData();
			mat.get(0, 0, data);
			return cacheImage;
		}
		return null;
	}
}
