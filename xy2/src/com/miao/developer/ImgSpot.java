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
 * 图形识别工具类
 * 
 * @author Administrator
 *
 */
public class ImgSpot {
	static {
		// 加载OpenCV库
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	/**
	 * 使用级联分类器，获取物体在屏幕的位置
	 * 
	 * @param imagename
	 *            图片路径
	 * @return 坐标点集合
	 */
	public static List<Point> getLocationForCascade(Mat image) {
		List<Point> request = new ArrayList<>();
		// 加载级联分类器
		CascadeClassifier faceDetector = new CascadeClassifier();
		// TODO 此处级联分类器，后期要实现动态加载
		faceDetector
				.load("D:/opencv/sources/data/haarcascades/haarcascade_frontalface_alt.xml");

		// 识别
		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(image, faceDetections);

		System.out.println(String.format("Detected %s faces",
				faceDetections.toArray().length));

		// 返回识别坐标,识别范围是矩形框，返回中心点坐标
		for (Rect rect : faceDetections.toArray()) {
			request.add(new Point(rect.x + rect.width / 2, rect.y + rect.height
					/ 2));
		}
		return request;
	}

	/**
	 * 使用模板匹配方式获取物体起始位置
	 * 
	 * @param image
	 *            欲搜索的图像
	 * @param src
	 *            目标图片
	 * 
	 * @return 目标图片所在搜索图像中的起始坐标点
	 */
	public static Point getLocationForTemplate(Mat image, String target) {
		// 加载模板图像
		Mat src = Imgcodecs.imread(target);
		// 计算搜索范围
		int iwidth = image.width() - src.width() + 1;
		int iheight = image.height() - src.height() + 1;
		// 创建结果图像
		Mat result = new Mat(new Size(iwidth, iheight), CvType.CV_32F);
		// 模板匹配图片
		Imgproc.matchTemplate(image, src, result, 0);
		// 返回取得匹配位置
		return Core.minMaxLoc(result).minLoc;
	}
}
