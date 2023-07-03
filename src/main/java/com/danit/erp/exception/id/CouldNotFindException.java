package com.danit.erp.exception.id;

import com.danit.erp.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class CouldNotFindException extends AbstractException {
  private  static final String MESSAGE = "  for the provided ID does not exist.";
  private  static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

  public CouldNotFindException() {
    super(CouldNotFindException.STATUS, CouldNotFindException.MESSAGE);
  }

  public CouldNotFindException(Boolean show) {
    super(CouldNotFindException.STATUS, CouldNotFindException.MESSAGE, show);
  }
  public CouldNotFindException(String subjectError) {
    super(CouldNotFindException.STATUS,(subjectError+ CouldNotFindException.MESSAGE));
  }
}