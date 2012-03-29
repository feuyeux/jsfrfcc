package creative.fire.jsfcc.cdk;

import javax.el.MethodExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.FacesEvent;
import javax.faces.event.MethodExpressionActionListener;
import javax.faces.event.PhaseId;
import javax.faces.render.Renderer;

public class BaseAvartarRenderer extends Renderer {
	@Override
	public void decode(FacesContext context, UIComponent component) {
		try {
			MethodExpression switchMethod = (MethodExpression) component.getAttributes().get("switching");
			if (switchMethod != null) {
				ActionListener listener = new MethodExpressionActionListener(switchMethod);
				FacesEvent event = new ActionEvent(component);
				event.setPhaseId(PhaseId.INVOKE_APPLICATION);
				component.queueEvent(event);
				((AbstractAvatar) component).fillFacesListener(listener);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}