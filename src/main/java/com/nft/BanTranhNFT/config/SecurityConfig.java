package com.nft.BanTranhNFT.config;

import com.nft.BanTranhNFT.model.Role;
import com.nft.BanTranhNFT.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers("/**")
                        .permitAll()
                        .requestMatchers("/api/v1/user/change-password").authenticated()
                        .requestMatchers("/api/v1/admin").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/api/v1/user").hasAnyAuthority(Role.ADMIN.name(),Role.CUSTOMER.name())
                        .requestMatchers("/api/v1/artist").hasAnyAuthority(Role.ADMIN.name(),Role.ARTIST.name())
                        .anyRequest().authenticated())

                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
                )
                .logout(logout -> logout
                        .logoutUrl("/api/v1/user/logout")
                        .logoutSuccessHandler(new LogoutSuccessHandler() {
                            @Override
                            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                response.setStatus(HttpStatus.OK.value());
                                response.setContentType("application/json");
                                String json = "{\"message\": \"Logout successfully\"}";
                                response.getWriter().write(json);

                                // Xóa token và thông tin xác thực của người dùng
                                SecurityContextHolder.getContext().setAuthentication(null);
                                request.getSession().invalidate();

                            }
                        }));
                return http.build();
    }
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //         .csrf(AbstractHttpConfigurer::disable)
    //         .authorizeHttpRequests(request -> request
    //             .requestMatchers("/api/v1/auth/**").permitAll() // Cho phép truy cập công khai đến các endpoint auth
    //             .anyRequest().authenticated())
    //         .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    //         .authenticationProvider(authenticationProvider())
    //         .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
    //         .logout(logout -> logout
    //             .logoutUrl("/api/v1/user/logout")
    //             .logoutSuccessHandler(new LogoutSuccessHandler() {
    //                 @Override
    //                 public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    //                     response.setStatus(HttpStatus.OK.value());
    //                     response.setContentType("application/json");
    //                     String json = "{\"message\": \"Logout successfully\"}";
    //                     response.getWriter().write(json);
    //                     SecurityContextHolder.getContext().setAuthentication(null);
    //                     request.getSession().invalidate();
    //                 }
    //             }));
    //     return http.build();
    // }

    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //         .csrf(AbstractHttpConfigurer::disable)
    //         .authorizeHttpRequests(request -> request
    //             .requestMatchers("/api/v1/auth/**").permitAll() // Cho phép truy cập công khai đến các endpoint auth
    //             .requestMatchers("/api/v1/admin").hasAuthority(Role.ADMIN.name()) // Chỉ cho phép ADMIN truy cập
    //             .requestMatchers("/api/v1/user").hasAnyAuthority(Role.ADMIN.name(), Role.CUSTOMER.name()) // ADMIN và CUSTOMER
    //             .requestMatchers("/api/v1/artist").hasAnyAuthority(Role.ADMIN.name(), Role.ARTIST.name()) // ADMIN và ARTIST
    //             .anyRequest().authenticated())
    //         .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    //         .authenticationProvider(authenticationProvider())
    //         .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
    //         .logout(logout -> logout
    //             .logoutUrl("/api/v1/user/logout")
    //             .logoutSuccessHandler((request, response, authentication) -> {
    //                 response.setStatus(HttpStatus.OK.value());
    //                 response.setContentType("application/json");
    //                 String json = "{\"message\": \"Logout successfully\"}";
    //                 response.getWriter().write(json);
    //                 SecurityContextHolder.getContext().setAuthentication(null);
    //                 request.getSession().invalidate();
    //             }));
    //     return http.build();
    // }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService.userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }



}
