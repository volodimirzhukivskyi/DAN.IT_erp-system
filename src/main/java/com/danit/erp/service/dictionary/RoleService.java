package com.danit.erp.service.dictionary;

import com.danit.erp.domain.dictionary.Role;
import com.danit.erp.repository.dictionary.RoleRepository;
import com.danit.erp.repository.dictionary.UniversityRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService implements BaseService<Role> {
  private final RoleRepository roleRepository;

  @Override
  public List<Role> findAll() {
    return roleRepository.findByDeletedFalse();
  }

  @Override
  public List<Role> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Override
  public Role findById(Long userId) {
    return roleRepository.findByIdAndDeletedFalse(userId).orElseThrow(() -> new Error());
    //TODO зробити помилку
  }


  @Override
  public Role create(Role obj) {
    Role role = Role.builder().role(obj.getRole()).build();
    return roleRepository.save(role);
  }

  @Override
  public void update(Role obj) {
    Role findUniversity =
      roleRepository.findByIdAndDeletedFalse(obj.getId()).orElseThrow(() -> new Error());

    Role role =
      Role.builder().id(findUniversity.getId()).role(obj.getRole()).build();
    roleRepository.save(role);
  }

  @Override
  public void delete(Long userId) {
    Role role =
      roleRepository.findById(userId).orElseThrow(() -> new Error());

    //TODO зробити помилку
    role.setDeleted(true);
    roleRepository.save(role);

  }
}
