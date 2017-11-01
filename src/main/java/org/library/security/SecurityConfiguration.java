package org.library.security;

import org.library.db.repo.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.library.services.LibraryUserDetailsService;
import org.springframework.web.servlet.ModelAndView;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = ReaderRepository.class)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private LibraryUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new Md5PasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                    .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                    .antMatchers("/reader/**")
                    .authenticated().anyRequest().permitAll()
                    .and()
                .formLogin()
                    //.loginPage("/login")
                    //.successForwardUrl("/reader")
                    //.failureForwardUrl("/reader")
                    //.loginProcessingUrl("/lib-login")
                    .permitAll()
                    .and()
                .logout()
                    .logoutSuccessUrl("/")
                    .permitAll();
    }
}
