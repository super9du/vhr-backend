package xyz.gotop.vhr.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import xyz.gotop.vhr.model.Hr;

/**
 * Demo HrUtils
 *
 * @author Wolf-Liu
 * @date 2020/1/30 12:00
 */
public class HrUtils {
    private HrUtils(){}

    public static Hr getHr() {
        return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
