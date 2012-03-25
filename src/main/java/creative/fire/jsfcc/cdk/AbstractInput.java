package creative.fire.jsfcc.cdk;

import org.richfaces.cdk.annotations.*;

@JsfComponent(
        type = "creative.fire.jsfcc.cdk.Input",
        family = "creative.fire.input",
        renderer = @JsfRenderer(type = "creative.fire.input"),
        tag = @Tag(name="input"))
abstract public class AbstractInput extends javax.faces.component.UIInput {

}