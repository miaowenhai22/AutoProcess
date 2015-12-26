package com.miao.developer;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 * ͼ��ʶ�𹤾���
 * 
 * @author Administrator
 *
 */
public class ImgSpot {
	static {
		// ����OpenCV��
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	/**
	 * ʹ�ü�������������ȡ��������Ļ��λ��
	 * 
	 * @param imagename
	 *            ͼƬ·��
	 * @return ����㼯��
	 */
	public static List<Point> getLocationForCascade(Mat image) {
		List<Point> request = new ArrayList<>();
		// ���ؼ���������
		CascadeClassifier faceDetector = new CascadeClassifier();
		// TODO �˴�����������������Ҫʵ�ֶ�̬����
		faceDetector
				.load("D:/opencv/sources/data/haarcascades/haarcascade_frontalface_alt.xml");

		// ʶ��
		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(image, faceDetections);

		System.out.println(String.format("Detected %s faces",
				faceDetections.toArray().length));

		// ����ʶ������,ʶ��Χ�Ǿ��ο򣬷������ĵ�����
		for (Rect rect : faceDetections.toArray()) {
			request.add(new Point(rect.x + rect.width / 2, rect.y + rect.height
					/ 2));
		}
		return request;
	}

	/**
	 * ʹ��ģ��ƥ�䷽ʽ��ȡ������ʼλ��
	 * 
	 * @param image
	 *            ��������ͼ��
	 * @param src
	 *            Ŀ��ͼƬ
	 * 
	 * @return Ŀ��ͼƬ��������ͼ���е���ʼ�����
	 */
	public static Point getLocationForTemplate(Mat image, String target) {
		// ����ģ��ͼ��
		Mat src = Imgcodecs.imread(target);
		// ����������Χ
		int iwidth = image.width() - src.width() + 1;
		int iheight = image.height() - src.height() + 1;
		// �������ͼ��
		Mat result = new Mat(new Size(iwidth, iheight), CvType.CV_32F);
		// ģ��ƥ��ͼƬ
		Imgproc.matchTemplate(image, src, result, 0);
		// ����ȡ��ƥ��λ��
		return Core.minMaxLoc(result).minLoc;
	}
}
