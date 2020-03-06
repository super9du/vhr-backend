package xyz.gotop.vhr.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Collection;

/**
 * Demo CustomAccessDecisionManager
 *
 * @author Wolf-Liu
 * @date 2020/3/7 0:16
 */
public class CustomAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication auth, Object resource, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        boolean flag = configAttributes.stream().anyMatch(configAttribute -> {
            if ("ROLE_login".equals(configAttribute.getAttribute())
                    && auth instanceof UsernamePasswordAuthenticationToken) {
                return true;
            } else {
                return auth.getAuthorities().stream()
                        .anyMatch(authority -> configAttribute.getAttribute().equals(authority.getAuthority()));
            }
        });
        if (flag) {
            throw new AccessDeniedException("权限不足");
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
