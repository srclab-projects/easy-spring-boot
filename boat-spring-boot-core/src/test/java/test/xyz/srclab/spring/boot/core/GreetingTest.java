package test.xyz.srclab.spring.boot.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import xyz.srclab.spring.boot.autoconfigure.BoatAutoConfiguration;

@SpringBootTest(classes = {
    BoatAutoConfiguration.class,
    TestStartGreeting.class
})
public class GreetingTest extends AbstractTestNGSpringContextTests {

    private static final Logger logger = LoggerFactory.getLogger(GreetingTest.class);

    @Test
    public void testAutoConfigure() {
    }
}
