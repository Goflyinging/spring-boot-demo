package com.lxing.valid.valid;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/***
 * Created on 2017/11/3 <br>
 * Description: <br>
 * @author lxing
 */
public class ForbiddenValidator implements ConstraintValidator<Forbidden, String> {

    private String[] forbiddenWords;

    @Override
    public void initialize(Forbidden constraintAnnotation) {
        this.forbiddenWords = constraintAnnotation.words();
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }
        for (String word : forbiddenWords) {
            if (value.contains(word)) {
                return false;//验证失败
            }
        }
        return true;
    }
}
