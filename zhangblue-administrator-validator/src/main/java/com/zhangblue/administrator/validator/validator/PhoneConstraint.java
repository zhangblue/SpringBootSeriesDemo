package com.zhangblue.administrator.validator.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName PhoneConstraint
 * @Description 注解实现累
 * @Author zhangdi
 * @Date 2019/04/04 16:45
 **/
public class PhoneConstraint implements ConstraintValidator<PhoneValidator, String> {

  private String regexp;

  @Override
  public void initialize(PhoneValidator constraintAnnotation) {
    this.regexp = constraintAnnotation.regexp();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    if (value == null) {
      return true;
    }
    if (value.matches(regexp)) {
      return true;
    }
    return false;
  }
}
