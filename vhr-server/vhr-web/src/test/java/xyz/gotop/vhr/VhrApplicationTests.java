package xyz.gotop.vhr;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class VhrApplicationTests {

    @Test
    void contextLoads() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println("=================================");
        for (int i = 0; i < 10; i++) {
            String encode = bCryptPasswordEncoder.encode("123456");
            System.out.println(encode);
        }
        System.out.println("=================================");
    }

}
