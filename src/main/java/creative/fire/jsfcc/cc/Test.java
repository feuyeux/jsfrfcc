package creative.fire.jsfcc.cc;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

@ManagedBean(name = "test1")
@SessionScoped
public class Test implements Serializable {
	private static final long serialVersionUID = 6198279530814568891L;
	static Logger log = Logger.getLogger(Test.class);
	@ManagedProperty(value = "1.png")
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
