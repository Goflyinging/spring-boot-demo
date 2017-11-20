package com.lxing.valid.exception;

import com.lxing.helloWorld.common.domain.ArgumentInvalidResult;
import com.lxing.helloWorld.common.exception.BaseException;
import com.lxing.helloWorld.common.exception.ValidateException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/***
 * Created on 2017/10/10 <br>
 * Description: [controller异常处理]<br>
 * @author lxing
 * @version 1.0
 */
@ControllerAdvice
//@ControllerAdvice  注解了这个类  @ExceptionHandler方法上的注解会作用于全部的@Controller类
@ResponseBody
public class ExceptionHandle {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    /***
     * 参数校验失败异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<List<ArgumentInvalidResult>> handleValidateException(ValidateException ex) {
        List<ArgumentInvalidResult> errors = ex.getErrors();
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /***
     * BaseException异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<String> baseExceptionHandler(BaseException ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /***
     * 其它异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> otherExceptionHandler(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
