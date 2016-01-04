/**
 * 
 */
package com.miao.test;

import java.awt.AWTException;
import java.awt.Robot;

import com.miao.developer.Action;

/**
* @Description: 
* @author Miao
* @date 2016年1月4日 上午10:41:40
*
*/
public class MouseClickTest {
	public static void main(String[] args) {
		try {
			Robot r = new Robot();

			while (true) {
				Action.leftClick(r);
				Thread.sleep(1000);
			}
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
