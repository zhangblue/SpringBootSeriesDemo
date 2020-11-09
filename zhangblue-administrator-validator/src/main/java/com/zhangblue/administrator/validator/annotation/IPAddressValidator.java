package com.zhangblue.administrator.validator.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author zhangd
 */
public class IPAddressValidator implements ConstraintValidator<IPAddress, String> {

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    return false;
  }
}
