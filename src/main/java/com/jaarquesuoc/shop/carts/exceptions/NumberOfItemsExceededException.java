package com.jaarquesuoc.shop.carts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class NumberOfItemsExceededException extends RuntimeException {
    public NumberOfItemsExceededException(final int numberItems) {
        super("Maximum number of items exceeded: " + numberItems);
    }
}
