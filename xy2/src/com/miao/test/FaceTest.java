package com.miao.test;

import java.awt.AWTException;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 * 人脸识别测试
 * @author mc
 *
 */
public class FaceTest {
	public static void main(String[] args) throws AWTException {
		//加载库
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		System.out.println("\nRunning FaceDetector");
		//创建级联分类器
		CascadeClassifier faceDetector = new CascadeClassifier();
		//加载分类器
		 faceDetector.load("D:/opencv/3.1/sources/data/haarcascades/haarcascade_frontalface_alt.xml");
		//加载图片
		Mat image = Imgcodecs.imread("D:/shekhar.jpg");
		 
		//矩形
		MatOfRect faceDetections = new MatOfRect();
		//识别脸
		faceDetector.detectMultiScale(image, faceDetections);
		
		System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
		//框出所有的人脸
		for (Rect rect : faceDetections.toArray()) {
			Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
					new Scalar(0, 255, 0));
		}
		
		//输出结果图片
		String filename = "D:/ouput.png";
		System.out.println(String.format("Writing %s", filename));
		Imgcodecs.imwrite(filename, image);
	}
}
