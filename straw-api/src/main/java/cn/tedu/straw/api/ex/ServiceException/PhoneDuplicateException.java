package cn.tedu.straw.api.ex.ServiceException;
/*
手机号码冲突的异常
 */
public class PhoneDuplicateException extends  ServiceException {
    public PhoneDuplicateException() {
        super();
    }

    public PhoneDuplicateException(String message) {
        super(message);
    }

    public PhoneDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneDuplicateException(Throwable cause) {
        super(cause);
    }

    protected PhoneDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
