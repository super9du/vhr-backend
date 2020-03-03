package xyz.gotop.mailserver.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import xyz.gotop.vhr.model.Employee;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * Demo EmployeeReceiver
 *
 * @author Wolf-Liu
 * @date 2020/2/16 22:17
 */
@Component
public class MailReceiver {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    String fromAddr;
    @Autowired
    TemplateEngine engine;

    @RabbitListener(queues = "go-top.mail.welcome")
    public void get(Employee employee) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        Context context = new Context();
        context.setVariable("workId", employee.getWorkid());
        context.setVariable("name", employee.getName());
        context.setVariable("gender", employee.getGender());
        context.setVariable("department", employee.getDepartment().getName());
        context.setVariable("position", employee.getPosition().getName());
        String mailContent = engine.process("mail", context);
        try {
            helper.setFrom(fromAddr);
            helper.setTo(employee.getEmail());
            helper.setSentDate(new Date());
            helper.setSubject(employee.getName() + "：欢迎入职");
            helper.setText(mailContent, true);
        } catch (MessagingException e) {
            logger.error("入职欢迎邮件发送失败！", e);
        }
        javaMailSender.send(mimeMessage);
    }
}
