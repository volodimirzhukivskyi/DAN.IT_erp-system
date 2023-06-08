package com.danit.erp.service.dictionary.roles;

import com.danit.erp.domain.dictionary.roles.Coordinator;
import com.danit.erp.repository.dictionary.roles.CoordinatorRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CoordinatorService implements BaseService<Coordinator> {
  private final CoordinatorRepository coordinatorRepository;

  @Override
  public List<Coordinator> findAll() {
    return coordinatorRepository.findByDeletedFalse();
  }

  @Override
  public List<Coordinator> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public Coordinator findById(Long userId) {
    return coordinatorRepository.findByIdAndDeletedFalse(userId).orElseThrow(() -> new Error());
    //TODO зробити помилку
  }


  @Override
  public Coordinator create(Coordinator obj) {
    Coordinator coordinator = Coordinator.builder().fullName(obj.getFullName()).build();
    return coordinatorRepository.save(coordinator);
  }

  @Override
  public void update(Coordinator obj) {
    Coordinator findCoordinator =
      coordinatorRepository.findByIdAndDeletedFalse(obj.getId()).orElseThrow(() -> new Error());

    Coordinator coordinator =
      Coordinator.builder().id(findCoordinator.getId()).fullName(obj.getFullName()).build();
    coordinatorRepository.save(coordinator);
  }

  @Override
  public void delete(Long userId) {
    Coordinator coordinator =
      coordinatorRepository.findById(userId).orElseThrow(() -> new Error());

    //TODO зробити помилку
    coordinator.setDeleted(true);
    coordinatorRepository.save(coordinator);

  }
}
