package com.miao.test;

import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class FindPic {
	public static void main(String[] args) {
		// ���ؿ�
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		// ����ģ��ͼ��
		Mat src = Imgcodecs.imread("D:/src.jpg");
		// ����Ҫ������ͼ��
		Mat pic = Imgcodecs.imread("D:/pic.jpg");

		// ����������Χ
		int iwidth = pic.width() - src.width() + 1;
		int iheight = pic.height() - src.height() + 1;
		// �������ͼ��
		Mat result = new Mat(new Size(iwidth, iheight), CvType.CV_32F);
		// ģ��ƥ��ͼƬ
		Imgproc.matchTemplate(pic, src, result, 0);
		// ȡ��ƥ��λ��
		MinMaxLocResult minMaxLoc = Core.minMaxLoc(result);
		// ���ο�ѡ���
		// minMaxLoc.minLoc���ص���point�����Ϊƥ�����ߵĽ��λ��
		Imgproc.rectangle(pic, minMaxLoc.minLoc, new Point(minMaxLoc.minLoc.x
				+ src.width(), minMaxLoc.minLoc.y + src.height()), new Scalar(
				0, 255, 0));

		Imgcodecs.imwrite("1.jpg", pic);

	}

}
