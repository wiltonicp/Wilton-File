package cn.wilton.framework.file.config;

import cn.wilton.framework.file.modules.service.impl.MyUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/2
 */
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
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
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() //开启登录配置
        .antMatchers("/","/s/**","/resources/**","/down","/toRegister","/user/register","/verify/**",
                "/api/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/toLogin")
        .loginProcessingUrl("/login")
        .defaultSuccessUrl("/")
        .failureUrl("/toLogin?error=0").permitAll()
        .and()
        .logout().logoutSuccessUrl("/toLogin").invalidateHttpSession(true).permitAll()
        .and()
        .rememberMe().tokenRepository(persistentTokenRepository())
        .tokenValiditySeconds(60*6).rememberMeParameter("rememberMe")
        .and()
        .csrf().disable();
        http.headers().frameOptions().disable();
    }

    /**
     * 用于设置rememberMe往数据库存储token
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    /**
     * 注入自定义PermissionEvaluator
     */
//    @Bean
//    public DefaultWebSecurityExpressionHandler userSecurityExpressionHandler(){
//        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
//        handler.setPermissionEvaluator(new UserPermissionEvaluator());
//        return handler;
//    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
