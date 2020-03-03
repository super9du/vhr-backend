package xyz.gotop.vhr.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import xyz.gotop.vhr.model.Menu;
import xyz.gotop.vhr.model.Role;
import xyz.gotop.vhr.service.MenuService;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * Demo CustomerFilter
 *
 * @author Wolf-Liu
 * @date 2020/1/12 22:29
 */
@Component
public class CustomerFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MenuService menuService;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        final String requestUrl = ((FilterInvocation) o).getRequestUrl();
        logger.debug("requestUrl: {}", requestUrl);
        final List<Menu> menus = menuService.getAllMenusWithRoles();
        for (Menu menu : menus) {
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                List<Role> roles = menu.getRoles();
                String[] rolesNames = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    rolesNames[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(rolesNames);
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
