package com.danit.erp.service.dictionary;

import com.danit.erp.domain.dictionary.Manager;
import com.danit.erp.repository.dictionary.ManagerRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ManagerService implements BaseService<Manager> {
  private final ManagerRepository managerRepository;

  @Override
  public List<Manager> findAll() {
    return managerRepository.findByDeletedFalse();
  }

  @Override
  public List<Manager> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public Manager findById(Long userId) {
    return managerRepository.findByIdAndDeletedFalse(userId).orElseThrow(() -> new Error());
    //TODO зробити помилку
  }


  @Override
  public Manager create(Manager obj) {
    Manager manager = Manager.builder().fullName(obj.getFullName()).build();
    return managerRepository.save(manager);
  }

  @Override
  public void update(Manager obj) {
    Manager findManager =
      managerRepository.findByIdAndDeletedFalse(obj.getId()).orElseThrow(() -> new Error());

    Manager manager =
      Manager.builder().id(findManager.getId()).fullName(obj.getFullName()).build();
    managerRepository.save(manager);
  }

  @Override
  public void delete(Long userId) {
    Manager manager =
      managerRepository.findById(userId).orElseThrow(() -> new Error());

    //TODO зробити помилку
    manager.setDeleted(true);
    managerRepository.save(manager);

  }
}
