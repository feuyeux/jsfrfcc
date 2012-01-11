package creative.fire.jsfcc.tag;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.el.MethodExpression;
import javax.faces.component.UIComponent;
import javax.faces.el.MethodBinding;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.MetaRule;
import javax.faces.view.facelets.Metadata;
import javax.faces.view.facelets.MetadataTarget;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagAttributeException;

import com.sun.faces.facelets.el.LegacyMethodBinding;

/**
 * Optional Rule for binding Method[Binding|Expression] properties
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings({ "rawtypes", "deprecation" })
public class UIWatermarkMethodRule extends MetaRule {
	private final String methodName;

	private final Class returnTypeClass;
	private final Class[] params;

	public UIWatermarkMethodRule(String methodName, Class returnTypeClass, Class[] params) {
		this.methodName = methodName;
		this.returnTypeClass = returnTypeClass;
		this.params = params;
	}

	public Metadata applyRule(final String name, final TagAttribute attribute, MetadataTarget meta) {
		if (!name.equals(this.methodName))
			return null;
		final Method method = meta.getWriteMethod(name);
		if (method != null) {
			if (MethodBinding.class.equals(meta.getPropertyType(name))) {
				return new Metadata() {
					@Override
					public void applyMetadata(FaceletContext ctx, Object instance) {
						MethodExpression expr = attribute.getMethodExpression(ctx, returnTypeClass, params);

						try {
							method.invoke(instance, new LegacyMethodBinding(expr));
						} catch (InvocationTargetException e) {
							throw new TagAttributeException(attribute, e.getCause());
						} catch (Exception e) {
							throw new TagAttributeException(attribute, e);
						}
					}
				};

			} else if (MethodExpression.class.equals(meta.getPropertyType(name))) {
				return new Metadata() {
					@Override
					public void applyMetadata(FaceletContext ctx, Object instance) {
						MethodExpression expr = attribute.getMethodExpression(ctx, returnTypeClass, params);

						try {
							method.invoke(instance, expr);
						} catch (InvocationTargetException e) {
							throw new TagAttributeException(attribute, e.getCause());
						} catch (Exception e) {
							throw new TagAttributeException(attribute, e);
						}
					}
				};

			}
		} else {
			return new Metadata() {
				@Override
				public void applyMetadata(FaceletContext ctx, Object instance) {
					MethodExpression expr = attribute.getMethodExpression(ctx, returnTypeClass, params);
					((UIComponent) instance).getAttributes().put(name, expr);
				}
			};
		}
		return null;
	}
}
