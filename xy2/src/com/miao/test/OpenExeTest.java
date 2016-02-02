package com.miao.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class OpenExeTest {
	// 第一种方式：利用cmd方式
	/**
	 * 执行cmd命令
	 * 
	 * @param command
	 * @throws IOException
	 */
	public static String executeCmd(String command) throws IOException {
		// log.info("Execute command : " + command);
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec("cmd /c " + command);
		BufferedReader br = new BufferedReader(new InputStreamReader(
				process.getInputStream(), "UTF-8"));
		String line = null;
		StringBuilder build = new StringBuilder();
		while ((line = br.readLine()) != null) {
			// log.info(line);
			build.append(line);
		}
		return build.toString();
	}

	// 第二种方式：利用<span
	// style="font-size: 1em; line-height: 1.5;">ProcessBuilder调用cmd方式</span>
	/**
	 * 启动应用程序
	 * 
	 * @param programName
	 * @return
	 * @throws IOException
	 */
	public static void startProgram(String programPath) throws IOException {
		// log.info("启动应用程序：" + programPath);
		// if (StringUtils.isNotBlank(programPath)) {
		try {
			String programName = programPath.substring(
					programPath.lastIndexOf("/") + 1,
					programPath.lastIndexOf("."));
			List<String> list = new ArrayList<String>();
			list.add("cmd.exe");
			list.add("/c");
			list.add("start");
			list.add("\"" + programName + "\"");
			list.add("\"" + programPath + "\"");
			ProcessBuilder pBuilder = new ProcessBuilder(list);
			pBuilder.start();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("应用程序：" + programPath + "不存在！");
		}
		// }
	}

	public static void main(String[] args) throws IOException {

		executeCmd("C:/Users/mc/Desktop/GlowtoolsA-wdf/wascompress.exe F:/新建文件夹/shape.wdl.files/0008-6d8f7435.was");
		// startProgram("explorer");
	}

	/**
	 * Moving a File to Another Directory
	 * 
	 * @param srcFile
	 *            eg: c:\windows\abc.txt
	 * @param destPath
	 *            eg: c:\temp
	 * @return success
	 */
	public static boolean move(String srcFile, String destPath) {
		// File (or directory) to be moved
		File file = new File(srcFile);

		// Destination directory
		File dir = new File(destPath);

		// Move file to new directory
		boolean success = file.renameTo(new File(dir, file.getName()));

		return success;
	}

}
