package com.danit.erp.repository.dictionary;

import com.danit.erp.domain.dictionary.Group;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
  Optional<Group> findByGroupName(String nameGroup);

  List<Group> findByDeletedFalse();
  Optional<Group> findByIdAndDeletedFalse(Long userId);


}
