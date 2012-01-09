package creative.fire.jsfcc;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;

@FacesComponent("creative.fire.jsfcc.Watermark")
public class UIWatermark extends UIOutput {
	private String textImage;

	public String getTextImage() {
		return textImage;
	}

	public void setTextImage(String textImage) {
		this.textImage = textImage;
	}
}
