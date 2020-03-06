package xyz.gotop.vhr.config;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import xyz.gotop.vhr.mapper.MenuMapper;
import xyz.gotop.vhr.model.Menu;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * Demo CustomFilterInvocationMatedataSource
 *
 * @author Wolf-Liu
 * @date 2020/3/6 23:40
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Resource
    private MenuMapper menuMapper;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object resource) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) resource).getRequestUrl();
        List<Menu> menus = menuMapper.getAllMenusWithRoles();
        String[] roles = menus.stream()
                .filter(menu -> antPathMatcher.match(menu.getUrl(), requestUrl))
                .flatMap(menu -> menu.getRoles().stream())
                .map(role -> "ROLE_" + role.getName())
                .toArray(String[]::new);
        return SecurityConfig.createList(roles.length > 0 ? roles : new String[]{"ROLE_login"});
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
