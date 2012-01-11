package creative.fire.jsfcc;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;
import javax.faces.event.ActionListener;

@FacesComponent("creative.fire.jsfcc.Watermark")
public class UIWatermark extends UIOutput {
	private String textImage;

	public String getTextImage() {
		return textImage;
	}

	public void setTextImage(String textImage) {
		this.textImage = textImage;
	}

	public void addSwitchListener(ActionListener listener) {
		addFacesListener(listener);
	}
}
