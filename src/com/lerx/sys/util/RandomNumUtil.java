package com.lerx.sys.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

public class RandomNumUtil {

	// 图像
	private ByteArrayInputStream inputStream;
	// 验证码
	private String randomCode;

	public RandomNumUtil(int width, int height, int size, int fontSize, int mode)
			throws Exception {
		init(width, height, size, fontSize, mode);
	}

	private void init(int width, int height, int size, int fontSize, int mode)
			throws Exception {
		// 在内存中创建图像
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		// 随机生成颜色
		// g.setColor(getRandColor(160,200));
		// 固定淡颜色
		g.setColor(new Color(252, 252, 184));
		// g.setColor(getRandColor(200,250));
		g.fillRect(0, 0, width, height);
		// 设定字体

		g.setFont(new Font("Times New Roman", Font.BOLD, fontSize));
		// g.setFont(new Font("Times New Roman",Font.PLAIN,fontSize));
		// 设置边框
		g.setColor(getRandColor(200, 250));
		// g.setColor(new Color(143, 167, 131));
		g.drawRect(0, 0, width - 1, height - 1);

		// 随机产生干扰线
		g.setColor(getRandColor(160, 200));

		// g.setColor(getRandColor(160,200));
		// for (int i=0;i<155;i++)
		// {
		// int x = random.nextInt(pi_width);
		// int y = random.nextInt(pi_height);
		// int xl = random.nextInt(12);
		// int yl = random.nextInt(12);
		// g.drawLine(x,y,x+xl,y+yl);
		// }
		for (int i = 0; i < 120; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(2) + 1;
			int yl = random.nextInt(4) + 1;
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 取随机产生的认证码(size个数字)
		this.randomCode = this.getRandCode(g, size, width, height, mode);
		// 图想法生效
		g.dispose();

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
		ImageIO.write(image, "JPEG", imageOut);
		imageOut.close();
		ByteArrayInputStream input = new ByteArrayInputStream(
				output.toByteArray());
		this.setInputStream(input);
	}

	private String getRandCode(Graphics g, int size, int width, int height,
			int mode) {
		// System.out.println("mode:"+mode);
		Random random = new Random();
		char c[];
		int l;
		if (mode == 2) {
			l = 56;
			c = new char[l];

			for (int i = 97, j = 0; i < 123; i++, j++) {
				if (i == 111 || i == 105 || i == 108) { // 去除i o l
					// System.out.println((char)i);
					j--;

				} else {
					c[j] = (char) i;
				}

			}

			for (int o = 65, p = 23; o < 91; o++, p++) {
				if (o == 79 || o == 73 || o == 76) { // 去除i o l大写
					// System.out.println((char)o);
					p--;
				} else {
					c[p] = (char) o;
				}

			}
			for (int m = 48, n = 46; m < 58; m++, n++) {
				c[n] = (char) m;
			}
		} else {
			l = 10;
			c = new char[l];
			for (int m = 48, n = 0; m < 58; m++, n++) {
				c[n] = (char) m;
			}
		}

		String sRand = "";
		for (int i = 0; i < size; i++) {
			int x = random.nextInt(l);
			String rand = String.valueOf(c[x]);
			sRand += rand;
			// System.out.println("sRand:"+sRand);
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			// g.drawString(rand, 13 * i + 6, 16);

			g.drawString(rand, 13 * i + width / 10, height - 4);
		}
		return sRand;
	}

	// 给定范围获得随机颜色
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public static int getRandom(int min, int max) {
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		return s;
	}

	public void setRandomCode(String randomCode) {
		this.randomCode = randomCode;
	}

	public String getRandomCode() throws Exception {
		return this.randomCode;
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}
}
