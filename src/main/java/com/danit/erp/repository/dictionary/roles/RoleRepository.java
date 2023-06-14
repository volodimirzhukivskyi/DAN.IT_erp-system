package com.danit.erp.repository.dictionary.roles;

import com.danit.erp.domain.dictionary.roles.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByRole(String role);
  Page<Role> findByDeletedFalse(Pageable pageable);

  List<Role> findByDeletedFalse();

  Optional<Role> findByIdAndDeletedFalse(Long userId);


}
