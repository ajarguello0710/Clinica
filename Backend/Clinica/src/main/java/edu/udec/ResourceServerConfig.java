package edu.udec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import edu.udec.exception.AuthExceptionWrapper;

//Indica los recursos autenticados y liberados del aplicatico
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	//Trae todo lo que configuramos en el SecurityConfig
	@Autowired
    private ResourceServerTokenServices tokenServices;
	
	
    @Value("${security.jwt.resource-ids}")
    private String resourceIds;
    
    //De donde se van a crear los tokens y la configuración del resourcesIds
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceIds).tokenServices(tokenServices);
    }
    
    //Url que vamos a proteger y como
    @Override
    public void configure(HttpSecurity http) throws Exception {
                http
                .exceptionHandling().authenticationEntryPoint(new AuthExceptionWrapper())
                .and()
                .requestMatchers()
                .and()
                .authorizeRequests()                  
                .antMatchers("/address/**").authenticated()
        		.antMatchers("/consult/**").authenticated()
        		.antMatchers("/consultDetail/**").authenticated()
        		.antMatchers("/consultExam/**").authenticated()
        		.antMatchers("/doctor/**").authenticated()
        		.antMatchers("/exam/**").authenticated()
        		.antMatchers("/patient/**").authenticated()
        		.antMatchers("/specialty/**").authenticated()
        		.antMatchers("/tokens/**").permitAll();                
    }    

}
