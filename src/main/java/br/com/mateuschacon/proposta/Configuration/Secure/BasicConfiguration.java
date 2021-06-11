package br.com.mateuschacon.proposta.Configuration.Secure;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class BasicConfiguration extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
            .and()
                /**
                 *if you need to put the authorization by the hasAuthority notation 
                 */
                .authorizeRequests(authorizeRequests ->
                    authorizeRequests
                        .antMatchers(HttpMethod.GET, "/api/**").hasAuthority("SCOPE_profile")
                        .antMatchers(HttpMethod.POST, "/api/**").hasAuthority("SCOPE_profile")
                        .antMatchers(HttpMethod.GET, "/actuator/**").hasAuthority("SCOPE_profile")
                        .anyRequest().authenticated()
                )
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .oauth2ResourceServer()
                    .jwt();
    }
 
}
