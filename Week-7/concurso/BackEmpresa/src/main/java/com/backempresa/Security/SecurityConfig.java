package com.backempresa.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@EnableWebSecurity @Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //Ignoramos seguridad para la BD de H2
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .addFilterAfter(new JWTAuthentication(), UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers(GET, "/v0/token/**").permitAll();
        http.authorizeRequests().antMatchers(POST, "/v0/token/**").permitAll();

        http.authorizeRequests().antMatchers(GET, "/v0/trip/details").permitAll();
        http.authorizeRequests().antMatchers(GET, "/v0/trip/detailsLocalDate").permitAll();
        http.authorizeRequests().antMatchers(GET, "/v0/trip/{\\d}").permitAll();
        http.authorizeRequests().antMatchers(GET, "/v0/trip/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(POST, "/v0/trip/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(PUT, "/v0/trip/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(DELETE, "/v0/trip/**").hasAnyAuthority("ADMIN");

        http.authorizeRequests().antMatchers(GET, "/v0/mail/**").hasAnyAuthority("ADMIN");

        http.authorizeRequests().antMatchers(GET, "/v0/client/{\\d}").hasAnyAuthority("USER");
        http.authorizeRequests().antMatchers(GET, "/v0/client/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(POST, "/v0/client/**").permitAll();
        http.authorizeRequests().antMatchers(PUT, "/v0/client/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(DELETE, "/v0/client/**").hasAnyAuthority("ADMIN");

        http.authorizeRequests().antMatchers(GET, "/v0/ticket/{\\d}").hasAnyAuthority("USER");
        http.authorizeRequests().antMatchers(GET, "/v0/ticket/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(POST, "/v0/ticket/**").hasAnyAuthority("USER", "ADMIN");
        http.authorizeRequests().antMatchers(DELETE, "/v0/ticket/**").hasAnyAuthority("USER", "ADMIN");

        http.authorizeRequests().anyRequest().authenticated();
    }
}
