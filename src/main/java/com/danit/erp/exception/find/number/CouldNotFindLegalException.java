package com.danit.erp.exception.find.number;

import com.danit.erp.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class CouldNotFindLegalException extends AbstractException {
  private  static final String MESSAGE = "Юридичної особи за введеним кодом не існує.";
  private  static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

  public CouldNotFindLegalException() {
    super(CouldNotFindLegalException.STATUS, CouldNotFindLegalException.MESSAGE);
  }

  public CouldNotFindLegalException(Boolean show) {
    super(CouldNotFindLegalException.STATUS, CouldNotFindLegalException.MESSAGE, show);
  }
}