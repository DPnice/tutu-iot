package com.dpnice.iot.tutu.config;

import com.dpnice.iot.tutu.config.jwt.JWTConfigurer;
import com.dpnice.iot.tutu.config.jwt.JwtAccessDeniedHandler;
import com.dpnice.iot.tutu.config.jwt.JwtAuthenticationEntryPoint;
import com.dpnice.iot.tutu.config.jwt.TokenProvider;
import com.dpnice.iot.tutu.security.CustomUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author DPnice
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    public SecurityConfig(
            TokenProvider tokenProvider,
            JwtAuthenticationEntryPoint authenticationErrorHandler,
            JwtAccessDeniedHandler jwtAccessDeniedHandler,
            Environment env,
            CustomUserDetailsServiceImpl userDetailsService
    ) {
        this.tokenProvider = tokenProvider;
        this.authenticationErrorHandler = authenticationErrorHandler;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.userDetailsService = userDetailsService;
        this.env = env;
    }

    private Environment env;
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint authenticationErrorHandler;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private CustomUserDetailsServiceImpl userDetailsService;
    private static final String SECURITY_IGNORE_URLS_SPILT_CHAR = ",";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationErrorHandler)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                // create no session
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/feed").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(securityConfigurerAdapter());
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }


    /**
     * 取消 security 对接口的拦截
     */
    @Override
    public void configure(WebSecurity web) {
        String ignoreURLs = env.getProperty("iot.security.ignore.urls", "/**");
        for (String ignoreURL : ignoreURLs.trim().split(SECURITY_IGNORE_URLS_SPILT_CHAR)) {
            web.ignoring().antMatchers(ignoreURL.trim());
        }
        //这里填写需要过滤的路径
//        web.ignoring().antMatchers("/alarm/**");
//        web.ignoring().antMatchers("/humidity/**");
//        web.ignoring().antMatchers("/notice/**");
//        web.ignoring().antMatchers("/temperature/**");
//        web.ignoring().antMatchers("/water/**");
//        web.ignoring().antMatchers("/authenticate/**");
    }
}

