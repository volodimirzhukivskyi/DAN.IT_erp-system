package com.danit.erp.exception.number;

import com.danit.erp.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class CouldNotFindLegalException extends AbstractException {
  private  static final String MESSAGE = "The legal entity according to the entered code does not exist.";
  private  static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

  public CouldNotFindLegalException() {
    super(CouldNotFindLegalException.STATUS, CouldNotFindLegalException.MESSAGE);
  }

  public CouldNotFindLegalException(Boolean show) {
    super(CouldNotFindLegalException.STATUS, CouldNotFindLegalException.MESSAGE, show);
  }
}