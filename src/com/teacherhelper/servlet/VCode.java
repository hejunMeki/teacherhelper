package com.teacherhelper.servlet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * time：2017/05/25
 */
public class VCode {

	private int w; // 图片宽
	private int h;// 图片高
	private Color bgColor = new Color(240, 240, 240);// 背景色
	private Random random = new Random();// 随机对象
	// 设置字体范围
	private String[] fontNames = { "宋体", "华文楷体", "黑体", "华文新魏", "华文隶书", "微软雅黑", "楷体" };
	// 设置字体样式范围
	private int[] fontstyle = { 0, 1, 2, 3 };
	// 设置字号范围
	private int[] fontSizes = { 24, 25, 26, 27, 28 };
	// 设置所有字符串范围
	private String codes = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private StringBuilder strbdr;
	// 无参数构造函数
	public VCode() {
		strbdr=new StringBuilder();   //初始化
	}

	// 带宽和高的构造函数
	public VCode(int w, int h) {
		this.w = w;
		this.h = h;
		strbdr=new StringBuilder();   //初始化
	}

	// 返回一张背景图片
	private BufferedImage createImage() {
		/**
		 * 1.创建图片 2.设置背景色
		 */
		// 创建图片
		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		// 设置背景色
		Graphics g = img.getGraphics();
		g.setColor(bgColor);
		g.fillRect(0, 0, w, h);
		return img;
	}

	// 随机返回字体颜色
	private Color randomColor() {
		int r = random.nextInt(256);
		int g = random.nextInt(256);
		int b = random.nextInt(256);
		return new Color(r, g, b);
	}

	// 随机返回字体样式
	private Font randomFont() {
		// 随机生成字体下标，随机从给定的范围内获取一个字体
		int index = random.nextInt(fontNames.length);
		String name = fontNames[index];

		// 随机生成字体样式下标，随即从给定的范围内获取一个字体样式
		index = random.nextInt(fontstyle.length);
		int style = fontstyle[index];

		// 随机生成字体大小下标，随机从给定的范围内获取一个字体大小
		index = random.nextInt(fontSizes.length);
		int size = fontSizes[index];

		return new Font(name, style, size);
	}

	// 随机返回字体内容
	private String randomChar() {
		int index = random.nextInt(codes.length());
		String str=codes.charAt(index) + "";
		strbdr.append(str);
		return str;      //strbdr     codes.charAt(index) + ""
	}
	/*
	 * 将生成的验证码返回
	 */
	public String getStrbdr()
	{
		return strbdr.toString();
	}

	// 随机返回几条干扰线
	private void getLine(BufferedImage img) {
		// 设置干扰线的宽度为1.5倍宽，随机画五条
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(1.5f));
		for (int i = 0; i < 5; i++) {
			int x1 = random.nextInt(w);
			int y1 = random.nextInt(h);
			int x2 = random.nextInt(w);
			int y2 = random.nextInt(h);
			g.drawLine(x1, y1, x2, y2);
		}
	}

	// 用户调用该方法获取图片
	public BufferedImage getImage() {
		/**
		 * 随机生成字符0-9A-Za-z,设置字体，字号，是否粗体，字符颜色，都是随机的
		 */
		BufferedImage img = createImage();

		this.getLine(img);

		// 获取画笔
		Graphics g = img.getGraphics();

		// 画内容
		for (int i = 0; i < 4; i++) {
			g.setColor(this.randomColor());// 获取随机颜色
			g.setFont(this.randomFont());// 获取随机字体
			g.drawString(this.randomChar(), w / 4 * i, h - 5);// 获取字符串随机内容
		}
		return img;
	}

	// 用户调用该方法保存图片到本地
	public void saveImage(BufferedImage img, OutputStream ous) {

		try {
			ImageIO.write(img, "JPEG", ous);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 测试
	 */
//	public static void main(String[] args) {
//		VCode v = new VCode(70,35); 
//		  BufferedImage img = v.getImage(); 
//		  System.out.println(v.getStrbdr());
//	}
}
