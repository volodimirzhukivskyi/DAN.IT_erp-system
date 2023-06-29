package com.danit.erp.service.dictionary.roles;

import com.danit.erp.domain.dictionary.roles.Role;
import com.danit.erp.exception.find.id.CouldNotFindException;
import com.danit.erp.repository.dictionary.roles.RoleRepository;
import com.danit.erp.service.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService implements BaseService<Role,Short> {
  private final RoleRepository roleRepository;

  @Override
  public List<Role> findAll() {
    return roleRepository.findByDeletedFalse();
  }

  @Override
  public Page<Role> getAllPageable(int size, int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, size);
    return roleRepository.findByDeletedFalse(pageable);

  }

  @Override
  public Role findById(Short userId) {
    return roleRepository.findByIdAndDeletedFalse(userId)
      .orElseThrow(() -> new CouldNotFindException("Ролі"));
  }


  @Override
  public Role create(Role obj) {
    Role role = Role.builder().role(obj.getRole()).build();
    return roleRepository.save(role);
  }

  @Override
  public void update(Role obj) {
    Role findUniversity = roleRepository.findByIdAndDeletedFalse(obj.getId())
      .orElseThrow(() -> new CouldNotFindException("Ролі"));

    Role role = Role.builder().id(findUniversity.getId()).role(obj.getRole()).build();
    roleRepository.save(role);
  }

  @Override
  public void delete(Short userId) {
    Role role =
      roleRepository.findById(userId).orElseThrow(() -> new CouldNotFindException("Ролі"));

    role.setDeleted(true);
    roleRepository.save(role);

  }
}
