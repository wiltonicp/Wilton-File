package cn.wilton.framework.file.config;

import cn.wilton.framework.file.modules.service.impl.MyUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/2
 */
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final IgnoreUrlsConfig ignoreUrlsConfig;
    private final MyUserDetailsServiceImpl userDetailService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        for (String url : ignoreUrlsConfig.getUrls()) {
            web.ignoring().antMatchers(url);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() //开启登录配置
        .anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/toLogin")
        .loginProcessingUrl("/login").failureUrl("/toLogin?error=0").permitAll();
        http.logout().logoutSuccessUrl("/").invalidateHttpSession(true).deleteCookies();
        http.rememberMe().rememberMeParameter("rememberMe");
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
