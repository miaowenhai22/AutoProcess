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
 * 图形识别工具类
 * @author Administrator
 *
 */
public class ImgSpot {
	static {
		// 加载OpenCV库
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	/**
	 * 获取物体在屏幕的位置
	 * @param imagename 图片路径
	 * @return 坐标点集合
	 */
	public static List<Point> getLocation(Mat image) {
		List<Point> request = new ArrayList<>();
		// 加载级联分类器
		CascadeClassifier faceDetector = new CascadeClassifier();
		// TODO 此处级联分类器，后期要实现动态加载
		faceDetector.load("D:/opencv/sources/data/haarcascades/haarcascade_frontalface_alt.xml");

		// 识别
		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(image, faceDetections);

		System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

		// 返回识别坐标,识别范围是矩形框，返回中心点坐标
		for (Rect rect : faceDetections.toArray()) {
			request.add(new Point(rect.x + rect.width / 2, rect.y + rect.height / 2));
		}
		return request;
	}
}
