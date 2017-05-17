package com.lph.app.view;

import java.util.Locale;
import org.springframework.oxm.Marshaller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;
/**
 * 这个视图解析器依赖于JAXB2编组/解组产生XML输出。domain类需要和JAXB2注释进行注释。
 * @author Administrator
 *
 */
public class Jaxb2MarshallingXmlViewResolver implements ViewResolver {

	private Marshaller marshaller;

    
    public Jaxb2MarshallingXmlViewResolver(Marshaller marshaller) {
        this.marshaller = marshaller;
    }
    
    
    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        MarshallingView view = new MarshallingView();
        view.setMarshaller(marshaller);
        return view;
    }

}
