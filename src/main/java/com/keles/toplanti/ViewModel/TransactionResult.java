package com.keles.toplanti.ViewModel;

import com.keles.toplanti.CustomExceptions.CustomException;

import java.time.Instant;

/**
 * İşlem Sonuçlarını temsil eden sınıf. Sunucu tarafından oluşturulmuş sonuçları istemciye
 * tek tip olarak gonderilmesi için hazırlanmış sınıf.İçerisinde başarı durumu eğer oluşmuşsa
 * hata nedeni,işlemin gerçekleşme zamanı ve istemciye gönderilmek istenen veri yer almaktadır.
 *
 * @author Onur KELES
 * @since 9.10.2018
 *
 * @param <T> Jenerik Tür.
 */


public class TransactionResult <T>{
   /**Başarı durumu*/
    private boolean success;

    /**Oluşan Hata*/
    private TransactionError error;

    /**Veri*/
    private T data;

    /**İslemin gerceklesme Zamanı*/
    private Instant createTime;

    /**İstemciye veri göndermeyip işlemin başarılı olduğunu belirtir.*/
    public TransactionResult() {
        this.success = true;
        this.data = null;
        this.error = null;
        this.createTime = Instant.now();
    }

    /**İstemciye veri gönderme ve  işlemin başarılı olduğunu belirtir.*/
    public TransactionResult(T data) {
        this.success = true;
        this.data = data;
        this.error = null;
        this.createTime = Instant.now();
    }

    /**İstemcite gösterilmek istenen error'u ve başarısız olduğunu belirtr*/
    public TransactionResult(CustomException error) {
        this.error = new TransactionError();
        this.error.setErrorName(error.getTitle());
        this.error.setErrorCode(error.getErrorCode());
        this.error.setErrorMessage(error.getMessage());
        this.error.setHttpStatusCode(error.getHttpStatusCode());
        this.success = false;
        this.data = null;
        this.createTime = Instant.now();
    }


    /**Getter Setter*/
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public TransactionError getError() {
        return error;
    }

    public void setError(TransactionError error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }
}
