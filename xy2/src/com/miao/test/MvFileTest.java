package com.miao.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class MvFileTest {

	public static void main(String[] args) throws Exception {
		// 递归显示C盘下所有文件夹及其中文件
		File root = new File("D:/work/was");
		showAllFiles(root);
		// Robot robot = new Robot();
		// for(int i = 0;i<=10;i++){
		// robot.delay(2000);
		// Action.keyPressWithAlt(robot, KeyEvent.VK_F);
		// Action.keyPress(robot, KeyEvent.VK_T);
		// }

	}

	/**
	 * 
	 * @方法名 ：isRunning<br>
	 * @方法描述 ：判断系统进程是否存在<br>
	 * @创建者 ：Andy.wang<br>
	 * @创建时间 ：2014-3-5上午11:25:46 <br>
	 * @param exeName
	 *            ：进程名
	 * @return 返回类型 ：boolean
	 */
	public static boolean isRunning(String exeName) {
		Process proc;
		try {
			proc = Runtime.getRuntime().exec("tasklist");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					proc.getInputStream()));
			String info = br.readLine();
			while (info != null) {
				// System.out.println(info);
				if (info.indexOf(exeName) >= 0) {
					return true;
				}
				info = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println(false);
		return false;
	}

	final static void showAllFiles(File dir) throws Exception {
//		Robot robot = new Robot();
//		File[] fs = dir.listFiles();
//		String stc = "C:/Users/mc/Desktop/GlowtoolsA-wdf/wascompress.exe ";
//		for (int i = 0; i < fs.length; i++) {
//			if (!fs[i].isDirectory()
//					&& FileTest.getFileExtension(fs[i]).equals("was")) {
//				System.out.println(i + "/" + fs.length + "|"
//						+ fs[i].getAbsolutePath());
//				// 用wascompress打开对应文件
//				Runtime.getRuntime().exec(stc + fs[i]);
//				// 当wascompress打开后
//				while (!isRunning("wascompress")) {
//					Thread.sleep(1000);
//				}
//				// 保存为tag
//				Action.keyPressWithAlt(robot, KeyEvent.VK_F);
//				Action.keyPress(robot, KeyEvent.VK_T);
//
//				// 关闭窗口
//				Action.keyPressWithAlt(robot, KeyEvent.VK_F);
//				Action.keyPress(robot, KeyEvent.VK_X);
//				while(isRunning("wascompress")){
//					Thread.sleep(1000);
//					Action.keyPressWithAlt(robot, KeyEvent.VK_F);
//					Action.keyPress(robot, KeyEvent.VK_X);
//				}
//			}
//		}

		// 清理多余为文件
		File[] tag = dir.listFiles();
		String tagPath;
		int tagFileNum = 0;
		int index;
		for (int i = 0; i < tag.length; i++) {
			// 当前文件不为目录且后为tag格式
			if (!tag[i].isDirectory()
					&& FileTest.getFileExtension(tag[i]).equals("tga")) {
				// 后4为不为0000的文件，全部删除
				index = tag[i].getName().lastIndexOf(".");
				if (!tag[i].getName().substring(index - 4, index)
						.equals("0000")) {
					System.out.println("删除" + tag[i].getName());
					tag[i].delete();
					tagFileNum++;
				}
			}
		}
		System.out
				.println("************共删除" + tagFileNum + "个tga文件***********");
	}
}
