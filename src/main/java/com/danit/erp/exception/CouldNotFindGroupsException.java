package com.danit.erp.exception;

import org.springframework.http.HttpStatus;

public class CouldNotFindGroupsException extends AbstractException {
  private  static final String MESSAGE = "The list of groups for the entered group does not exist.";
  private  static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

  public CouldNotFindGroupsException() {
    super(CouldNotFindGroupsException.STATUS, CouldNotFindGroupsException.MESSAGE);
  }

  public CouldNotFindGroupsException(Boolean show) {
    super(CouldNotFindGroupsException.STATUS, CouldNotFindGroupsException.MESSAGE, show);
  }
}