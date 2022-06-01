package com.winchesters.devopsify.exception.git;

import com.winchesters.devopsify.exception.GeneralException;

public class GitException extends GeneralException {
    public GitException(String code, String message) {
        super(code, message);
    }

    public GitException(Exception exception) {
        super(exception);
    }
}
