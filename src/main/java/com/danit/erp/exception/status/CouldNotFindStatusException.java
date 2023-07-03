package com.danit.erp.exception.status;

import com.danit.erp.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class CouldNotFindStatusException extends AbstractException {
  private  static final String MESSAGE = "За таким статусом немає -";
  private  static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

  public CouldNotFindStatusException() {
    super(CouldNotFindStatusException.STATUS, CouldNotFindStatusException.MESSAGE);
  }

  public CouldNotFindStatusException(Boolean show) {
    super(CouldNotFindStatusException.STATUS, CouldNotFindStatusException.MESSAGE, show);
  }
  public CouldNotFindStatusException(String subjectError) {
    super(CouldNotFindStatusException.STATUS, (CouldNotFindStatusException.MESSAGE+subjectError));
  }
}