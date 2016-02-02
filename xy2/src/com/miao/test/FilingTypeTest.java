package com.miao.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FilingTypeTest {
	private Map<String, File> map;
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

	public static String getFileExtension(File file) {
		String fileName = file.getName();
		return fileName.substring(fileName.lastIndexOf(".") + 1);
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
	 * 
	 * @param dir
	 *            Ҫ�鵵���ļ���
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
				File newFile = new File(file.getAbsolutePath() + "/" + fs[i].getName());
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
					newFile = new File(file.getAbsolutePath() + "/" + fs[i].getName());
					fs[i].renameTo(newFile);
					// fs[i].delete();
				}
			}
		}
	}

	/**
	 * �鵵��ѹ���bmp�ļ�
	 * 
	 * @param dir
	 */
	public static void removeBmpFile(File dir) {
		String path, id;
		File[] fs = dir.listFiles();
		// ɾ�����ļ�
		if (fs.length <= 0) {
			dir.delete();
			return;
		}

		for (int i = 0; i < fs.length; i++) {
			System.out.println(Thread.currentThread().getName() + ":" + i + i + "/" + fs.length);
			// �����ǰ·����Ŀ¼������Ŀ¼��������
			if (fs[i].isDirectory()) {
				// �����̴߳���
				File file = fs[i];
				new Thread(new Runnable() {
					@Override
					public void run() {
						removeBmpFile(file);
					}
				}, fs[i].getName()).start();
			} else {
				// ��ǰ�ļ���bmp�ļ�ʱ
				if (fs[i].getAbsolutePath().endsWith(".tga")) {
					path = dir.getAbsolutePath();
					// �����ǰ�ļ�����was�������ļ���
					if ( fs[i].getAbsolutePath().endsWith("00000.tga")) {
						id = path.substring(path.lastIndexOf("\\") + 1, path.lastIndexOf("_"));
						fs[i].renameTo(new File(path.substring(0, path.lastIndexOf("\\")) + "\\" + id + ".bmp"));
					} else {
						fs[i].delete();
					}
				}
			}
		}
		// ɾ���ļ�Ŀ¼
		if (dir.getAbsolutePath().endsWith("_was")) {
			dir.delete();
		}
	}

	/**
	 * ���ݵ�ǰ·���µ�����was�ļ����ƣ�������ֵ��
	 * 
	 * @param dir
	 *            �ļ���
	 * @param fileType
	 *            �ļ����� bmp
	 * @return Map<id,�ļ���.fileType>
	 */
	public static Map<String, String> getIdMap(File dir, String fileType) {
		File[] listFiles = dir.listFiles();
		Map<String, String> wasMap = new HashMap<String, String>();
		String path = "";
		String wasFileName = "";
		String substring = "";
		String id = "";
		String bmpName = "";
		for (int i = 0; i < listFiles.length; i++) {
			// ��ȡ����was�ļ�id,��������Ӧ��bmp�ļ�����
			path = listFiles[i].getAbsolutePath();
			if (path.endsWith(".was")) {
				// ��ȡwas�ļ�����
				wasFileName = listFiles[i].getName();
				// ȥ�����һ��-֮����ַ� ��000000-5f4de-0.was ����000000-5f4de
				substring = wasFileName.substring(0, wasFileName.lastIndexOf("-"));
				// ��ȡid
				id = substring.substring(substring.indexOf("-") + 1);
				// ����bmp�ļ�����
				bmpName = path.substring(0, path.lastIndexOf(".") + 1) + fileType;
				// ����map
				wasMap.put(id, bmpName);
			}
		}
		return wasMap;
	}

	/**
	 * ����id��������
	 * 
	 * @param dir
	 */
	public static void remBmpName(File dir) {
		File[] fs = dir.listFiles();
		Map<String, String> idMap = getIdMap(dir, "bmp");
		String id = "";
		for (int i = 0; i < fs.length; i++) {
			System.out.println(Thread.currentThread().getName() + ":" + i + i + "/" + fs.length);
			// �����ǰ·����Ŀ¼������Ŀ¼��������
			if (fs[i].isDirectory()) {
				// �����̴߳���
				File file = fs[i];
				new Thread(new Runnable() {
					@Override
					public void run() {
						remBmpName(file);
					}
				}, fs[i].getName()).start();
			} else {
				// ��ǰ�ļ���bmp�ļ�ʱ
				if (fs[i].getName().endsWith(".bmp")) {
					id = fs[i].getName().substring(0, fs[i].getName().lastIndexOf("."));
					fs[i].renameTo(new File(idMap.get(id)));
				}
			}
		}
	}

	public static Map<String, File> getPngMap(File dir) {
		File[] listFiles = dir.listFiles();
		Map<String, File> wasMap = new HashMap<String, File>();
		String path = "";
		String wasFileName = "";
		String substring = "";
		String id = "";
		for (int i = 0; i < listFiles.length; i++) {
			// ��ȡ����was�ļ�id,��������Ӧ��bmp�ļ�����
			path = listFiles[i].getAbsolutePath();
			if (path.endsWith(".png")) {
				// ��ȡwas�ļ�����
				wasFileName = listFiles[i].getName();

				substring = wasFileName.substring(wasFileName.indexOf("-") + 1);

				// ��ȡid
				id = substring.substring(0, substring.indexOf(".") - 4);
				// ����map
				wasMap.put(id, listFiles[i]);
			}
		}
		return wasMap;
	}

	public static void showAllFiles2(File dir, Map<String, File> map) {
		File[] fs = dir.listFiles();
		File file;
		String wasFileName;
		String substring;
		String id;
		for (int i = 0; i < fs.length; i++) {
			// �����ǰ·����Ŀ¼������Ŀ¼��������
			if (fs[i].isDirectory()) {
				showAllFiles2(fs[i], map);
			} else {
				if (fs[i].getName().endsWith(".was")) {
					String path = fs[i].getAbsolutePath();
					id = fs[i].getName().substring(0, fs[i].getName().indexOf("."));
					System.out.println(path + "|" + id);
					if (fs[i].getName().indexOf("-") != -1) {
						// ��ȡwas�ļ�����
						wasFileName = fs[i].getName();
						// ȥ�����һ��-֮����ַ� ��000000-5f4de-0.was ����000000-5f4de
						substring = wasFileName.substring(0, wasFileName.lastIndexOf("-"));
						// ��ȡid
						id = substring.substring(substring.indexOf("-") + 1);
					}
					// ��idʱ
					if (map.containsKey(id)) {
						file = map.get(id);
						file.renameTo(new File(path.substring(0, path.length() - 3) + "png"));
						file.delete();
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		 File dir = new File("F:\\games\\�½��ļ���\\shape_wdf");
		 //�鵵bmp
//		 removeBmpFile(dir);
		 //����
//		 remBmpName(dir);

		
		
		//�ɰ�pngת�°�
//		File file = new File("D:\\�½��ļ���\\png");
//		Map<String, File> pngMap = getPngMap(file);
//		showAllFiles2(new File("F:\\games\\�½��ļ���"), pngMap);
	}
}
