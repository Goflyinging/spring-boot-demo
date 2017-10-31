package com.lxing.demo.aspect;

import com.lxing.common.constant.StatusConstants;
import com.lxing.common.domain.ArgumentInvalidResult;
import com.lxing.common.exception.ValidateException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/***
 * Created on 2017/11/1 <br>
 * Description: [参数校验失败统一处理 ]<br>
 * @author lxing
 * @version 1.0
 */
@Aspect
@Component
@Order(-5)
public class ValidateAspect {

  private static final Logger logger = LoggerFactory.getLogger(ValidateAspect.class);


  @Around("execution(* com.lxing.demo.controller..*.*(..)) && args(..,bindingResult)")
  public Object handleValidateResult(ProceedingJoinPoint pjp, BindingResult bindingResult)
      throws Throwable {
    if (bindingResult.hasErrors()) {
      //按需重新封装需要返回的错误信息
      List<ArgumentInvalidResult> invalidArguments = new ArrayList<>();
      //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
      for (FieldError error : bindingResult.getFieldErrors()) {
        ArgumentInvalidResult invalidArgument = new ArgumentInvalidResult();
        invalidArgument.setDefaultMessage(error.getDefaultMessage());
        invalidArgument.setField(error.getField());
        invalidArgument.setRejectedValue(error.getRejectedValue());
        invalidArguments.add(invalidArgument);
      }
      throw new ValidateException(StatusConstants.EX_VALIDATE_CODE, invalidArguments);
    }
    Object result = pjp.proceed();
    return result;
  }
}


