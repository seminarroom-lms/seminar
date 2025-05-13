// package com.seminarroom.edu.config;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// // @Configuration
// // public class WebConfig implements WebMvcConfigurer {
// //     @Override
// //     public void addCorsMappings(CorsRegistry registry) {
// //         registry.addMapping("/**")
// //                 .allowedOrigins("http://localhost:8081") // Allow requests from frontend at port 8081
// //                 .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specific methods
// //                 .allowedHeaders("*") // Allow all headers
// //                 .allowCredentials(true); // Allow credentials if needed
// //     }
// // }

// @Configuration
// public class WebConfig implements WebMvcConfigurer {
//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         registry.addMapping("/**")
//                 .allowedOrigins("http://localhost:8080") // ✅ your frontend origin
//                 .allowedMethods("*") // GET, POST, PUT, DELETE, etc.
//                 .allowedHeaders("*")
//                 .allowCredentials(true);
//     }
// }
// package com.seminarroom.edu.config;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class WebConfig implements WebMvcConfigurer {
//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         registry.addMapping("/**")
//                 .allowedOrigins("http://localhost:8081")  // Correct frontend port (8081)
//                 .allowedMethods("*")  // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
//                 .allowedHeaders("*")  // Allow all headers
//                 .allowCredentials(true);  // Allow credentials if needed
//     }
// }



package com.seminarroom.edu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.Arrays;

@Configuration
public class WebConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:8081")); // ✅ React frontend
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
