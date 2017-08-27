package com.teacherhelper.servlet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * time��2017/05/25
 */
public class VCode {

	private int w; // ͼƬ��
	private int h;// ͼƬ��
	private Color bgColor = new Color(240, 240, 240);// ����ɫ
	private Random random = new Random();// �������
	// �������巶Χ
	private String[] fontNames = { "����", "���Ŀ���", "����", "������κ", "��������", "΢���ź�", "����" };
	// ����������ʽ��Χ
	private int[] fontstyle = { 0, 1, 2, 3 };
	// �����ֺŷ�Χ
	private int[] fontSizes = { 24, 25, 26, 27, 28 };
	// ���������ַ�����Χ
	private String codes = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private StringBuilder strbdr;
	// �޲������캯��
	public VCode() {
		strbdr=new StringBuilder();   //��ʼ��
	}

	// ����͸ߵĹ��캯��
	public VCode(int w, int h) {
		this.w = w;
		this.h = h;
		strbdr=new StringBuilder();   //��ʼ��
	}

	// ����һ�ű���ͼƬ
	private BufferedImage createImage() {
		/**
		 * 1.����ͼƬ 2.���ñ���ɫ
		 */
		// ����ͼƬ
		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		// ���ñ���ɫ
		Graphics g = img.getGraphics();
		g.setColor(bgColor);
		g.fillRect(0, 0, w, h);
		return img;
	}

	// �������������ɫ
	private Color randomColor() {
		int r = random.nextInt(256);
		int g = random.nextInt(256);
		int b = random.nextInt(256);
		return new Color(r, g, b);
	}

	// �������������ʽ
	private Font randomFont() {
		// ������������±꣬����Ӹ����ķ�Χ�ڻ�ȡһ������
		int index = random.nextInt(fontNames.length);
		String name = fontNames[index];

		// �������������ʽ�±꣬�漴�Ӹ����ķ�Χ�ڻ�ȡһ��������ʽ
		index = random.nextInt(fontstyle.length);
		int style = fontstyle[index];

		// ������������С�±꣬����Ӹ����ķ�Χ�ڻ�ȡһ�������С
		index = random.nextInt(fontSizes.length);
		int size = fontSizes[index];

		return new Font(name, style, size);
	}

	// ���������������
	private String randomChar() {
		int index = random.nextInt(codes.length());
		String str=codes.charAt(index) + "";
		strbdr.append(str);
		return str;      //strbdr     codes.charAt(index) + ""
	}
	/*
	 * �����ɵ���֤�뷵��
	 */
	public String getStrbdr()
	{
		return strbdr.toString();
	}

	// ������ؼ���������
	private void getLine(BufferedImage img) {
		// ���ø����ߵĿ��Ϊ1.5�������������
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

	// �û����ø÷�����ȡͼƬ
	public BufferedImage getImage() {
		/**
		 * ��������ַ�0-9A-Za-z,�������壬�ֺţ��Ƿ���壬�ַ���ɫ�����������
		 */
		BufferedImage img = createImage();

		this.getLine(img);

		// ��ȡ����
		Graphics g = img.getGraphics();

		// ������
		for (int i = 0; i < 4; i++) {
			g.setColor(this.randomColor());// ��ȡ�����ɫ
			g.setFont(this.randomFont());// ��ȡ�������
			g.drawString(this.randomChar(), w / 4 * i, h - 5);// ��ȡ�ַ����������
		}
		return img;
	}

	// �û����ø÷�������ͼƬ������
	public void saveImage(BufferedImage img, OutputStream ous) {

		try {
			ImageIO.write(img, "JPEG", ous);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * ����
	 */
//	public static void main(String[] args) {
//		VCode v = new VCode(70,35); 
//		  BufferedImage img = v.getImage(); 
//		  System.out.println(v.getStrbdr());
//	}
}
