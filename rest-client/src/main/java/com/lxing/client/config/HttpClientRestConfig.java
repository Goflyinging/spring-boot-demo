package com.lxing.client.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.http.Header;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/***
 * Created on 2017/11/3 <br>
 * Description: <br>
 * @author 01369533
 */
@Configuration
public class HttpClientRestConfig {
//    @Bean
//    public ClientHttpRequestFactory clientHttpRequestFactory() {
//        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//        //clientHttpRequestFactory.setHttpClient();
//        //这里是使用了自定义的一个HttpsClientPoolThread线程池单例 以后有机会会单独写文章展示其配置内容, 大家可以先使用默认的HttpClients.createDefault()进行配置,或自定义线程池;
//        clientHttpRequestFactory.setConnectTimeout(10000);
//        clientHttpRequestFactory.setReadTimeout(10000);
//        clientHttpRequestFactory.setConnectionRequestTimeout(200);
//        return clientHttpRequestFactory;
//    }

//  @Bean
//  public RestTemplate restTemplate() {
//    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//    requestFactory.setConnectTimeout(1000);// 设置超时
//    requestFactory.setReadTimeout(1000);
//    RestTemplate restTemplate = new RestTemplate(requestFactory);
//    return  restTemplate;
//  }
  @Bean
  public RestTemplate restTemplate() {
    // 长连接保持30秒
    PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager(
        30, TimeUnit.SECONDS);
    // 总连接数
    pollingConnectionManager.setMaxTotal(40);
    // 同路由的并发数
    pollingConnectionManager.setDefaultMaxPerRoute(30);

    HttpClientBuilder httpClientBuilder = HttpClients.custom();
    httpClientBuilder.setConnectionManager(pollingConnectionManager);
    // 重试次数，默认是3次，没有开启
    httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(1, true));
    // 保持长连接配置，需要在头添加Keep-Alive
    httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());

//        RequestConfig.Builder builder = RequestConfig.custom();
//        builder.setConnectionRequestTimeout(200);
//        builder.setConnectTimeout(5000);
//        builder.setSocketTimeout(5000);
//
//        RequestConfig requestConfig = builder.build();
//        httpClientBuilder.setDefaultRequestConfig(requestConfig);

    List<Header> headers = new ArrayList<>();
    headers.add(new BasicHeader("User-Agent",
        "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
    headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
    headers.add(new BasicHeader("Accept-Language", "zh-CN"));
    headers.add(new BasicHeader("Connection", "Keep-Alive"));

    httpClientBuilder.setDefaultHeaders(headers);

    CloseableHttpClient httpClient = httpClientBuilder.build();

    // httpClient连接配置，底层是配置RequestConfig
    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
        httpClient);
    // 连接超时
    clientHttpRequestFactory.setConnectTimeout(5000);
    // 数据读取超时时间，即SocketTimeout
    clientHttpRequestFactory.setReadTimeout(5000);
    // 连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
    clientHttpRequestFactory.setConnectionRequestTimeout(200);
    // 缓冲请求数据，默认值是true。通过POST或者PUT大量发送数据时，建议将此属性更改为false，以免耗尽内存。
    // clientHttpRequestFactory.setBufferRequestBody(false);

    // 添加内容转换器
//    List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//    messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
//    messageConverters.add(new FormHttpMessageConverter());
//    messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
//    messageConverters.add(new MappingJackson2HttpMessageConverter());

    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setRequestFactory(clientHttpRequestFactory);
    // restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
    return restTemplate;
  }
}
