package com.lph.app.view;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
/**
 * 这个视图解析器使用lowagie iText库实际生成PDF输出。还要注意的是实际的视图，从Spring AbstractPdfView 内部使用 lowagie iText 库扩展。
 * @author Administrator
 *
 */
public class PdfViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
	PdfView view = new PdfView();
	return view;
    }

}
