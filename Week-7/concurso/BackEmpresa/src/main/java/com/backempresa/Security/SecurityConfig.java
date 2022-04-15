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

    String ADMIN = "ADMIN";
    String USER = "USER";

    String ALLTRIP = "/v0-empresa/trip/**";
    String ALLCLIENT = "/v0-empresa/client/**";
    String ALLTICKET = "/v0-empresa/ticket/**";

    String ALLTOKENS = "/v0-empresa/token/**";

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

        http.authorizeRequests().antMatchers(GET, ALLTOKENS).permitAll();
        http.authorizeRequests().antMatchers(POST, ALLTOKENS).permitAll();

        http.authorizeRequests().antMatchers(GET, "/v0-empresa/trip/details").permitAll();
        http.authorizeRequests().antMatchers(GET, "/v0-empresa/trip/detailsLocalDate").permitAll();
        http.authorizeRequests().antMatchers(GET, "/v0-empresa/trip/{\\d}").permitAll();
        http.authorizeRequests().antMatchers(GET, ALLTRIP).hasAnyAuthority(ADMIN);
        http.authorizeRequests().antMatchers(POST, ALLTRIP).hasAnyAuthority(ADMIN);
        http.authorizeRequests().antMatchers(PUT, ALLTRIP).hasAnyAuthority(ADMIN);
        http.authorizeRequests().antMatchers(DELETE, ALLTRIP).hasAnyAuthority(ADMIN);

        http.authorizeRequests().antMatchers(GET, "/v0-empresa/mail/**").hasAnyAuthority(ADMIN);

        http.authorizeRequests().antMatchers(GET, "/v0-empresa/client/{\\d}").hasAnyAuthority(USER);
        http.authorizeRequests().antMatchers(GET, ALLCLIENT).hasAnyAuthority(ADMIN);
        http.authorizeRequests().antMatchers(POST, ALLCLIENT).permitAll();
        http.authorizeRequests().antMatchers(PUT, ALLCLIENT).hasAnyAuthority(ADMIN, USER);
        http.authorizeRequests().antMatchers(DELETE, ALLCLIENT).hasAnyAuthority(ADMIN);

        http.authorizeRequests().antMatchers(GET, "/v0-empresa/ticket/{\\d}").hasAnyAuthority(USER);
        http.authorizeRequests().antMatchers(GET, ALLTICKET).hasAnyAuthority(ADMIN);
        http.authorizeRequests().antMatchers(POST, ALLTICKET).hasAnyAuthority(USER, ADMIN);
        http.authorizeRequests().antMatchers(DELETE, ALLTICKET).hasAnyAuthority(USER, ADMIN);

        http.authorizeRequests().anyRequest().authenticated();
    }
}
