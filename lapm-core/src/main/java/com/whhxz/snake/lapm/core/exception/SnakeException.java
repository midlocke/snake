package com.whhxz.snake.lapm.core.exception;

/**
 * SnakeException
 * Created by xuzhuo on 2019/6/12.
 */
public class SnakeException extends RuntimeException {
    public SnakeException(String message) {
        super(message);
    }

    public SnakeException(Throwable cause) {
        super(cause);
    }
}
