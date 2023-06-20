package com.danit.erp.repository.dictionary;

import com.danit.erp.domain.dictionary.Group;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
  Optional<Group> findByGroupName(String nameGroup);

  List<Group> findByDeletedFalse();

  Page<Group> findByDeletedFalse(Pageable pageable);

  Optional<Group> findByIdAndDeletedFalse(Long userId);


}
