package uk.ac.bath.Web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.persistence.EntityManagerFactory;

/**
 * Created by Sam on 12/02/2016.
 */
@Configuration
@EnableWebMvc
//@EnableJpaRepositories
public class WebConfig extends WebMvcConfigurerAdapter {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/")
//                .allowedOrigins("http://people.bath.ac.uk")
//                .allowedMethods("PUT", "DELETE", "POST", "GET");
////                .allowedHeaders("header1", "header2", "header3")
////                .exposedHeaders("header1", "header2")
////                .allowCredentials(false).maxAge(3600);
//    }

}
