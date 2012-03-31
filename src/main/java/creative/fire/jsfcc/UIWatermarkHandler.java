package creative.fire.jsfcc;

import javax.faces.event.ActionEvent;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.MetaRuleset;

import creative.fire.jsfcc.tag.UIWatermarkMethodRule;
/**
 * @author feuyeux@gmail.com
 * @version 1.0
 */
public class UIWatermarkHandler extends ComponentHandler {

	public UIWatermarkHandler(ComponentConfig config) {
		super(config);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected MetaRuleset createMetaRuleset(Class type) {
		return super.createMetaRuleset(type).addRule(
			new UIWatermarkMethodRule(
					"switching", 
					Void.class, 
					new Class<?>[] { 
						ActionEvent.class 
					}
			)
		);
	}
}