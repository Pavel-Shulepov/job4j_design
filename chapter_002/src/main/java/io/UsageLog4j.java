package io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Pavel Shulepov";
        short age = 36;
        int height = 164;
        char initial = 'P';
        double d = 100.101D;
        long l = 123456789L;
        float f = 1.2F;
        byte b = 28;
        boolean bool = true;
        LOG.debug("User info name : {}, age : {}, height: {}, initial: {}", name, age, height, initial);
        LOG.debug("double : {}, long : {}, float: {}, byte: {}, boolean: {}", d, l, f, b, bool);
    }
}
