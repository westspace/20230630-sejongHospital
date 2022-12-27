package com.sh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.sh.config.vo.ExternalFiles;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class ExternalFilesConfiguration implements WebMvcConfigurer {

	@Autowired
	@Qualifier("beforeActionInterceptor")
	HandlerInterceptor beforeActionInterceptor;

	private final String PATH = "file://";

	@Autowired
	private ExternalFiles externalFiles;

	@Bean
	public ClassLoaderTemplateResolver templateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

		templateResolver.setPrefix("/templates/"); // 모든 뷰 페이지는 /templates/ 내부에서 검색한다.
		templateResolver.setCacheable(false); // 캐싱하지 않는다.
		templateResolver.setSuffix(".html"); // 모든 뷰 페이지는 .html 이다.
		templateResolver.setTemplateMode("HTML"); // HTML 형식으로 읽는다.
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setOrder(0);

		return templateResolver;
	}

	/**
	 * 외부파일 경로 templetes
	 */
	@Bean
	public ITemplateResolver externalResolver() {
		FileTemplateResolver templateResolver = new FileTemplateResolver();
		templateResolver.setPrefix(externalFiles.getExternalTemplatesPath());
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML");
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setOrder(0);
		templateResolver.setCacheable(false);

		return templateResolver;
	}

	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(externalResolver());
		templateEngine.addDialect(layoutDialect());

		return templateEngine;
	}

	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());

		viewResolver.setCharacterEncoding("UTF-8");
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println(externalFiles.toString());

		/**
		 * internalCssPath 패턴으로 요청시 => file://uploadPath 경로로 location
		 */

		registry.addResourceHandler(externalFiles.getInternalCssPath() + "**")
				.addResourceLocations(PATH + externalFiles.getExternalCssPath());
		registry.addResourceHandler(externalFiles.getInternalHtmlPath() + "**")
				.addResourceLocations(PATH + externalFiles.getExternalHtmlPath());
		registry.addResourceHandler(externalFiles.getInternalFontsPath() + "**")
				.addResourceLocations(PATH + externalFiles.getExternalFontsPath());
		registry.addResourceHandler(externalFiles.getInternalJsPath() + "**")
				.addResourceLocations(PATH + externalFiles.getExternalJsPath());
		registry.addResourceHandler(externalFiles.getInternalModulePath() + "**")
				.addResourceLocations(PATH + externalFiles.getExternalModulePath());
		
		registry.addResourceHandler(externalFiles.getInternalImagesPath() + "**")
				.addResourceLocations(PATH + externalFiles.getExternalImagesPath());

		registry.addResourceHandler(externalFiles.getInternalArticleImagePath() + "**")
				.addResourceLocations(PATH + externalFiles.getExternalArticleImagePath());

	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 단 resource 로 시작하는 액션은 제외
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**")
				.excludePathPatterns("/module/**").excludePathPatterns("/css/**").excludePathPatterns("/fonts/**")
				.excludePathPatterns("/js/**").excludePathPatterns("/static/**").excludePathPatterns("/images/**")
				.excludePathPatterns("/articmeImage/**");
	}

}
