package creative.fire.jsfcc.cc;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.apache.log4j.Logger;

public class SwitchListener implements ActionListener {
	static Logger log = Logger.getLogger(SwitchListener.class);

	public void processAction(ActionEvent event) throws AbortProcessingException {
		final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Test test = (Test) externalContext.getSessionMap().get("test1");
		if (test != null) {
			log.info(test.getImage());
		}
	}
}