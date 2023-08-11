package com.learn.vs.securitydemo.configure;

import com.learn.vs.securitydemo.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
public class SecurityConfiguration {

    @Autowired
    public CustomUserDetailService customUserDetailService;

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(customUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return daoAuthenticationProvider;

    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests()
                .requestMatchers(toH2Console()).permitAll()
                .and()
                .csrf()
                .and().csrf().ignoringRequestMatchers(antMatcher("/h2-console/**"))
                .and().headers().frameOptions().sameOrigin();

        httpSecurity.authorizeHttpRequests().requestMatchers(antMatcher("/home/admin"))
                .hasRole("ADMIN")
                .requestMatchers(antMatcher("/home/normal"))
                .hasRole("USER")
                .requestMatchers(antMatcher("/home/public"))
                .permitAll()
                .anyRequest()// any other request need to authenticate
                .authenticated()
                .and()
                .formLogin();

       /* httpSecurity.authorizeHttpRequests()
                .requestMatchers(toH2Console()).permitAll()
                .and()
                .csrf()
                .and().csrf().ignoringRequestMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin();*/

        /*Provide authentication provider*/
        httpSecurity.authenticationProvider(authenticationProvider());
        return httpSecurity.build();
        /*try {
            httpSecurity.csrf().disable().authorizeHttpRequests((auth)->
                    *//*auth.anyRequest().authenticated())
                    .httpBasic(Customizer.withDefaults()*//*
                            auth.requestMatchers("/home")
                                    .permitAll()
                                    .and()
                                    .formLogin();

                    );
            return httpSecurity.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
    }
}
