package creative.fire.jsfcc.bean;

import java.awt.Color;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import creative.fire.jsfcc.UIWatermark;
/**
 * @author feuyeux@gmail.com
 * @version 1.0
 */
@ManagedBean(name = "test")
@SessionScoped
public class TestBean implements Serializable {
	private static final long serialVersionUID = 6198279530814568891L;
	private boolean sw;

	@ManagedProperty(value = "1.png")
	private String image;
	@ManagedProperty(value = "water mark testing")
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

	public void switching(ActionEvent event) {
		System.out.println(event);
		UIWatermark watermark = (UIWatermark) event.getSource();
		sw = !sw;
		final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String contextPath = externalContext.getRequestContextPath() + "/resources/img/";
		String source = contextPath + image;

		if (sw)
			watermark.generateWatermark(source, text, Color.red, rotate, position);
		else
			watermark.generateWatermark(source, text, Color.blue, rotate, position);
	}
}
