package no.toreb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "spring.security.user.name=theUser",
        "spring.security.user.password=thePass",
        "spring.security.user.roles=user"
})
class ApplicationRunnerTests {

    @Test
    void contextLoads() {
    }

}
