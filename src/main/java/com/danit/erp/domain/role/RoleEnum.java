package com.danit.erp.domain.role;


import static com.danit.erp.domain.role.Permission.ADMINISTRATOR_CREATE;
import static com.danit.erp.domain.role.Permission.ADMINISTRATOR_DELETE;
import static com.danit.erp.domain.role.Permission.ADMINISTRATOR_READ;
import static com.danit.erp.domain.role.Permission.ADMINISTRATOR_UPDATE;
import static com.danit.erp.domain.role.Permission.EMPLOYEE_CREATE;
import static com.danit.erp.domain.role.Permission.EMPLOYEE_DELETE;
import static com.danit.erp.domain.role.Permission.EMPLOYEE_READ;
import static com.danit.erp.domain.role.Permission.EMPLOYEE_UPDATE;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@RequiredArgsConstructor
public enum RoleEnum {
  ADMINISTRATOR(
    Set.of(ADMINISTRATOR_DELETE, ADMINISTRATOR_CREATE, ADMINISTRATOR_READ, ADMINISTRATOR_UPDATE,
      EMPLOYEE_DELETE, EMPLOYEE_CREATE, EMPLOYEE_READ, EMPLOYEE_UPDATE)),
  EMPLOYEE(Set.of(EMPLOYEE_DELETE, EMPLOYEE_CREATE, EMPLOYEE_READ, EMPLOYEE_UPDATE)),
  CLIENT(Collections.emptySet());
  @Getter
  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions().stream()
      .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
      .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;

  }
}
