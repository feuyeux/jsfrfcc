package creative.fire.jsfcc.cdk;

import org.richfaces.cdk.annotations.Attribute;
import org.richfaces.cdk.annotations.JsfComponent;
import org.richfaces.cdk.annotations.JsfRenderer;
import org.richfaces.cdk.annotations.Tag;
/**
 * @author feuyeux@gmail.com
 * @version 1.0
 */

@JsfComponent(
		type = "creative.fire.jsfcc.cdk.picta", 
		family = "creative.fire.picta", 
		generate = "creative.fire.jsfcc.cdk.component.UIBasicpicta", 
		renderer = @JsfRenderer(type = "creative.fire.picta"), 
		tag = @Tag(name = "picta")
)
public abstract class AbstractPicta extends javax.faces.component.UIOutput {
	@Attribute(defaultValue = "0.png")
	public abstract String getImage();
}