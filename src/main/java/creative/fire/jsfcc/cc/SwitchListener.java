package creative.fire.jsfcc.cc;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.apache.log4j.Logger;

import creative.fire.jsfcc.bean.AvatarBean;

/**
 * @author feuyeux@gmail.com
 * @version 1.0
 */
public class SwitchListener implements ActionListener {
	static Logger log = Logger.getLogger(SwitchListener.class);

	public void processAction(ActionEvent event) throws AbortProcessingException {
		final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		AvatarBean test = (AvatarBean) externalContext.getSessionMap().get("avatarBean");
		if (test != null) {
			log.info(test.getImage());
		}
	}
}