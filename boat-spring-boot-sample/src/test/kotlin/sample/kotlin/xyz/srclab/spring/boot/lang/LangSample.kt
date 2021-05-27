package sample.kotlin.xyz.srclab.spring.boot.lang

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests
import org.testng.Assert
import org.testng.annotations.Test
import xyz.srclab.common.codec.aes.toAesKey
import xyz.srclab.spring.boot.lang.KeyString

@SpringBootTest(classes = [Starter::class])
open class LangSample : AbstractTestNGSpringContextTests() {

    @Value("AES,BASE64:rliqBhMdiKQDcH8lqNZdIg==")
    private lateinit var keyString: KeyString

    @Test
    fun testEncodeString() {
        log.info("encodeString: {}", keyString)
        val key = "123".toAesKey()
        Assert.assertEquals(keyString.decodeString(key), "some password")
    }

    companion object {
        private val log = LoggerFactory.getLogger(LangSample::class.java)
    }
}

@SpringBootApplication
open class Starter