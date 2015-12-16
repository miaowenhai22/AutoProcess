package com.miao.developer;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.objdetect.CascadeClassifier;

/**
 * ͼ��ʶ�𹤾���
 * @author Administrator
 *
 */
public class ImgSpot {
	static {
		// ����OpenCV��
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	/**
	 * ��ȡ��������Ļ��λ��
	 * @param imagename ͼƬ·��
	 * @return ����㼯��
	 */
	public static List<Point> getLocation(Mat image) {
		List<Point> request = new ArrayList<>();
		// ���ؼ���������
		CascadeClassifier faceDetector = new CascadeClassifier();
		// TODO �˴�����������������Ҫʵ�ֶ�̬����
		faceDetector.load("D:/opencv/sources/data/haarcascades/haarcascade_frontalface_alt.xml");

		// ʶ��
		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(image, faceDetections);

		System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

		// ����ʶ������,ʶ��Χ�Ǿ��ο򣬷������ĵ�����
		for (Rect rect : faceDetections.toArray()) {
			request.add(new Point(rect.x + rect.width / 2, rect.y + rect.height / 2));
		}
		return request;
	}
}
