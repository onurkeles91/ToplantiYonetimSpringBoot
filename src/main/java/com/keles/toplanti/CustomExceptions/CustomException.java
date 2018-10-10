package com.keles.toplanti.CustomExceptions;

/**
 * İstemci tarafından görünmesini istediğimiz exceptionları temsil eden sınıf.
 *
 * @author Onur KELES
 * @since 9.10.2018
 */
public class CustomException extends Exception{

    private final String title;
    private final int errorCode;
    private final int httpStatusCode;


    /**Nesne oluşturma metodları*/
    public CustomException(String title, String message, int errorCode, int httpStatusCode) {
        super(message);
        this.title = title;
        this.errorCode = errorCode;
        this.httpStatusCode = httpStatusCode;
    }

    public CustomException(String title, String message, int errorCode, int httpStatusCode, Exception exception) {
        super(message, exception);
        this.title = title;
        this.errorCode = errorCode;
        this.httpStatusCode = httpStatusCode;
    }

    /**Getter Setter*/
    public String getTitle() {
        return title;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }
}
