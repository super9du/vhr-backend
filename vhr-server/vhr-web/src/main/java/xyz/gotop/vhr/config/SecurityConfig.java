package xyz.gotop.vhr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import xyz.gotop.vhr.model.Hr;
import xyz.gotop.vhr.model.RespBean;
import xyz.gotop.vhr.service.HrService;

import javax.annotation.Resource;
import java.io.PrintWriter;

/**
 * Demo SecurityConfig
 *
 * @author Wolf-Liu
 * @date 2019/12/22 21:39
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private HrService hrService;
    @Autowired
    private CustomerAccessDecisionManager accessDecisionManager;
    @Autowired
    private CustomerFilterInvocationSecurityMetadataSource metadataSource;
    

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/admin/**").hasRole("admin")
//                .antMatchers("/user/**").hasRole("user")
//                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(metadataSource);
                        object.setAccessDecisionManager(accessDecisionManager);
                        return object;
                    }
                })
                .and()
                .formLogin()
                // 未授权时跳转此页面
                .loginPage("/login")
                // 认证用户名和密码使用此链接处理
                .loginProcessingUrl("/doLogin")
                .successForwardUrl("/hello")
                .successHandler((req, resp, auth) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    Hr hr = (Hr) auth.getPrincipal();
                    hr.setPassword(null);
                    RespBean ok = RespBean.ok("登录成功", hr);
                    PrintWriter writer = resp.getWriter();
                    writer.write(new ObjectMapper().writeValueAsString(ok));
                    writer.flush();
                    writer.close();
                })
                .failureHandler((req, resp, e) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    RespBean error = RespBean.error("登录失败");
                    PrintWriter writer = resp.getWriter();
                    if (e instanceof BadCredentialsException) {
                        error.setMsg("用户名或密码错误");
                    } else if (e instanceof UsernameNotFoundException) {
                        error.setMsg("用户名不存在");
                    }
                    writer.write(new ObjectMapper().writeValueAsString(error));
                    writer.flush();
                    writer.close();
                })
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler((req, resp, auth) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    RespBean ok = RespBean.ok("已退出登录");
                    PrintWriter writer = resp.getWriter();
                    writer.write(new ObjectMapper().writeValueAsString(ok));
                    writer.flush();
                    writer.close();
                })
                .permitAll()
                .and().csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((req, resp, e) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    resp.setStatus(401);
                    final RespBean bean = RespBean.error("未知异常");
                    if(e instanceof InsufficientAuthenticationException){
                        bean.setMsg("权限不足，请联系管理员");
                    }
                    final PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(bean));
                    out.flush();
                    out.close();
                });
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login");
    }
}
