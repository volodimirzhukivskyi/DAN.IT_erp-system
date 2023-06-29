package com.danit.erp.repository.list;

import com.danit.erp.domain.dictionary.Group;
import com.danit.erp.domain.list.group_list.GroupList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupListRepository extends JpaRepository<GroupList,Integer> {
  Optional<GroupList> findByGroup(Group group);
}
