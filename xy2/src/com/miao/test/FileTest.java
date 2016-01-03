package com.miao.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class FileTest {
	// 缓存文件头信息-文件头信息
	public static final HashMap<String, String> mFileTypes = new HashMap<String, String>();
	static {
		// 已知文件类型头参数
		mFileTypes.put("FFD8FF", "jpg");
		mFileTypes.put("89504E47", "png");
		mFileTypes.put("47494638", "gif");
		mFileTypes.put("49492A00", "tif");
		mFileTypes.put("424D", "bmp");
		mFileTypes.put("41433130", "dwg"); // CAD
		mFileTypes.put("38425053", "psd");
		mFileTypes.put("7B5C727466", "rtf"); // 日记本
		mFileTypes.put("3C3F786D6C", "xml");
		mFileTypes.put("68746D6C3E", "html");
		mFileTypes.put("44656C69766572792D646174653A", "eml"); // 邮件
		mFileTypes.put("D0CF11E0", "doc");
		mFileTypes.put("5374616E64617264204A", "mdb");
		mFileTypes.put("252150532D41646F6265", "ps");
		mFileTypes.put("255044462D312E", "pdf");
		mFileTypes.put("504B0304", "docx");
		mFileTypes.put("52617221", "rar");
		mFileTypes.put("57415645", "wav");
		mFileTypes.put("41564920", "avi");
		mFileTypes.put("2E524D46", "rm");
		mFileTypes.put("000001BA", "mpg");
		mFileTypes.put("000001B3", "mpg");
		mFileTypes.put("6D6F6F76", "mov");
		mFileTypes.put("3026B2758E66CF11", "asf");
		mFileTypes.put("4D546864", "mid");
		mFileTypes.put("1F8B08", "gz");
		mFileTypes.put("4D5A9000", "exe/dll");
		mFileTypes.put("75736167", "txt");
		mFileTypes.put("53500C00", "was");
		mFileTypes.put("BDABBDA8", "wdf");
	}

	/**
	 * 根据文件路径获取文件头信息
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 文件头信息
	 */
	public static String getFileHeader(File file) {
		FileInputStream is = null;
		String value = null;
		try {
			is = new FileInputStream(file);
			byte[] b = new byte[4];
			/*
			 * int read() 从此输入流中读取一个数据字节。 int read(byte[] b) 从此输入流中将最多 b.length
			 * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len)
			 * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
			 */
			is.read(b, 0, b.length);
			value = bytesToHexString(b);
		} catch (Exception e) {
		} finally {
			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
		return value;
	}

	/**
	 * 获取文件类型
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 文件类型，null为非已知类型
	 */
	public static String getFileType(File file) {
		// System.out.println(getFileHeader(filePath));
		// System.out.println(mFileTypes.get(getFileHeader(filePath)));

		// 获取文件头信息
		String fileHeader = getFileHeader(file);
		// 判断是否为已知类型
		if (mFileTypes.containsKey(fileHeader)) {
			// 返回类型名称
			return mFileTypes.get(fileHeader);
		}
		// 未知类型，直接返回头信息
		return fileHeader;
	}
	
	public static String getFileExtension(File file){
		String fileName = file.getName();
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}

	/**
	 * 将要读取文件头信息的文件的byte数组转换成string类型表示
	 * 
	 * @param src
	 *            要读取文件头信息的文件的byte数组
	 * @return 文件头信息
	 */
	private static String bytesToHexString(byte[] src) {
		StringBuilder builder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		String hv;
		for (int i = 0; i < src.length; i++) {
			// 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
			hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
			if (hv.length() < 2) {
				builder.append(0);
			}
			builder.append(hv);
		}
		// System.out.println(builder.toString());
		return builder.toString();
	}

	/**
	 * 文件按类型归档
	 * @param dir 要归档的文件夹
	 * @throws Exception
	 */
	final static void showAllFiles(File dir) throws Exception {
		File[] fs = dir.listFiles();
		for (int i = 0; i < fs.length; i++) {
			// 如果当前路径是目录，进入目录继续查找
			if (fs[i].isDirectory()) {
				try {
					showAllFiles(fs[i]);
				} catch (Exception e) {
				}
			} else {
				File file = new File("d:/work/" + getFileType(fs[i]));
				if (!file.exists()) {
					file.mkdir();
				}
				File newFile = new File(file.getAbsolutePath() + "/"
						+ fs[i].getName());
				fs[i].renameTo(newFile);
				// fs[i].delete();
			}
		}
	}

	final static void showAllFiles1(File dir) throws Exception {
		File[] fs = dir.listFiles();
		File newFile = null;
		for (int i = 0; i < fs.length; i++) {
			// 如果当前路径是目录，进入目录继续查找
			if (fs[i].isDirectory()) {
				try {
					showAllFiles(fs[i]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				String name = fs[i].getName();
				int indexOf = name.indexOf(".") + 1;
				if (name.substring(indexOf, indexOf + 2).equals("wd")) {
					System.out.println(name);
					File file = new File("d:/work/");
					if (!file.exists()) {
						file.mkdir();
					}
					newFile = new File(file.getAbsolutePath() 
							+"/"+ fs[i].getName());
					 fs[i].renameTo(newFile);
					// fs[i].delete();
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
//		 System.out.println(getFileType(new File("d:/1.mp3")));
		// 1.遍历目录下所有文件
		showAllFiles(new File("D:/work"));
		// File file = new File("d:/a.jpg");
		// File f = new File("d:/abc");
		// if(!f.exists()){
		// f.mkdirs();
		// }
		// File fileNew = new File(f.getAbsolutePath()+"/"+file.getName());
		// System.out.println(f.getAbsolutePath()+file.getName());
		//
		// file.renameTo(fileNew);
		// //2.新建目录，将相同类型的文件，归档到1个目录下

	}
}
