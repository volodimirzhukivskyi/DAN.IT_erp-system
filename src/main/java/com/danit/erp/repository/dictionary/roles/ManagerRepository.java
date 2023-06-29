package com.danit.erp.repository.dictionary.roles;

import com.danit.erp.domain.dictionary.roles.Manager;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {
  Optional<Manager> findByFullName(String fullName);
  Page<Manager> findByDeletedFalse(Pageable pageable);
  List<Manager> findByDeletedFalse();
  Optional<Manager> findByIdAndDeletedFalse(Integer userId);


}
