package creative.fire.jsfcc.cdk;

import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.richfaces.cdk.annotations.Attribute;
import org.richfaces.cdk.annotations.Event;
import org.richfaces.cdk.annotations.JsfComponent;
import org.richfaces.cdk.annotations.JsfRenderer;
import org.richfaces.cdk.annotations.Tag;

@JsfComponent(type = "creative.fire.jsfcc.cdk.picta", family = "creative.fire.picta", generate = "creative.fire.jsfcc.cdk.component.UIBasicpicta", 
renderer = @JsfRenderer(type = "creative.fire.picta"), 
tag = @Tag(name = "picta"), 
fires = @Event(value = ActionEvent.class, listener = ActionListener.class, 
listenerMethod = "switching",
tag=@Tag(name="switching",generate=true)))
public abstract class AbstractPicta extends javax.faces.component.UIOutput {
	@Attribute(defaultValue = "0.png")
	public abstract String getImage();

	public void fillFacesListener(ActionListener listener) {
		addFacesListener(listener);
	}

	public void dropFacesListener(ActionListener listener) {
		removeFacesListener(listener);
	}
}