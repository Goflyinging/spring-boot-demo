package com.lxing.client.config;

import org.apache.http.Header;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/***
 * Created on 2017/11/3 <br>
 * Description:restTemplate配置 <br>
 * @author lxing
 */
@Configuration
public class HttpClientRestConfig {
    /***
     * 回收空闲连接定期时间
     */
    private long maxIdleTime = 60L;

    /***
     * 链接存活时间
     */
    private long timeToLive = 60L;
    /***
     * 连接池最大连接总数
     */
    private int poolMaxTotal = 40;
    /***
     * 每个路由最大的连接数
     */
    private int poolMaxPerRounte = 30;
    /***
     *  缓冲请求数据，默认值是true。
     *  通过POST或者PUT大量发送数据时，建议将此属性更改为false，以免耗尽内存。
     */
    private boolean bufferRequestBody = false;

    /***
     * 连接超时时间
     */
    private int connectTimeout = 3 * 1000;

    /***
     *等待数据超时时间
     */
    private int readTimeout = 3 * 1000;

    /***
     * 从连接池获取连接的超时时间
     */
    private int connectionRequestTimeout = 200;

    /***
     * StringHttpMessageConverter 编码
     */
    private String charsetName = "utf-8";


    /***
     * spring客户端访问restful服务类  支持http方法  json等数据转换（jackson fastjson） 错误处理
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(clientHttpRequestFactory());
        restTemplate.setErrorHandler(new GlobalResponseErrorHandler());
        return restTemplate;
    }

    /***
     * 使用HttpClient 帮助类
     * @return
     */
    @Bean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {

        // 长连接保持30秒
        PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager(
                timeToLive, TimeUnit.SECONDS);
        // 总连接数
        pollingConnectionManager.setMaxTotal(poolMaxTotal);
        // 同路由的并发数
        pollingConnectionManager.setDefaultMaxPerRoute(poolMaxPerRounte);

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN"));
        headers.add(new BasicHeader("Connection", "Keep-Alive"));

        CloseableHttpClient httpClient = HttpClients.custom()
                //请求头
                .setDefaultHeaders(headers)
                //连接池
                .setConnectionManager(pollingConnectionManager)
                //保持长链接配置
                .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
                //重试次数
                .setRetryHandler(new DefaultHttpRequestRetryHandler(1, false))
                //定期回收过期连接
                .evictExpiredConnections()
                //定期回收空闲连接
                .evictIdleConnections(maxIdleTime, TimeUnit.SECONDS)
                .build();
        // httpClient连接配置，底层是配置RequestConfig
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                httpClient);
        // 连接超时
        clientHttpRequestFactory.setConnectTimeout(connectTimeout);
        // 数据读取超时时间，即SocketTimeout
        clientHttpRequestFactory.setReadTimeout(readTimeout);
        // 连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
        clientHttpRequestFactory.setConnectionRequestTimeout(connectionRequestTimeout);
        // 缓冲请求数据，默认值是true。通过POST或者PUT大量发送数据时，建议将此属性更改为false，以免耗尽内存。
        clientHttpRequestFactory.setBufferRequestBody(bufferRequestBody);

        return clientHttpRequestFactory;
    }
}
