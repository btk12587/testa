package com.btk.cn;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class QRCode_1 {
	public static void main(String[] args) {
		createQrcode(25, "skldjfkldsj", "qw.png", 200, 30, 50, 0,56, 180);
	}

	public static void createQrcode(int version, String str, String path,
			int startR, int startG, int startB, int endR, int endG, int endB) {
		String data="BEGIN:VCARD\r\n" + 
		   "FN:ÐÕÃû:°×Í­À¤\r\n"+
		   "TEL;CELL;VOICE:123456789\r\n"+
		   "ADR;HOME:Ð¡½ã½ã\r\n"+
		   "END:VCARD";
		Qrcode qrcode = new Qrcode();
		qrcode.setQrcodeVersion(version);
		qrcode.setQrcodeErrorCorrect('L');
		int imgSize = 67 + (version - 1) * 12;
		BufferedImage bi = new BufferedImage(imgSize, imgSize,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D gs = bi.createGraphics();
		gs.setBackground(Color.WHITE);
		gs.clearRect(0, 0, imgSize, imgSize);
		boolean[][] calQrcode = qrcode.calQrcode(data.getBytes());
		Color color;
		
		int pixoff = 2;
		for (int i = 0; i < calQrcode.length; i++) {
			for (int j = 0; j < calQrcode[i].length; j++) {
				int r = startR + (endR - startR) * (i + 1) / calQrcode.length;
				int g = startG + (endG - startG) * (i + 1) / calQrcode.length;
				int b = startB + (endB - startB) * (i + 1) / calQrcode.length;
				color = new Color(r, g, b);
				gs.setColor(color);
				if (calQrcode[i][j]) {
					gs.fillRect(i * 3 + pixoff, j * 3 + pixoff, 3, 3);
				}
			}
		}
		int len = imgSize/2-40;
		System.out.println(len);
		try {
			Image img = ImageIO.read(new File("logo.jpg"));
			gs.drawImage(img,len,len,80,80,null);
			bi.flush();
			gs.dispose();
			
			ImageIO.write(bi, "png", new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
