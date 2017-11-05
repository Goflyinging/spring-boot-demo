package com.lxing.valid.valid;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/***
 * Created on 2017/11/3 <br>
 * Description: <br>
 * @author 01369533
 */
@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE}) //指明该类型的注解可以注解的程序元素的范围
@Retention(RUNTIME)//指明了该Annotation被保留的时间长短
@Constraint(validatedBy = ForbiddenValidator.class)//指定验证器
@Documented //指明拥有这个注解的元素可以被javadoc此类的工具文档化。
public @interface Forbidden {

    String[] words();

    //默认错误消息
    String message() default "";

    //分组
    Class<?>[] groups() default {};

    //负载
    Class<? extends Payload>[] payload() default {};

    //指定多个时使用
    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Forbidden[] value();
    }
}
