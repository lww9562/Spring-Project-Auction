package org.koreait.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaAuditing
public class MvcConfig implements WebMvcConfigurer {
	@Value("$(fileupload.path)")
	private String fileUploadPath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploads/**")
				.addResourceLocations("file:///" + fileUploadPath);
		//추후에 저장될 때는 회원 아이디명 폴더로 생성, 판매물품 no + n번째 사진 이런식으로 하면 좋을듯
	}

	//MessageSource - commons : 공통 / errors : 에러 / validations : 검증자
	@Bean
	public MessageSource messageSource(){
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setDefaultEncoding("UTF-8");
		ms.addBasenames("messages.commons", "messages.errors", "messages.validations");
		return ms;
	}

	@Bean	//수정자 - 추후 활용 예정으로 넣어둠
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}
}
