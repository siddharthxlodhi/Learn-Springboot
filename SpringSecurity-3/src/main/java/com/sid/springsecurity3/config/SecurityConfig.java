package com.sid.springsecurity3.config;

import com.sid.springsecurity3.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static com.sid.springsecurity3.Permission.*;
import static com.sid.springsecurity3.Roles.ADMIN;
import static com.sid.springsecurity3.Roles.USER;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    DataSource dataSource;
    @Autowired
    MyUserRepository myUserRepository;

    //Our custom security filter chain
    //once a request matches a specific authority (like ADMIN_CREATE), it doesn’t proceed to check the role (ADMIN). This happens because Spring Security stops processing further matchers once it finds a match.
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).sessionManagement(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) ->
//                        requests.anyRequest().authenticated().
                                requests
                                        .requestMatchers("/login", "/css/**", "/js/**").permitAll()
                                        .requestMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority(ADMIN_CREATE.name())
                                        .requestMatchers(HttpMethod.DELETE, "/admin/**").hasAuthority(ADMIN_DELETE.getPermission())
                                        .requestMatchers(HttpMethod.PUT, "/admin/**").hasAuthority(ADMIN_UPDATE.getPermission())
                                        .requestMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority(ADMIN_READ.getPermission(), USER_READ.getPermission())
                                        .requestMatchers("/admin/**").hasRole(ADMIN.name())
                                        .requestMatchers(HttpMethod.POST, "/user/**").hasAuthority(USER_CREATE.getPermission())
                                        .requestMatchers(HttpMethod.DELETE, "/user/**").hasAuthority(USER_DELETE.getPermission())
                                        .requestMatchers(HttpMethod.PUT, "/user/**").hasAuthority(USER_UPDATE.getPermission())
                                        .requestMatchers(HttpMethod.GET, "/user/**").hasAuthority(USER_READ.getPermission())
                                        .requestMatchers("/user/**").hasRole(USER.name())
                                        .anyRequest().authenticated()
                ).httpBasic(withDefaults());   //Basic Authentication
//      http.formLogin(withDefaults());      Form Based Authentication
        return http.build();
    }


//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails admin = User.withUsername("Siddharth").password("{noop}Sid@12").roles("Admin").build();
//        UserDetails user = User.withUsername("Sachin").password("{noop}Sachin@12").roles("User").build();
//        //In memory Authentication
//       return new InMemoryUserDetailsManager(admin, user);
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.createUser(admin);
//        jdbcUserDetailsManager.createUser(user);
//        return jdbcUserDetailsManager;
//    }


    //This changes the default implementation loadByUsername of UserDetailsService and loads user from database
    @Bean
    UserDetailsService userDetailsService() {
        return username -> myUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username:" + username));
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
