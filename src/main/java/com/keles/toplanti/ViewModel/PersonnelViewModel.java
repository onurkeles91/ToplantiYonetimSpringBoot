package com.keles.toplanti.ViewModel;

/**
 * Personel view modeli temsil eden sınıf.
 *
 * @author Onur KELES
 * @since 9.10.2018
 */
public class PersonnelViewModel {
  //Mapper de dönüştürülürken eşleşmesi için entitiy modeldeki değişkenlerle aynı isim verildi.

    /**Id*/
    private Long id;

    /**Ad*/
    private String name;

    /**Soyad*/
    private String surname;


    /**Getter Setter*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
