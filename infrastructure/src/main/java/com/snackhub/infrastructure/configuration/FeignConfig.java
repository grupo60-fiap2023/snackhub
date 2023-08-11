 package com.snackhub.infrastructure.configuration;

import feign.Logger;
import feign.okhttp.OkHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class FeignConfig {

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public CloseableHttpClient feignClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(10, TimeUnit.MINUTES);
        connectionManager.setMaxTotal(1000);
        connectionManager.setDefaultMaxPerRoute(20);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();

        Thread th = new IdleConnectionMonitorThread(connectionManager);

        th.start();
        return httpClient;
    }

    static class IdleConnectionMonitorThread extends Thread {

        private volatile boolean shutdown;
        private final PoolingHttpClientConnectionManager connMgr;

        public IdleConnectionMonitorThread(PoolingHttpClientConnectionManager connMgr) {
            super();
            this.setName("TCP-Ideal-Monitroing-Thread");
            this.connMgr = connMgr;
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    sleep(TimeUnit.MINUTES.toMillis(1));
                    // Close expired connections
                    connMgr.closeExpiredConnections();
                    // Optionally, close connections
                    // that have been idle longer than 60 sec
                    connMgr.closeIdleConnections(10, TimeUnit.MINUTES);
                }
            } catch (InterruptedException ex) {
                // terminate
            }
        }

        public void shutdown() {
            shutdown = true;
        }

}
}
