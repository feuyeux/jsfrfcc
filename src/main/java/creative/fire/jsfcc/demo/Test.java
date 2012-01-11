package creative.fire.jsfcc.demo;

import java.awt.Color;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import creative.fire.jsfcc.tool.WaterMark;

@ManagedBean(name = "test")
@SessionScoped
public class Test implements Serializable{
	private boolean sw;
	@ManagedProperty(value = "1.png")
	private String image;
	@ManagedProperty(value = "水印文字测试")
	private String text;
	@ManagedProperty(value = "-5")
	private int rotate;
	private String position = "right_bottom";

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getRotate() {
		return rotate;
	}

	public void setRotate(int rotate) {
		this.rotate = rotate;
	}

	private WaterMark wm = new WaterMark();

	public void switching(ActionEvent event) {
		sw = !sw;
		final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		String filePath = externalContext.getRealPath("/") + "resources/img/";
		String contextPath = externalContext.getRequestContextPath() + "/resources/img/";

		String file = image.substring(contextPath.length(), image.length());

		String source = filePath + file;
		
		wm.generate(source, text, Color.BLUE, rotate, position);
		if (sw)
			wm.generate(source, text, Color.red, rotate, position);
		else
			wm.generate(source, text, Color.blue, rotate, position);
	}
}
