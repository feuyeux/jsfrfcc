package creative.fire.jsfcc.env;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * @author feuyeux@gmail.com
 * @version 1.0
 */
@ManagedBean(name = "jsfrfEnv")
@ApplicationScoped
public class JSFRFEnvironment {
	private String contextPath;
	private String imagePath;

	public String getContextPath() {
		if (contextPath == null) {
			final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			contextPath = externalContext.getRequestContextPath();
		}
		return contextPath;
	}

	public String getImagePath() {
		if (imagePath == null) {
			imagePath = getContextPath() + "/resources/img/";
		}
		return imagePath;
	}
}
