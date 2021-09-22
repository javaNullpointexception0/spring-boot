package com.lzj.exception;

/**
 * @author Created by luzhenjiang
 * @date 2021/9/9 13:51
 * @description
 */
public class OptimisticLockerException extends RuntimeException {

    public OptimisticLockerException(String message) {
        super(message);
    }

}
