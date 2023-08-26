package org.xcale.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.xcale.interceptor.CartInterceptor;

@Configuration
@EnableWebMvc
public class XCaleConfig implements WebMvcConfigurer {
    private final CartInterceptor cartInterceptor;

    public XCaleConfig(CartInterceptor cartInterceptor) {
        this.cartInterceptor = cartInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cartInterceptor).addPathPatterns("/cart/**");
    }

}
