package creative.fire.jsfcc.cdk;

import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.richfaces.cdk.annotations.Attribute;
import org.richfaces.cdk.annotations.Event;
import org.richfaces.cdk.annotations.JsfComponent;
import org.richfaces.cdk.annotations.JsfRenderer;
import org.richfaces.cdk.annotations.Tag;

@JsfComponent(type = "creative.fire.jsfcc.cdk.avatar", family = "creative.fire.avatar", generate = "creative.fire.jsfcc.cdk.component.UIBasicAvatar", renderer = @JsfRenderer(type = "creative.fire.avatar"), tag = @Tag(name = "avatar"), 
fires = @Event(value = ActionEvent.class, listener = ActionListener.class, 
listenerMethod = "switching",tag=@Tag(name="switching")))
public abstract class AbstractAvatar extends javax.faces.component.UIOutput {
	@Attribute(defaultValue = "0.png")
	public abstract String getImage();

	public void fillFacesListener(ActionListener listener) {
		addFacesListener(listener);
	}

	public void dropFacesListener(ActionListener listener) {
		removeFacesListener(listener);
	}
}