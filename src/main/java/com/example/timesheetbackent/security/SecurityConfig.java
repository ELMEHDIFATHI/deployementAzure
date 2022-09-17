package com.example.timesheetbackent.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).
                passwordEncoder(bCryptPasswordEncoder);

    }


    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/login").permitAll().and().csrf().disable();
        http.authorizeRequests().antMatchers("/project/**").permitAll().and().csrf().disable();
        http.authorizeRequests().antMatchers("/employee/**").permitAll().and().csrf().disable();
        http.authorizeRequests().antMatchers("/task/**").permitAll().and().csrf().disable();
        http.authorizeRequests().antMatchers("/event/**").permitAll().and().csrf().disable();
        http.authorizeRequests().antMatchers("/find/**").permitAll().and().csrf().disable();
        http.authorizeRequests().antMatchers("/dashboard/**").permitAll().and().csrf().disable();
        http.authorizeRequests().antMatchers("/time/**").permitAll().and().csrf().disable();
        http.authorizeRequests().antMatchers("/all").hasAuthority("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new JWTAuthenticationFilter (authenticationManager())) ;
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);


    }


}
