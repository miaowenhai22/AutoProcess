package com.miao.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class MvFileTest {

	public static void main(String[] args) throws Exception {
		// �ݹ���ʾC���������ļ��м������ļ�
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
	 * @������ ��isRunning<br>
	 * @�������� ���ж�ϵͳ�����Ƿ����<br>
	 * @������ ��Andy.wang<br>
	 * @����ʱ�� ��2014-3-5����11:25:46 <br>
	 * @param exeName
	 *            ��������
	 * @return �������� ��boolean
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
//				// ��wascompress�򿪶�Ӧ�ļ�
//				Runtime.getRuntime().exec(stc + fs[i]);
//				// ��wascompress�򿪺�
//				while (!isRunning("wascompress")) {
//					Thread.sleep(1000);
//				}
//				// ����Ϊtag
//				Action.keyPressWithAlt(robot, KeyEvent.VK_F);
//				Action.keyPress(robot, KeyEvent.VK_T);
//
//				// �رմ���
//				Action.keyPressWithAlt(robot, KeyEvent.VK_F);
//				Action.keyPress(robot, KeyEvent.VK_X);
//				while(isRunning("wascompress")){
//					Thread.sleep(1000);
//					Action.keyPressWithAlt(robot, KeyEvent.VK_F);
//					Action.keyPress(robot, KeyEvent.VK_X);
//				}
//			}
//		}

		// �������Ϊ�ļ�
		File[] tag = dir.listFiles();
		String tagPath;
		int tagFileNum = 0;
		int index;
		for (int i = 0; i < tag.length; i++) {
			// ��ǰ�ļ���ΪĿ¼�Һ�Ϊtag��ʽ
			if (!tag[i].isDirectory()
					&& FileTest.getFileExtension(tag[i]).equals("tga")) {
				// ��4Ϊ��Ϊ0000���ļ���ȫ��ɾ��
				index = tag[i].getName().lastIndexOf(".");
				if (!tag[i].getName().substring(index - 4, index)
						.equals("0000")) {
					System.out.println("ɾ��" + tag[i].getName());
					tag[i].delete();
					tagFileNum++;
				}
			}
		}
		System.out
				.println("************��ɾ��" + tagFileNum + "��tga�ļ�***********");
	}
}
