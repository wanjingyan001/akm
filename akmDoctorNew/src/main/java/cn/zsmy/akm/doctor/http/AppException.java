package cn.zsmy.akm.doctor.http;

/**
 * Created by qinwei on 2015/10/6.
 * email:qinwei_it@163.com
 */
public class AppException extends Exception {
    public int responseCode;
    public String responseMessage;
    public ErrorType type;

    public enum ErrorType {
        PARSE_JSON,TIMEOUT, SERVER, IO, URL_NOT_VALID, CANCEL, FILE_NOT_FOUND, UPLOAD, MUNAL
    }

    public AppException(int responseCode, String responseMessage) {
        super(responseMessage);
        this.type = ErrorType.SERVER;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public AppException(ErrorType type, String detailMessage) {
        super(type.name()+":"+detailMessage);
        this.type = type;
    }
}
