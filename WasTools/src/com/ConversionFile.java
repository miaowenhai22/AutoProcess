/**
 * 
 */
package com;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.wildbean.wastools.core.WasTools;

/**
* @Description: 
* @author Miao
* @date 2016年1月6日 上午11:35:45
*
*/
public class ConversionFile {
	public static void main(String[] args) {
		String path = "D:\\Desktop\\aa";
		List<Thread> list = new ArrayList<>();
		Thread toolThread;
		if (args.length > 0 && args.length == 1) {
			path = args[0];
		}
		long time = System.currentTimeMillis();
		// 创建线程转换所有was文件
		File[] files = new File(path).listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				toolThread = new Thread(new Runnable() {
					public void run() {
						WasTools.main(new String[] { file.getAbsolutePath() });
					}
				}, file.getName());
				toolThread.start();
				list.add(toolThread);
			}
		}

		// 等待所有线程结束
		if (list.size() > 0) {
			for (Thread t : list) {
				while (t.isAlive()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		System.out.println("转换完成用时" + ((System.currentTimeMillis() - time)));
		System.exit(0);
	}
}
