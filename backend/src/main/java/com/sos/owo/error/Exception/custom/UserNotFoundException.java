package com.sos.owo.error.Exception.custom;

import com.sos.owo.error.Exception.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(int memberId){
        super("memberId: "+ memberId +" is not found");
    }
}
