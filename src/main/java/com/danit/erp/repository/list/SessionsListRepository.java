package com.danit.erp.repository.list;

import com.danit.erp.domain.dictionary.Group;
import com.danit.erp.domain.list.sessions_list.SessionsList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionsListRepository extends JpaRepository<SessionsList, Long> {
 Optional<SessionsList> findAllByGroupList_Group(Group group);
}
