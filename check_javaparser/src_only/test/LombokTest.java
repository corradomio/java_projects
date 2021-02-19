package test;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;

@Slf4j
@Data
public class LombokTest {

    private int anInt;

    public static void main(String[] args) {
        var iAmMutable = new Integer(10);
        val iAmConstant = 20;
        log.debug("log is defined by @Slf4j");

        LombokTest lt = new LombokTest();
        log.info(String.format("%d", lt.getAnInt()));
    }
}
