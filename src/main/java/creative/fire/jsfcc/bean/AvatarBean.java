package creative.fire.jsfcc.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

@ManagedBean
@SessionScoped
public class AvatarBean implements Serializable {
	private static final long serialVersionUID = 6198279530814568891L;
	static Logger log = Logger.getLogger(AvatarBean.class);
	@ManagedProperty(value = "0.png")
	private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String switching() {
		log.info("switching");
		return null;
	}
}
