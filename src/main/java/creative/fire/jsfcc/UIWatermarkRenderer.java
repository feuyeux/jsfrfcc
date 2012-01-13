package creative.fire.jsfcc;

import java.awt.Color;
import java.io.IOException;

import javax.el.MethodExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.FacesEvent;
import javax.faces.event.MethodExpressionActionListener;
import javax.faces.event.PhaseId;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

@FacesRenderer(componentFamily = "javax.faces.Output", rendererType = "creative.fire.jsfcc.Watermark")
public class UIWatermarkRenderer extends Renderer {

	@Override
	public void encodeBegin(FacesContext context, UIComponent watermark) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String clientId = watermark.getClientId(context);
		encode(watermark, writer, clientId);
	}

	private void encode(UIComponent component, ResponseWriter writer, String clientId) throws IOException {
		String width = (String) component.getAttributes().get("width");
		String height = (String) component.getAttributes().get("height");

		writer.startElement("img", component);
		writer.writeAttribute("id", clientId, null);
		writer.writeAttribute("name", clientId, null);

		UIWatermark watermark = ((UIWatermark) component);
		if (watermark.getTextImage() != null){
			writer.writeAttribute("src", watermark.getTextImage(), "src");
		}else{
			String image = (String) component.getAttributes().get("image");
			String text = (String) component.getAttributes().get("text");
			Integer temp = (Integer) component.getAttributes().get("rotate");
			int rotate = temp;
			String position = (String) component.getAttributes().get("position");
			watermark.generateWatermark(image, text, Color.black, rotate, position);
			writer.writeAttribute("src", watermark.getTextImage(), "src");
		}
			
		writer.writeAttribute("width", width, null);
		writer.writeAttribute("height", height, null);

		writer.writeAttribute("style", "cursor:'hand'", null);

		String formId = component.getParent().getClientId();
		writer.writeAttribute("onclick", "mojarra.jsfcljs(document.getElementById('" + formId + "'),{'" + clientId + "':'" + clientId + "'},'');return false",
				null);
		writer.endElement("img");

		watermark.removeMaxMinListener();
	}

	@Override
	public void decode(FacesContext context, UIComponent component) {
		MethodExpression switchMethod = (MethodExpression) component.getAttributes().get("switching");
		if (switchMethod != null) {
			ActionListener listener = new MethodExpressionActionListener(switchMethod);
			FacesEvent event = new ActionEvent(component);
			event.setPhaseId(PhaseId.INVOKE_APPLICATION);
			component.queueEvent(event);
			((UIWatermark) component).addSwitchListener(listener);
		}
	}
}