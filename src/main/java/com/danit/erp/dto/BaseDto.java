package com.danit.erp.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public  abstract class BaseDto<T> {
  private int totalPages;
  private long totalElements;
  private List<T> content = new ArrayList<>();
}
