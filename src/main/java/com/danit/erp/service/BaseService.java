package com.danit.erp.service;


import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface BaseService<T> {
  @Transactional(readOnly = true)
  List<T> findAll();

  @Transactional(readOnly = true)
  List<T> getAllPageable(int size, int pageNumber);

  @Transactional(readOnly = true)
  T getById(Long userId);

  void update(T obj);

  void create(T obj);

  void delete(Integer id);
}

