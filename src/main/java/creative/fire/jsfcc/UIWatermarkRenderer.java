package creative.fire.jsfcc;

import java.awt.Color;
import java.io.IOException;

import javax.el.MethodExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.FacesEvent;
import javax.faces.event.MethodExpressionActionListener;
import javax.faces.event.PhaseId;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

import creative.fire.jsfcc.tool.WaterMark;

@FacesRenderer(componentFamily = "javax.faces.Output", rendererType = "creative.fire.jsfcc.Watermark")
public class UIWatermarkRenderer extends Renderer {

	@Override
	public void encodeBegin(FacesContext context, UIComponent watermark) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String clientId = watermark.getClientId(context);
		encode(watermark, writer, clientId);
	}

	private void encode(UIComponent component, ResponseWriter writer, String clientId) throws IOException {
		String image = (String) component.getAttributes().get("image");
		String text = (String) component.getAttributes().get("text");
		Integer temp = (Integer) component.getAttributes().get("rotate");
		int rotate = temp;
		String position = (String) component.getAttributes().get("position");
		String target = generateWatermark(image, text, rotate, position);

		writer.startElement("img", component);
		writer.writeAttribute("id", clientId, null);
		writer.writeAttribute("name", clientId, null);
		writer.writeAttribute("src", target, "src");
		writer.writeAttribute("width", "100%", null);
		writer.writeAttribute("height", "100%", null);
		writer.writeAttribute("onclick", "alert('123')", null);
		writer.endElement("img");
	}

	private String generateWatermark(String image, String text, int rotate, String position) {
		final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		String filePath = externalContext.getRealPath("/") + "resources/img/";
		String contextPath = externalContext.getRequestContextPath() + "/resources/img/";

		String file = image.substring(contextPath.length(), image.length());

		String source = filePath + file;
		String target = image.substring(0, image.lastIndexOf(".")) + "_watermark.png";
		WaterMark wm = new WaterMark();
		wm.generate(source, text, Color.BLUE, rotate, position);
		return target;
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