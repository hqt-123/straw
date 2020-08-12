package cn.tedu.straw.api.ex.ServiceException;
/*
插入数据的异常
 */
public class InserException extends ServiceException {
    public InserException() {
        super();
    }

    public InserException(String message) {
        super(message);
    }

    public InserException(String message, Throwable cause) {
        super(message, cause);
    }

    public InserException(Throwable cause) {
        super(cause);
    }

    protected InserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
