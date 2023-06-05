package com.danit.erp.repository.dictionary;

import com.danit.erp.domain.dictionary.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByRole(String role);

  List<Role> findByDeletedFalse();

  Optional<Role> findByIdAndDeletedFalse(Long userId);


}
