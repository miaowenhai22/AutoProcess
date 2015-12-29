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
		// 加载库
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		// 加载模板图像
		Mat src = Imgcodecs.imread("D:/src.jpg");
		// 加载要搜索的图像
		Mat pic = Imgcodecs.imread("D:/pic.jpg");

		// 计算搜索范围
		int iwidth = pic.width() - src.width() + 1;
		int iheight = pic.height() - src.height() + 1;
		// 创建结果图像
		Mat result = new Mat(new Size(iwidth, iheight), CvType.CV_32F);
		// 模板匹配图片
		Imgproc.matchTemplate(pic, src, result, 0);
		// 取得匹配位置
		MinMaxLocResult minMaxLoc = Core.minMaxLoc(result);
		// 矩形框选结果
		// minMaxLoc.minLoc返回的是point对象既为匹配度最高的结果位置
		Imgproc.rectangle(pic, minMaxLoc.minLoc, new Point(minMaxLoc.minLoc.x
				+ src.width(), minMaxLoc.minLoc.y + src.height()), new Scalar(
				0, 255, 0));

		Imgcodecs.imwrite("1.jpg", pic);

	}

}
