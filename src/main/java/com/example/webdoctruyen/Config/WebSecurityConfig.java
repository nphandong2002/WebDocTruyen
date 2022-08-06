package com.example.webdoctruyen.Config;

import com.example.webdoctruyen.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configurable
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {
    @Autowired
    UserService userSevice;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws  Exception{
        auth.userDetailsService(userSevice).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws  Exception{
        http.csrf().disable()
                .authorizeRequests()

            .antMatchers("/","/index","/img/**","/dist/**","/css/**","/js/**").permitAll()
            .antMatchers("/admin").hasAnyAuthority("admin")
            .antMatchers("/user").hasAnyAuthority("user","admin","qtv")

            .anyRequest().authenticated()
            .and()
            .formLogin()
                .usernameParameter("username") // default is username
                .passwordParameter("password") // default is password
                .loginPage("/index?login=true") // default is /login with an HTTP get
                .failureUrl("/index?login=true") // default is /login?error
            .defaultSuccessUrl("/index")
            .failureForwardUrl("/login/error")
            .permitAll()
            .and()
            .logout()
            .permitAll()
            .and()
            .exceptionHandling()
            .accessDeniedPage("/401");
    }

}
