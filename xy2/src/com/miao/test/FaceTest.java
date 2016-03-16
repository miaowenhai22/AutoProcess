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
 * ����ʶ�����
 * @author mc
 *
 */
public class FaceTest {
	public static void main(String[] args) throws AWTException {
		//���ؿ�
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		System.out.println("\nRunning FaceDetector");
		//��������������
		CascadeClassifier faceDetector = new CascadeClassifier();
		//���ط�����
		 faceDetector.load("D:/opencv/3.1/sources/data/haarcascades/haarcascade_frontalface_alt.xml");
		//����ͼƬ
		Mat image = Imgcodecs.imread("D:/shekhar.jpg");
		 
		//����
		MatOfRect faceDetections = new MatOfRect();
		//ʶ����
		faceDetector.detectMultiScale(image, faceDetections);
		
		System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
		//������е�����
		for (Rect rect : faceDetections.toArray()) {
			Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
					new Scalar(0, 255, 0));
		}
		
		//������ͼƬ
		String filename = "D:/ouput.png";
		System.out.println(String.format("Writing %s", filename));
		Imgcodecs.imwrite(filename, image);
	}
}
