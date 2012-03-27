package creative.fire.jsfcc.util;

import java.awt.AlphaComposite;
import java.awt.Color;
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

public class WaterMark implements Serializable {
	private static final long serialVersionUID = 6031549489202326512L;

	public String generate(String image, String text, Color color, Integer rotate, String position) {
		String textImage = image.substring(0, image.lastIndexOf(".")) + "_watermark_" + System.currentTimeMillis() + ".png";
		InputStream is = null;
		OutputStream os = null;

		try {
			Image srcImg = ImageIO.read(new File(image));
			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
			int width = buffImg.getWidth();
			int height = buffImg.getHeight();
			Graphics2D g = buffImg.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
			g.rotate(Math.toRadians(rotate), (double) width / 2, (double) height / 2);
			g.setColor(color);
			float alpha = 0.5f;
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
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