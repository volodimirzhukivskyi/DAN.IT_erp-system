package com.danit.erp.exception;

import org.springframework.http.HttpStatus;

public class CouldNotFindSessionsException extends AbstractException {
  private  static final String MESSAGE = "There are no sessions for this program.";
  private  static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

  public CouldNotFindSessionsException() {
    super(CouldNotFindSessionsException.STATUS, CouldNotFindSessionsException.MESSAGE);
  }

  public CouldNotFindSessionsException(Boolean show) {
    super(CouldNotFindSessionsException.STATUS, CouldNotFindSessionsException.MESSAGE, show);
  }
}