package com.miao.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class FileTest {
	// �����ļ�ͷ��Ϣ-�ļ�ͷ��Ϣ
	public static final HashMap<String, String> mFileTypes = new HashMap<String, String>();
	static {
		// ��֪�ļ�����ͷ����
		mFileTypes.put("FFD8FF", "jpg");
		mFileTypes.put("89504E47", "png");
		mFileTypes.put("47494638", "gif");
		mFileTypes.put("49492A00", "tif");
		mFileTypes.put("424D", "bmp");
		mFileTypes.put("41433130", "dwg"); // CAD
		mFileTypes.put("38425053", "psd");
		mFileTypes.put("7B5C727466", "rtf"); // �ռǱ�
		mFileTypes.put("3C3F786D6C", "xml");
		mFileTypes.put("68746D6C3E", "html");
		mFileTypes.put("44656C69766572792D646174653A", "eml"); // �ʼ�
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
	 * �����ļ�·����ȡ�ļ�ͷ��Ϣ
	 * 
	 * @param filePath
	 *            �ļ�·��
	 * @return �ļ�ͷ��Ϣ
	 */
	public static String getFileHeader(File file) {
		FileInputStream is = null;
		String value = null;
		try {
			is = new FileInputStream(file);
			byte[] b = new byte[4];
			/*
			 * int read() �Ӵ��������ж�ȡһ�������ֽڡ� int read(byte[] b) �Ӵ��������н���� b.length
			 * ���ֽڵ����ݶ���һ�� byte �����С� int read(byte[] b, int off, int len)
			 * �Ӵ��������н���� len ���ֽڵ����ݶ���һ�� byte �����С�
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
	 * ��ȡ�ļ�����
	 * 
	 * @param filePath
	 *            �ļ�·��
	 * @return �ļ����ͣ�nullΪ����֪����
	 */
	public static String getFileType(File file) {
		// System.out.println(getFileHeader(filePath));
		// System.out.println(mFileTypes.get(getFileHeader(filePath)));

		// ��ȡ�ļ�ͷ��Ϣ
		String fileHeader = getFileHeader(file);
		// �ж��Ƿ�Ϊ��֪����
		if (mFileTypes.containsKey(fileHeader)) {
			// ������������
			return mFileTypes.get(fileHeader);
		}
		// δ֪���ͣ�ֱ�ӷ���ͷ��Ϣ
		return fileHeader;
	}
	
	public static String getFileExtension(File file){
		String fileName = file.getName();
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}

	/**
	 * ��Ҫ��ȡ�ļ�ͷ��Ϣ���ļ���byte����ת����string���ͱ�ʾ
	 * 
	 * @param src
	 *            Ҫ��ȡ�ļ�ͷ��Ϣ���ļ���byte����
	 * @return �ļ�ͷ��Ϣ
	 */
	private static String bytesToHexString(byte[] src) {
		StringBuilder builder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		String hv;
		for (int i = 0; i < src.length; i++) {
			// ��ʮ�����ƣ����� 16���޷���������ʽ����һ�������������ַ�����ʾ��ʽ����ת��Ϊ��д
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
	 * �ļ������͹鵵
	 * @param dir Ҫ�鵵���ļ���
	 * @throws Exception
	 */
	final static void showAllFiles(File dir) throws Exception {
		File[] fs = dir.listFiles();
		for (int i = 0; i < fs.length; i++) {
			// �����ǰ·����Ŀ¼������Ŀ¼��������
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
			// �����ǰ·����Ŀ¼������Ŀ¼��������
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
		// 1.����Ŀ¼�������ļ�
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
		// //2.�½�Ŀ¼������ͬ���͵��ļ����鵵��1��Ŀ¼��

	}
}
