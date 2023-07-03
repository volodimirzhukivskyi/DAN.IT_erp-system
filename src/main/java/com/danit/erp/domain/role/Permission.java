package com.danit.erp.domain.role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
  ADMINISTRATOR_READ("administrator:read"),
  ADMINISTRATOR_UPDATE("administrator:update"),
  ADMINISTRATOR_CREATE("administrator:create"),
  ADMINISTRATOR_DELETE("administrator:delete"),
  EMPLOYEE_READ("employee:read"),
  EMPLOYEE_UPDATE("employee:update"),
  EMPLOYEE_CREATE("employee:create"),
  EMPLOYEE_DELETE("employee:delete"),

  ;
  @Getter
  private final String permission;
}
