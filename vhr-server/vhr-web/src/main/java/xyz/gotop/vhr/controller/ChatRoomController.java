package xyz.gotop.vhr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.gotop.vhr.model.Hr;
import xyz.gotop.vhr.model.RespBean;
import xyz.gotop.vhr.service.HrService;
import xyz.gotop.vhr.utils.HrUtils;

import java.util.List;

/**
 * Demo ChatRoomController
 *
 * @author Wolf-Liu
 * @date 2020/2/18 22:19
 */
@RestController
@RequestMapping("/chat-room")
public class ChatRoomController {
    @Autowired
    private HrService hrService;

    @GetMapping("/hrs")
    public RespBean getHrsExcludingCurrent() {
        Integer currentId = HrUtils.getHr().getId();
        List<Hr> hrs = hrService.getHrsExcludingCurrent(currentId);
        return RespBean.ok(null, hrs);
    }

}
