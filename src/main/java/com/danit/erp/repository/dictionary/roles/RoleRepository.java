package com.danit.erp.repository.dictionary.roles;

import com.danit.erp.domain.dictionary.roles.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Short> {
  Optional<Role> findByRole(String role);
  Page<Role> findByDeletedFalse(Pageable pageable);

  List<Role> findByDeletedFalse();

  Optional<Role> findByIdAndDeletedFalse(Short userId);


}
