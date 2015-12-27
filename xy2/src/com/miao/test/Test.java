/**
 * 
 */
package com.miao.test;

import java.awt.AWTException;
import java.awt.image.BufferedImage;

import org.opencv.core.Mat;
import org.opencv.core.Point;

import com.miao.developer.Converter;
import com.miao.developer.ImgSpot;
import com.miao.developer.Screen;
import com.miao.xy2.Coordinate;
import com.miao.xy2.Dw;

/**
 * @Description:
 * @author Miao
 * @date 2015年12月15日 下午4:34:26
 *
 */
public class Test {

	public static void main(String[] args) throws AWTException {
	Coordinate c = new Coordinate();
	c.run();
	System.out.println(c.getX()+","+c.getY());
	}
}
