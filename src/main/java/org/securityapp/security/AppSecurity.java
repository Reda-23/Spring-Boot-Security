package org.securityapp.security;



import org.securityapp.entities.AppUser;
import org.securityapp.services.AccountService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

//Authentification Stateful
@Configuration
@EnableWebSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter {
    private final AccountService accountService;

    public AppSecurity(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.headers().frameOptions().disable();
    //http.formLogin();
    http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authorizeRequests().anyRequest().authenticated();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(new UserDetailsService() {
                @Override
                public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                  AppUser appUser =  accountService.loadUserByUserName(username);
                    Collection<GrantedAuthority> authorities = new ArrayList<>();
                    appUser.getAppRoles().forEach(au -> {
                        authorities.add(new SimpleGrantedAuthority(au.getRolename()));
                    });
                    return new User(appUser.getUsername(),appUser.getPassword(),authorities);

                }
            });
    }


}
