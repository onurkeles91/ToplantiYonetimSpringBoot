package com.keles.toplanti.ViewModel;

/**
 * İslem Hatası sınıfı.Sunucu tarafında oluşan hataların istemci tarafına ulaşması için hazırlanmıştır.
 *
 * @author Onur KELES
 * @since 9.10.2018
 *
 * @see TransactionResult
 */
public class TransactionError {

    /**Hata adı*/
    private String errorName;

    /**Hata mesajı*/
    private Object errorMessage;

    /**Hata Kodu*/
    private int errorCode;

    /**Http Status kodu*/
    private int httpStatusCode;

    /**Getter Setter*/
    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
}
