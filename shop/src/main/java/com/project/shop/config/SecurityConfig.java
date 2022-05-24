package com.project.shop.config;

import com.project.shop.service.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserService customUserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)

                .and()
                .authorizeHttpRequests()
                .antMatchers("/", "/member/**").permitAll()
                .antMatchers("/api/member/**").permitAll()
                .antMatchers("/item/**").permitAll()
                .antMatchers( "/api/item/total", "/api/item/list").permitAll()
                .antMatchers("/api/item/category/**").permitAll()
                .antMatchers("/member/cart").hasAuthority("USER")
                .antMatchers("/sell/**").hasAuthority("ADMIN")
                .antMatchers( "/api/item/**").authenticated()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/member/login")
                .loginProcessingUrl("/api/member/login")
                .usernameParameter("memberId")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)

                .and()
                .logout()
                .logoutUrl("/api/member/logout")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/");


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        web.ignoring().antMatchers("/./templates/**");
        web.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService)
                .passwordEncoder(passwordEncoder());
    }


}
