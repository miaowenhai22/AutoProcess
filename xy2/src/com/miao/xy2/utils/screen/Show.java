package com.miao.xy2.utils.screen;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * ������ʾ����
 * @author Administrator
 *
 */
public class Show {
	private static JLabel jLabel;
	private static JFrame jFrame;

	/**
	 * �½�JFrame����
	 * @param img
	 */
	public static void createWindow(BufferedImage img) {
		// ��ʼ������̨
		jFrame = new JFrame("����̨");// ����
		jFrame.setSize(500, 400);// ��С
		// imag_lab���ڴ�Ż���
		jLabel = new JLabel();
		jFrame.add(jLabel);
		// ���ÿ���̨�ɼ�
		jFrame.setVisible(true);
		// ����̨�ö�
		jFrame.setAlwaysOnTop(true);
		// ����̨�˳�ģʽ
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ����ͼ���С��ʾ������
		update(img);
	}

	/**
	 * ����ͼƬ
	 * @param img
	 */
	public static void update(BufferedImage img) {
		if (jFrame == null)
			throw new IllegalArgumentException("����δ�½����޷�����");
		jLabel.setIcon(new ImageIcon(resize(img, jFrame.getWidth(), jFrame.getHeight())));
	}

	/**
	 * ����ͼƬ�ߴ�
	 * @param img
	 * @param newW
	 * @param newH
	 * @return
	 */
	private static BufferedImage resize(BufferedImage img, int newW, int newH) {
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
		Graphics2D g = dimg.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, 0, 0, newW, newH, 0, 0, img.getWidth(), img.getHeight(), null);
		g.dispose();
		return dimg;
	}

}
