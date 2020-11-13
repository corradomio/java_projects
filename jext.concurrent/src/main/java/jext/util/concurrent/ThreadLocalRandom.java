package jext.util.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ThreadLocalRandom {

    private static Map<Long, Random> randomMap = new HashMap<Long, Random>();

    public static synchronized Random current() {
        long threadId = Thread.currentThread().getId();
        if (!randomMap.containsKey(threadId))
            randomMap.put(threadId, new Random(System.currentTimeMillis() + threadId));
        return randomMap.get(threadId);
    }
}
