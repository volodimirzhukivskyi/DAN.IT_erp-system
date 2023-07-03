package com.danit.erp.exception.id;

import com.danit.erp.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class CouldNotFindInvoiceException extends AbstractException {
  private  static final String MESSAGE = "The account for this id does not exist.";
  private  static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;

  public CouldNotFindInvoiceException() {
    super(CouldNotFindInvoiceException.STATUS, CouldNotFindInvoiceException.MESSAGE);
  }

  public CouldNotFindInvoiceException(Boolean show) {
    super(CouldNotFindInvoiceException.STATUS, CouldNotFindInvoiceException.MESSAGE, show);
  }
}