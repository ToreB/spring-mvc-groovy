package no.toreb;

import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit5.FlywayTestExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(
        classes = ApplicationRunner.class,
        properties = {
        "spring.security.user.name=theUser",
        "spring.security.user.password=thePass",
        "spring.security.user.roles=user",
        "spring.datasource.url=jdbc:hsqldb:mem:testdb",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.datasource.driverClassName=org.hsqldb.jdbc.JDBCDriver",
})
@ExtendWith(FlywayTestExtension.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext
class SpringMvcGroovyTests {

    @FlywayTest
    @BeforeEach
    void setUp() {
    }

    @Test
    void contextLoads() {
    }

}
