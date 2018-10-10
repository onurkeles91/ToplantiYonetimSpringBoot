package com.keles.toplanti.Repository;

import com.keles.toplanti.EntityModel.BaseEntityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;


/**
 * BaseRepository sınıfını temsil eder.
 *
 * @author Onur KELES
 * @since 9.10.2018
 *
 * @param <T> Jenerik Tür
 */

@NoRepositoryBean
public interface BaseRepository <T extends BaseEntityModel> extends JpaRepository<T,Long> {

    /**
     * Soft delete. Veritabanından kalıcı olarak silmek yerine silindi özelliğini true yapar
     * @param id entity id
     */
    @Modifying
    @Transactional
    @Query("update #{#entityName} e set e.softDelete=true where e.id=?1")
    void softDelete(Long id);


    /**
     * Tüm silinmemiş kayıtları döner.
     * @return iterable silinmemiş entity listesi.
     */
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.softDelete = false")
    Iterable<T> findAllActive();


    /**
     * Aktif olan kayıtların sayısını döner.
     * @return long Aktif olan kayıt sayısı.
     */
    @Transactional(readOnly = true)
    @Query("select count(e) from #{#entityName} e where e.softDelete = false")
    long countActive();

    /**
     * Parametre olarak verilen ID'li kayıt eğer veritabanında aktif ise (silindi = false) ise
     * döner. Eğer bulamazsa null döner.
     * @param id entity id
     * @return T Veritabanı kaydı.
     */
    @Transactional(readOnly = true)
    @Query("select e from #{#entityName} e where e.id = ?1 and e.softDelete = false")
    T findOneActive(Long id);

}


