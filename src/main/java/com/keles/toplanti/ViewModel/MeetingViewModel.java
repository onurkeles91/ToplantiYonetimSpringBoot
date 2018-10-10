package com.keles.toplanti.ViewModel;

import java.util.List;

/**
 * Toplantı view modeli temsil eden sınıf.
 *
 * @author Onur KELES
 * @since 9.10.2018
 */

public class MeetingViewModel {
    //Mapper de dönüştürülürken eşleşmesi için entitiy modeldeki değişkenlerle aynı isim verildi.

    /**Id*/
    private Long id;

    /**Toplantı Adı*/
    private String meetingName;

    /**Personel Listesi*/
    List<PersonnelViewModel> personnelViewModelList;

    /**Getter Setter*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public List<PersonnelViewModel> getPersonnelViewModelList() {
        return personnelViewModelList;
    }

    public void setPersonnelViewModelList(List<PersonnelViewModel> personnelViewModelList) {
        this.personnelViewModelList = personnelViewModelList;
    }
}
