package com.sh.config.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ExternalFiles {

	// 외부 css 파일 경로
	@Value("${files.external.css}")
	private String externalCssPath;
	@Value("${files.internal.css}")
	private String internalCssPath;

	@Value("${files.external.html}")
	private String externalHtmlPath;
	@Value("${files.internal.html}")
	private String internalHtmlPath;

	@Value("${files.external.fonts}")
	private String externalFontsPath;
	@Value("${files.internal.fonts}")
	private String internalFontsPath;

	@Value("${files.external.js}")
	private String externalJsPath;
	@Value("${files.internal.js}")
	private String internalJsPath;

	@Value("${files.external.module}")
	private String externalModulePath;
	@Value("${files.internal.module}")
	private String internalModulePath;

	@Value("${files.external.images}")
	private String externalImagesPath;
	@Value("${files.internal.images}")
	private String internalImagesPath;

	@Value("${files.external.templates}")
	private String externalTemplatesPath;
	@Value("${files.internal.templates}")
	private String internalTemplatesPath;
	
	@Value("${files.external.articleImage}")
	private String externalArticleImagePath;
	@Value("${files.internal.articleImage}")
	private String internalArticleImagePath;

}
