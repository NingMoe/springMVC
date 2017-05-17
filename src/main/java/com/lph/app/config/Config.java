package com.lph.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
/**
 * 使用Spring MVC基于注解配置
 * 
 * @author Administrator
 *
 */
@Configuration
//@Configuration指明该类包含注解为@Bean 生产 bean管理是由Spring容器的一个或多个bean方法。
@ComponentScan("com")
//等同于 context:component-scan base-package="..." 提供 spring 在哪里寻找 管理 beans/classes.
@EnableWebMvc
//等同于 mvc:annotation-driven 在XML中. 它能够为使用@RequestMapping向特定的方法传入的请求映射
public class Config extends WebMvcConfigurerAdapter {

    @Autowired
    RoleToUserProfileConverter roleToUserProfileConverter;
    /*
     * Configure ContentNegotiationManager 内容协商多视图
     * 第一步是建立它用于通过委托给ContentNegotiationManager， 以确定所请求的媒体类型的请求是
     * ContentNegotiationStrategy
     * 列表的一个实例。默认情况下PathExtensionContentNegotiationStrategy被查询 (使用URL扩展名，例如.
     * .xls, .pdf,.json.)， 接着ParameterContentNegotiationStrategy (使用请求参数
     * ‘format=xls’，例如)， 其次是HeaderContentNegotiationStrategy(使用HTTP接受头)。
     */
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//	configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.TEXT_HTML);
//    }

    /*
     * Configure ContentNegotiatingViewResolver 配置 ContentNegotaionViewResolver
     * 本身， 我们需要设置 ContentNegotiationManager由Spring
     * 注入，和为每一个应用程序可能会产生输出格式设置不同的解析器，。 最后，我们已经创建了不同的视图解析器以对 XML，JSON，PDF，XLS 和
     * HTML 输出
     */
//    @Bean
//    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
//	ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
//	resolver.setContentNegotiationManager(manager);
//
//	// Define all possible view resolvers
//	List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
//
//	resolvers.add(jaxb2MarshallingXmlViewResolver());
//	resolvers.add(jsonViewResolver());
//	resolvers.add(jspViewResolver());
//	resolvers.add(pdfViewResolver());
//	resolvers.add(excelViewResolver());
//
//	resolver.setViewResolvers(resolvers);
//	return resolver;
//    }

    /*
     * Configure View resolver to provide XML output Uses JAXB2 marshaller to
     * marshall/unmarshall POJO's (with JAXB annotations) to XML
     */
//    @Bean
//    public ViewResolver jaxb2MarshallingXmlViewResolver() {
//	Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//	marshaller.setClassesToBeBound(Pizza.class);
//	return new Jaxb2MarshallingXmlViewResolver(marshaller);
//    }

    /*
     * Configure View resolver to provide JSON output using JACKSON library to
     * convert object in JSON format.
     */
//    @Bean
//    public ViewResolver jsonViewResolver() {
//	return new JsonViewResolver();
//    }

    /*
     * Configure View resolver to provide PDF output using lowagie pdf library
     * to generate PDF output for an object content
     */
//    @Bean
//    public ViewResolver pdfViewResolver() {
//	return new PdfViewResolver();
//    }

    /*
     * Configure View resolver to provide XLS output using Apache POI library to
     * generate XLS output for an object content
     */
//    @Bean
//    public ViewResolver excelViewResolver() {
//	return new ExcelViewResolver();
//    }

    /*
     * jsp视图解析器的bean
     * 
     * @return
     */
//    @Bean
//    public ViewResolver jspViewResolver() {
//	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//	viewResolver.setViewClass(JstlView.class);
//	viewResolver.setPrefix("/WEB-INF/pages/");
//	viewResolver.setSuffix(".jsp");
//	return viewResolver;
//    }

    /*
     * 过滤静态资源 注意，此方法在 WebMvcConfigurerAdapter 中定义， 因此我们需要扩展这个类来注册我们的静态资源覆盖此方法。
     * 
     * @see
     * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
     * #addResourceHandlers(org.springframework.web.servlet.config.annotation.
     * ResourceHandlerRegistry)
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//    }

    /*
     * 国际化消息 方法为 messageSource 配置消息包，以支持[国际化]消息属性文件。 请注意方法 baseName 提供的参数(消息)。
     * Spring 将搜索应用程序类路径中一个名为messages.properties文件 请注意，上述消息按照特定的模式
     * {ValidationAnnotationClass}.{modelObject}.{fieldName}
     */
    @Bean
    public MessageSource messageSource() {
	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	messageSource.setBasename("message");
	return messageSource;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	viewResolver.setViewClass(JstlView.class);
	viewResolver.setPrefix("/WEB-INF/pages/");
	viewResolver.setSuffix(".jsp");
	registry.viewResolver(viewResolver);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    /**
     * Configure Converter to be used. In our example, we need a converter to
     * convert string values[Roles] to UserProfiles in newUser.jsp
     */
    /*
     * 使用addFormatters创建了Spring配置。 其次是方法configurePathMatch它提供了一个解决方法(虽然其他解决方法存在)
     * 在Spring中是一个已知的错误， 这仍然在Spring4.1.7.RELEASE中有发现。(non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
     * #addFormatters(org.springframework.format.FormatterRegistry)
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
	registry.addConverter(roleToUserProfileConverter);
    }

    /**
     * Optional. It's only required when handling '.' in @PathVariables which
     * otherwise ignore everything after last '.' in
     * 
     * @PathVaidables argument. It's a known bug in Spring
     *                [https://jira.spring.io/browse/SPR-6164], still present in
     *                Spring 4.1.7. This is a workaround for this issue.
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
	matcher.setUseRegisteredSuffixPatternMatch(true);
    }

    @Bean(name = "multipartResolver")
    public StandardServletMultipartResolver resolver() {
	return new StandardServletMultipartResolver();
    }

}