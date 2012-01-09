package creative.fire.jsfcc.demo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "test")
@ViewScoped
public class Test {
	@ManagedProperty(value = "1.png")
	private String image;
	@ManagedProperty(value = "水印文字测试")
	private String text;
	@ManagedProperty(value = "-5")
	private int rotate;

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
}
