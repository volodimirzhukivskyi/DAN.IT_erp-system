package com.danit.erp.exception.number;

import com.danit.erp.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class CouldNotFindNumberException extends AbstractException {
  private  static final String MESSAGE = " with the entered code does not exist.";
  private  static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

  public CouldNotFindNumberException() {
    super(CouldNotFindNumberException.STATUS, CouldNotFindNumberException.MESSAGE);
  }

  public CouldNotFindNumberException(Boolean show) {
    super(CouldNotFindNumberException.STATUS, CouldNotFindNumberException.MESSAGE, show);
  }
  public CouldNotFindNumberException(String subjectError) {
    super(CouldNotFindNumberException.STATUS, (subjectError+CouldNotFindNumberException.MESSAGE));
  }
}