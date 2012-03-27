package creative.fire.jsfcc;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;

class WaterMark implements Serializable {
	private static final long serialVersionUID = 6031549489202326512L;

	String generate(String image, String text, Color color, Integer rotate, String position) {
		String textImage = image.substring(0, image.lastIndexOf(".")) + "_watermark_" + System.currentTimeMillis() + ".png";
		InputStream is = null;
		OutputStream os = null;

		try {
			Image srcImg = ImageIO.read(new File(image));
			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
			int width = buffImg.getWidth();
			int height = buffImg.getHeight();
			// 得到画笔对象
			// Graphics g= buffImg.getGraphics();
			Graphics2D g = buffImg.createGraphics();
			// 设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
			g.rotate(Math.toRadians(rotate), (double) width / 2, (double) height / 2);
			// 设置颜色
			g.setColor(color);
			// 设置 Font
			g.setFont(new Font("微软雅黑", Font.BOLD, 18));

			float alpha = 0.5f;
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

			// 第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y) .
			int xoffset = 0;
			int yoffset = 0;
			int len = text.length() * 20;
			if (position.equals("right_bottom")) {
				xoffset = width - len;
				yoffset = height - 30;
			}
			g.drawString(text, xoffset, yoffset);
			g.dispose();
			os = new FileOutputStream(textImage);
			// 生成图片
			ImageIO.write(buffImg, "PNG", os);
			String result = textImage.substring(textImage.lastIndexOf("/") + 1, textImage.length());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != is)
					is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}