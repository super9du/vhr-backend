package xyz.gotop.vhr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import xyz.gotop.vhr.model.Hr;

import java.util.Date;

/**
 * Demo WsController
 *
 * @author Wolf-Liu
 * @date 2020/2/23 19:57
 */
@Controller
public class WsController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void chat(Authentication authentication, Chat chat) {
        Hr hr = (Hr) authentication.getPrincipal();
        chat.setUsername(hr.getUsername());
        chat.setNickname(hr.getName());
        chat.setDate(new Date());
        simpMessagingTemplate.convertAndSendToUser(chat.getTo(), "/queue/chat", chat);
    }


    //--------- Inner Class ------------

    public static class Chat {
        private String to;
        private String username;
        private String nickname;
        private Date date;
        private String content;

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
