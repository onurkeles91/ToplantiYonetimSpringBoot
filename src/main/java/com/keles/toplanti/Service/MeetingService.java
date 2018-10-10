package com.keles.toplanti.Service;

import com.keles.toplanti.EntityModel.MeetingEntityModel;
import com.keles.toplanti.ViewModel.MeetingViewModel;

import java.util.List;

/**
 * Toplanti servis , interface sınıfını temsil eder.
 *
 * @author Onur KELES
 * @since 9.10.2018
 */

public interface MeetingService {
    /**
     * Toplantıları Listele.
     * @return toplantı view model list
     */
    List<MeetingViewModel> meetingList();

    /**
     * Toplantı Getiriri,Personelleri listeler.
     * @param id toplantı Id
     * @return toplantı view model
     */
    MeetingViewModel findMeeting(Long id);

    /**Toplantı Ekle
     * @param meetingEntityModel toplantı entity model.
     * @return boolean
     */
    Boolean addMeeting(MeetingEntityModel meetingEntityModel);

    /**Toplantı Güncelle
     * @param meetingEntityModel toplantı entity model.
     * @return boolean
     */
    Boolean updateMeeting(MeetingEntityModel meetingEntityModel);


    /**Toplantı Bilgilerini Güncelle(Toplantı ismi ,zamanı,..)   (personel listesini güncellemez.)
     * @param meetingEntityModel toplantı entity model.
     * @return boolean
     */
    Boolean updateMeetingInfo(MeetingEntityModel meetingEntityModel);


    /**Toplantı Sil
     * @param id toplantı id
     * @return boolean
     */
    Boolean deleteMeeting(Long id);

    /**
     * Toplantıya Personel Ekle.
     * @param meetingId toplantı id
     * @param personelId personel id
     * @return boolean islem sonucu
     */
    Boolean addPersonnelMeeting(Long meetingId,Long personelId);

    /**
     *Toplantıdan personel siler.
     * @param meetingId toplantı id
     * @param personelId personel id
     * @return boolean islem sonucu
     */
    Boolean deletePersonnelMeeting(Long meetingId,Long personelId);




}
