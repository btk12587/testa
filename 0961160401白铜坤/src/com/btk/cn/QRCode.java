package com.btk.cn;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class QRCode {
	public static void main(String[] args) throws IOException {
		int version = 5;
		// v=n 21+(n-1)*4
		int imgSize = 67 + (version - 1) * 12;
		Qrcode qrcode = new Qrcode();
		qrcode.setQrcodeVersion(version);
		BufferedImage bi = new BufferedImage(imgSize, imgSize,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D cs = bi.createGraphics();
		cs.setBackground(Color.WHITE);

		cs.clearRect(0, 0, imgSize, imgSize);
		String str = "dsfsad";
		int r = 0;
		int g = 0;
		int b = 0;

		Color color;
		boolean[][] calQrcode = qrcode.calQrcode(str.getBytes());
		for (int i = 0; i < calQrcode.length; i++) {
			for (int j = 0; j < calQrcode[i].length; j++) {
				float startR = 100f;
				float startG = 30f;
				float startB = 40f;

				float endR = 255f;
				float endG = 244f;
				float endB = 200f;

				r = (int) (startR + (endR - startR) / calQrcode.length
						* (i + 1));
				g = (int) (startG + (endG - startG) / calQrcode.length
						* (i + 1));
				b = (int) (startB + (endB - startB) / calQrcode.length
						* (i + 1));
				System.out.println(r + " " + g + " " + b);
				color = new Color(r, g, b);
				cs.setColor(color);
				if (calQrcode[i][j]) {
					cs.fillRect(i * 3 + 2, j * 3 + 2, 3, 3);
				}
			}
		}
		bi.flush();
		try {
			ImageIO.write(bi, "png", new File("D:\\q.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		cs.dispose();
	}
}

