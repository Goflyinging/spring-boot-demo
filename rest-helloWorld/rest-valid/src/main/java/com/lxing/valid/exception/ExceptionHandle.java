package com.lxing.valid.exception;

import com.lxing.helloWorld.common.constant.StatusConstants;
import com.lxing.helloWorld.common.domain.ArgumentInvalidResult;
import com.lxing.helloWorld.common.exception.BaseException;
import com.lxing.helloWorld.common.exception.ValidateException;
import com.lxing.helloWorld.common.response.BaseResponse;
import com.lxing.helloWorld.common.response.ListResultResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@ResponseBody
public class ExceptionHandle {

  private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

  /***
   * 参数校验失败异常处理
   * @param ex
   * @return
   */
  @ExceptionHandler(ValidateException.class)
  public ListResultResponse<ArgumentInvalidResult> handleValidateException(ValidateException ex) {
    List<ArgumentInvalidResult> errors = ex.getErrors();
    return new ListResultResponse<ArgumentInvalidResult>(ex.getStatus(), ex.getMessage())
        .List(errors);
  }

  /***
   * BaseException异常处理
   * @param ex
   * @return
   */
  @ExceptionHandler(BaseException.class)
  public BaseResponse baseExceptionHandler(BaseException ex) {
    logger.error(ex.getMessage(), ex);
    return new BaseResponse(ex.getStatus(), ex.getMessage());
  }

  /***
   * 其它异常处理
   * @param ex
   * @return
   */
  @ExceptionHandler(Exception.class)
  public BaseResponse otherExceptionHandler(Exception ex) {
    logger.error(ex.getMessage(), ex);
    return new BaseResponse(StatusConstants.EX_OTHER_CODE, ex.getMessage());
  }
}
