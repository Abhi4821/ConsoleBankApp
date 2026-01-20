package util;

import CustomException.ValidationException;

@FunctionalInterface
public interface Validation <T>{
    void validate(T value) throws ValidationException;
}
