package com.danit.erp.service;


import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface BaseService<T,L> {
  @Transactional(readOnly = true)
  List<T> findAll();

  @Transactional(readOnly = true)
  default Page<T> getAllPageable(int size, int pageNumber) {
    return null;
  }

  @Transactional(readOnly = true)
  T findById(L userId);

  void update(T obj);

  T create(T obj);

  void delete(L id);
}

