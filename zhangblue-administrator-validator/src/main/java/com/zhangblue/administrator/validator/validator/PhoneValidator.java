package com.zhangblue.administrator.validator.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {PhoneConstraint.class})//指定注解实现类
public @interface PhoneValidator {

  String message() default "手机号格式错误";

  String regexp() default "^((13[0-9])|(15[^4,\\D])|(18[0,3-9]))\\d{8}$";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
