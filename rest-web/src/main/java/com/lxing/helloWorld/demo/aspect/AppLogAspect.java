package com.lxing.helloWorld.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/***
 * Created on 2017/11/1 <br>
 * Description: [日志切面 Order  定义切面执行的优先级，数字越低，优先级越高]<br>
 * @author lxing
 * @version 1.0
 */
@Component
@Aspect
@Order(-10)
public class AppLogAspect {

  private static final Logger logger = LoggerFactory.getLogger(AppLogAspect.class);
  // 保证每个线程都有一个单独的实例
  private ThreadLocal<Long> threadLocal = new ThreadLocal<>();

  @Pointcut("execution(* com.lxing.demo.controller..*.*(..))")
  public void pointcut() {
  }

  @Before("pointcut()")
  public void doBefore(JoinPoint joinPoint) {
    threadLocal.set(System.currentTimeMillis());
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    // 记录请求的内容
    logger.info("Request URL: {}", request.getRequestURL().toString());
    logger.info("Request Method: {}", request.getMethod());
    logger.info("IP: {}", request.getRemoteAddr());
    logger.info("User-Agent:{}", request.getHeader("User-Agent"));
    logger.info("Class Method:{}",
        joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    logger.info("Cookies:{}", request.getCookies());
    logger.info("Params:{}", Arrays.toString(joinPoint.getArgs()));
    Enumeration<String> enums = request.getParameterNames();
    while (enums.hasMoreElements()) {
      String paraName = enums.nextElement();
      logger.info(paraName + ":" + request.getParameter(paraName));
    }
  }

  @After("pointcut()")
  public void doAfter(JoinPoint joinPoint) {
    logger.info("doAfter():{}", joinPoint.toString());
  }

  @AfterReturning("pointcut()")
  public void doAfterReturning(JoinPoint joinPoint) {
    logger.info("耗时 :{}", ((System.currentTimeMillis() - threadLocal.get())) + "ms");
    threadLocal.remove();
  }
}
