package com.danit.erp.exception.number;

import com.danit.erp.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class CouldNotFindContractException extends AbstractException {
  private  static final String MESSAGE = "Contract group does not exist for this number.";
  private  static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

  public CouldNotFindContractException() {
    super(CouldNotFindContractException.STATUS, CouldNotFindContractException.MESSAGE);
  }

  public CouldNotFindContractException(Boolean show) {
    super(CouldNotFindContractException.STATUS, CouldNotFindContractException.MESSAGE, show);
  }
}