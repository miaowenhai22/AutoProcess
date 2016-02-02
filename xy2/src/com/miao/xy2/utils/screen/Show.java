package com.miao.xy2.utils.screen;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 窗口显示工具
 * @author Administrator
 *
 */
public class Show {
	private static JLabel jLabel;
	private static JFrame jFrame;

	/**
	 * 新建JFrame窗口
	 * @param img
	 */
	public static void createWindow(BufferedImage img) {
		// 初始化控制台
		jFrame = new JFrame("控制台");// 标题
		jFrame.setSize(500, 400);// 大小
		// imag_lab用于存放画面
		jLabel = new JLabel();
		jFrame.add(jLabel);
		// 设置控制台可见
		jFrame.setVisible(true);
		// 控制台置顶
		jFrame.setAlwaysOnTop(true);
		// 控制台退出模式
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 适配图像大小显示到窗口
		update(img);
	}

	/**
	 * 更新图片
	 * @param img
	 */
	public static void update(BufferedImage img) {
		if (jFrame == null)
			throw new IllegalArgumentException("窗口未新建，无法更新");
		jLabel.setIcon(new ImageIcon(resize(img, jFrame.getWidth(), jFrame.getHeight())));
	}

	/**
	 * 设置图片尺寸
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
