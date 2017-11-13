package org.library.security;

import org.library.db.repo.ReaderRepository;
import org.library.services.LibraryUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = ReaderRepository.class)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final static Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Autowired
    private LibraryUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new Md5PasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable();

        http.authorizeRequests()
                .antMatchers("/account/reader/**").access("hasRole('ROLE_READER')")
                .antMatchers("/account/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/account/**")
                .authenticated().anyRequest().permitAll()
                .and();
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .failureUrl("/login?error")
                .usernameParameter("email_enter")
                .passwordParameter("passwd_enter")
                .defaultSuccessUrl("/account")
                .permitAll()
                .and();
        http.logout()
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }
}
