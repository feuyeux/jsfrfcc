package creative.fire.jsfcc;

import java.awt.Color;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionListener;

import creative.fire.jsfcc.util.WaterMark;
/**
 * @author feuyeux@gmail.com
 * @version 1.0
 */
@FacesComponent("creative.fire.jsfcc.Watermark")
public class UIWatermark extends UIOutput {
	private String textImage;
	private ActionListener switchListener;

	public String getTextImage() {
		return textImage;
	}

	public void addSwitchListener(ActionListener switchListener) {
		if (switchListener != null) {
			addFacesListener(switchListener);
		}
		this.switchListener = switchListener;
	}

	public void removeSwitchListener() {
		if (switchListener != null) {
			removeFacesListener(switchListener);
			switchListener = null;
		}
	}

	public String generateWatermark(String image, String text, Color color, Integer rotate, String position) {
		final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		String filePath = externalContext.getRealPath("/") + "resources/img/";
		String contextPath = externalContext.getRequestContextPath() + "/resources/img/";
		String file = image.substring(contextPath.length(), image.length());
		String source = filePath + file;

		WaterMark wm = new WaterMark();
		textImage = contextPath + wm.generate(source, text, color, rotate, position);
		return textImage;
	}
}
