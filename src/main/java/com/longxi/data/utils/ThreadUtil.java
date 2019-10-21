package com.longxi.data.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @author longxi.cwl
 * @date 2019/10/16
 */
public class ThreadUtil {
    private static Logger logger = LoggerFactory.getLogger(ThreadUtil.class);

    private static ExecutorService defaultExecutor;
    private static LinkedBlockingQueue<FutureTask<Object>> defaultBlockingQueue;

    private static final int DEFAULT_THREAD_POOL_SIZE = 100;
    private static final int DEFAULT_THREAD_QUEUE_SIZE = 500;

    static {
        // 默认线程池
        defaultExecutor = newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE);
        defaultBlockingQueue = new LinkedBlockingQueue<FutureTask<Object>>(DEFAULT_THREAD_QUEUE_SIZE);
        consume();
    }

    /**
     * 提交线程池缓存队列异步处理
     *
     * @param futureTask
     */
    public static void submit(FutureTask<Object> futureTask) {
        if (null != futureTask) {
            boolean flag = defaultBlockingQueue.offer(futureTask);
            if (!flag) {
                logger.error("提交线程池缓存队列异步处理，加入队列失败！FutureTask: " + futureTask.toString());
            }
        }
    }

    /**
     * 线程池缓存队列消费
     */
    public static void consume() {
        new Thread("fund_thread_pool") {
            @Override
            public void run() {
                while (true) {
                    try {
                        FutureTask<Object> futureTask = defaultBlockingQueue.take();
                        defaultExecutor.submit(futureTask);
                    } catch (Exception e) {
                        logger.error("default consume exception ", e);
                    }
                }
            }
        }.start();
    }

    /**
     * @return
     */
    public static JSONObject getThreadInfo() {
        JSONObject json = new JSONObject();
        json.put("thread_pool_size", ((ThreadPoolExecutor)defaultExecutor).getCorePoolSize());
        json.put("active_thread_count", ((ThreadPoolExecutor)defaultExecutor).getActiveCount());
        json.put("queue_count", ((ThreadPoolExecutor)defaultExecutor).getQueue().size());
        return json;
    }
}
