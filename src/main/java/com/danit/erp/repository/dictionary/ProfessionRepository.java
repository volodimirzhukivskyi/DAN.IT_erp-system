package com.danit.erp.repository.dictionary;

import com.danit.erp.domain.dictionary.Profession;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, Long> {
  List<Profession> findByDeletedFalse();
  Page<Profession> findByDeletedFalse(Pageable pageable);
  Optional<Profession> findByIdAndDeletedFalse(Long userId);

  Optional<Profession>  findByName(String name);
}
