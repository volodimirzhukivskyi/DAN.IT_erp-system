package com.danit.erp.exception.find;

import com.danit.erp.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class CouldNotFindGroupsException extends AbstractException {
  private  static final String MESSAGE = "Списку груп за введеною групою не існує.";
  private  static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

  public CouldNotFindGroupsException() {
    super(CouldNotFindGroupsException.STATUS, CouldNotFindGroupsException.MESSAGE);
  }

  public CouldNotFindGroupsException(Boolean show) {
    super(CouldNotFindGroupsException.STATUS, CouldNotFindGroupsException.MESSAGE, show);
  }
}