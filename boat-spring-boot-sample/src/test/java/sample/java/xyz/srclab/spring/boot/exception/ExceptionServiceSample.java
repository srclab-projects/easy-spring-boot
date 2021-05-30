package sample.java.xyz.srclab.spring.boot.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import xyz.srclab.common.exception.ExceptionStatus;
import xyz.srclab.spring.boot.autoconfigure.BoatAutoConfiguration;
import xyz.srclab.spring.boot.exception.EnableExceptionHandlingService;
import xyz.srclab.spring.boot.exception.ExceptionHandlingService;

import javax.annotation.Resource;

@SpringBootTest(classes = {
    BoatAutoConfiguration.class,
    RuntimeExceptionHandler.class,
    ThrowableHandler.class,
})
@EnableExceptionHandlingService
public class ExceptionServiceSample extends AbstractTestNGSpringContextTests {

    public static final Logger log = LoggerFactory.getLogger(ExceptionServiceSample.class);

    @Resource
    private ExceptionHandlingService exceptionHandlingService;

    @Test
    public void testExceptionStateService() {
        ExceptionStatus runtime = exceptionHandlingService.handle(new RuntimeException(), ExceptionStatus.class);
        log.info("runtime: {}", runtime);
        Assert.assertEquals(runtime.code(), "102");
        ExceptionStatus throwable = exceptionHandlingService.handle(new Exception(), ExceptionStatus.class);
        log.info("throwable: {}", throwable);
        Assert.assertEquals(throwable.code(), "101");
    }
}
