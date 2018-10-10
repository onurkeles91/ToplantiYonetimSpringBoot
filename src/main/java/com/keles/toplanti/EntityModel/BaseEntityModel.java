package com.keles.toplanti.EntityModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Diğer entitylerin extend edildiği , kök entity sınıfını temsil eder.
 *
 * @author Onur KELES
 * @since 7.10.2018
 */

/** BaseEntityModel'den türetilen sınıflar
 *  @see PersonelEntityModel Personel entity sınıfı.
 */

@MappedSuperclass
public abstract class BaseEntityModel implements Serializable {

    /**
     * Serializable interfacesini kullandıktan sonra hata almamak için
     * serialVersionUID  static final olarak tanımladık.
     */
    private static final long serialVersionUID = 1L;


    /**Id kolonu  (null olamaz , güncellenemez) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    protected Long id;

//    /**Versiyon Numarası*/
//    @Version
//    @Column(name = "VERSION")
//    protected int version = 0;

    /**Oluşturma Tarihi*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE")
    protected Date createDate;

    /**Son Güncelleme Tarihi*/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_DATE")
    protected Date updateDate;

    /**Verinin silinip silinmediğini tuttuğumuz değer*/
    @Column(name = "SOFT_DELETE")
    protected boolean softDelete = false;



    /** Base Entity Methodları */

    /**
     * Hash Code üreten metod.
     *
     * @return integer hash
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getId() != null ? this.getId().hashCode() : 0);

        return hash;
    }

    /**
     * Eşit olup olmadığını karşılaştıran metod.
     *
     * @param object object
     * @return boolean
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;

        BaseEntityModel other = (BaseEntityModel) object;
        if (this.getId() != other.getId() && (this.getId() == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Class name + id'sini string olarak dönen method.
     *
     * @return string className+id
     */
    @Override
    public String toString() {
        return this.getClass().getName() + " [ID=" + id + "]";
    }



    /**Getter Setter*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public int getVersion() {
//        return version;
//    }
//
//    public void setVersion(int version) {
//        this.version = version;
//    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isSoftDelete() {
        return softDelete;
    }

    public void setSoftDelete(boolean softDelete) {
        this.softDelete = softDelete;
    }
}

