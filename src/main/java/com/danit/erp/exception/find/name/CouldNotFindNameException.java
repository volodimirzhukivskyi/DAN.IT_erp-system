package com.danit.erp.exception.find.name;

import com.danit.erp.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class CouldNotFindNameException extends AbstractException {
  private  static final String MESSAGE = " за таким іменем не існує.";
  private  static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

  public CouldNotFindNameException() {
    super(CouldNotFindNameException.STATUS, CouldNotFindNameException.MESSAGE);
  }

  public CouldNotFindNameException(Boolean show) {
    super(CouldNotFindNameException.STATUS, CouldNotFindNameException.MESSAGE, show);
  }
  public CouldNotFindNameException(String subjectError) {
    super(CouldNotFindNameException.STATUS, (subjectError+CouldNotFindNameException.MESSAGE));
  }
}