package com.keles.toplanti.EntityModel;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Toplantı Kayıtlarının temsil edildildiği sınıf.
 *
 * @author Onur KELES
 * @since 8.10.2018
 */

@Entity
@Table(name = "MEETING")
public class MeetingEntityModel extends BaseEntityModel {

    /**Toplantı Adı*/
    @Column(name = "MEETING_NAME")
    private String meetingName;

    /**Toplanti Tarihi*/
    @Column(name = "MEETING_DATE")
    private Date meetingDate;

    /**Personel Listesi*/
    @ManyToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PersonelEntityModel> personelEntityModelList;

    /** Getter Setter*/
    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public List<PersonelEntityModel> getPersonelEntityModelList() {
        return personelEntityModelList;
    }

    public void setPersonelEntityModelList(List<PersonelEntityModel> personelEntityModelList) {
        this.personelEntityModelList = personelEntityModelList;
    }
}
