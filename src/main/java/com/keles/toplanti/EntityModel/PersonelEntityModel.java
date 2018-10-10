package com.keles.toplanti.EntityModel;

import javax.persistence.*;
import java.util.Date;

/**
 * Personel kayıtlarının temsil edildiği sınıf.
 *
 * @author Onur KELES
 * @since 7.10.2018
 */

@Entity
@Table(name = "PERONNEL")
public class PersonelEntityModel extends BaseEntityModel {

    /**Adi*/
    @Column(name = "NAME")
    private String name;

    /**Soyadı*/
    @Column(name = "SURNAME")
    private String surname;

    /**Yaşı*/
    @Column(name = "AGE")
    private Integer age;

    /**T.C kimlik numarası*/
    @Column(name = "TC_NO")
    private String tcNo;

    /**Personel Numarası*/
    @Column(name = "PERSONNEL_NO")
    private Integer personnelNo;




    /**Getter Setter*/
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTcNo() {
        return tcNo;
    }

    public void setTcNo(String tcNo) {
        this.tcNo = tcNo;
    }

    public Integer getPersonnelNo() {
        return personnelNo;
    }

    public void setPersonnelNo(Integer personnelNo) {
        this.personnelNo = personnelNo;
    }


}
