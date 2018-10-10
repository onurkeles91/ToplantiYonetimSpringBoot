package com.keles.toplanti.ServiceImpl;

import com.keles.toplanti.EntityModel.MeetingEntityModel;
import com.keles.toplanti.EntityModel.PersonelEntityModel;
import com.keles.toplanti.ModelConverter.MeetingModelConverter;
import com.keles.toplanti.ModelConverter.PersonnelModelConverter;
import com.keles.toplanti.Repository.MeetingRepository;
import com.keles.toplanti.Repository.PersonnelRepository;
import com.keles.toplanti.Service.MeetingService;
import com.keles.toplanti.ViewModel.MeetingViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Onur KELES
 * @since 10.10.2018
 */

@Service
public class MeetingServiceImpl implements MeetingService {

    /**
     * toplantı repostory
     */
    @Autowired
    private MeetingRepository meetingRepository;

    /**
     * Toplantı entity modeli view modele dönüştüren mapper
     */
    @Autowired
    private MeetingModelConverter meetingModelConverter;

    /**
     * Personel  entity modeli view modele dönüştüren mapper
     */
    @Autowired
    private PersonnelModelConverter personnelModelConverter;

    /**
     * PErsonel Repository
     */
    @Autowired
    private PersonnelRepository personnelRepository;


    /**
     * Toplantı listeler
     *
     * @return toplanti view model list
     */
    @Override
    public List<MeetingViewModel> meetingList() {
        Iterable<MeetingEntityModel> meetingEntityModelIterable = this.meetingRepository.findAllActive();//softDelete'i false olan tüm kayıtların listesini alma işlemi
        Stream<MeetingEntityModel> stream = StreamSupport.stream(meetingEntityModelIterable.spliterator(), true);
        List<MeetingViewModel> meetingViewModelList = stream.map(x -> this.meetingModelConverter.createViewModel(x, this.personnelModelConverter)).collect(Collectors.toList());//stream ile meetingModelConverter a entitiy modellerimizi gönderip view modelleri oluşturuyoruz.
        //toplantının içinde personel listesi olduğu için personelModelConverter i de içersinde gönderiyoruz.
        return meetingViewModelList;
    }

    /**
     * Secilen Toplantıyı getirir.
     *
     * @param id toplantı Id
     * @return toplantı view model
     */
    @Override
    public MeetingViewModel findMeeting(Long id) {
        MeetingEntityModel meetingEntityModel = this.meetingRepository.findOneActive(id);//Id ye göre  softdelete i false olan toplantıyı getirir.
        if (meetingEntityModel != null) {
            MeetingViewModel meetingViewModel = this.meetingModelConverter.createViewModel(meetingEntityModel, this.personnelModelConverter);//Toplantı entity modeli view modele dönüştürür.
            return meetingViewModel;
        } else {
            return null;//Böyle bir toplantı yoksa null döner.
        }
    }

    /**
     * Toplantı Ekler
     *
     * @param meetingEntityModel toplantı entity model.
     * @return boolean işlem sonucu
     */
    @Override
    public Boolean addMeeting(MeetingEntityModel meetingEntityModel) {
        Boolean isAvailable = meetingIsAvailable(meetingEntityModel);//Toplantının Mevcut olup olmadığını kontrol eder.
        Boolean operationComplete = false;//Kayıt etme isleminin başarılı olup olmadığını tutan değişken
        if (!isAvailable) {
            try {
                this.meetingRepository.save(meetingEntityModel);//mevcut değilse kayıt işlemini yapar.
                operationComplete = true;//sorunsuz bir şekilde kayıt yapılırsa islem başarılı.
            } catch (Exception e) {
                operationComplete = false;//kayıt esnasında hata alırsa işlem başarısız.
                System.out.println("Toplantı ekleme sırasında hata oluştu.");
            }
        }
        return operationComplete;
    }

    /**
     * Toplantı Günceller.
     *
     * @param meetingEntityModel toplantı entity model.
     * @return boolean işlem sonuvu
     */
    @Override
    public Boolean updateMeeting(MeetingEntityModel meetingEntityModel) {
        Boolean operationComplete = false;//Güncelleme  isleminin başarılı olup olmadığını tutan değişken
        try {
            this.meetingRepository.save(meetingEntityModel);//Güncelleme işlemini yapar.
            operationComplete = true;//sorunsuz bir şekilde güncelleme yapılırsa islem başarılı.
        } catch (Exception e) {
            operationComplete = false;//güncelleme esnasında hata alırsa işlem başarısız.
            System.out.println("Toplantı güncelleme sırasında hata oluştu.");
        }
        return operationComplete;
    }


    /**
     * Toplantı Bilgilerini Güncelle(Toplantı ismi ,zamanı,..)   (personel listesini güncellemez.)
     *
     * @param meetingEntityModel toplantı entity model.
     * @return boolean işlem sonuvu
     */
    @Override
    public Boolean updateMeetingInfo(MeetingEntityModel meetingEntityModel) {
        Boolean operationComplete = false;//Güncelleme  isleminin başarılı olup olmadığını tutan değişken

       MeetingEntityModel entityModel = this.meetingRepository.findOneActive(meetingEntityModel.getId());//güncellenmek istenen entitinin eski halini db den çektik.
        entityModel.setMeetingDate(meetingEntityModel.getMeetingDate());//istemciden gönderilen modelin içindeki tarihi atıyoruz
        entityModel.setMeetingName(meetingEntityModel.getMeetingName());////istemciden gönderilen modelin içindeki toplantı ismini atıyoruz
        //Geri kalan personel listesi ni güncelleme işlemne sokmuyoruz. Sadece toplantı bilgilerini update ediyoruz.
        try {
            this.meetingRepository.save(entityModel);//Güncelleme işlemini yapar.
            operationComplete = true;//sorunsuz bir şekilde güncelleme yapılırsa islem başarılı.
        } catch (Exception e) {
            operationComplete = false;//güncelleme esnasında hata alırsa işlem başarısız.
            System.out.println("Toplantı güncelleme sırasında hata oluştu.");
        }
        return operationComplete;
    }


    /**
     * Toplantı siler.
     *
     * @param id toplantı id
     * @return boolean işlem sonucu
     */
    @Override
    public Boolean deleteMeeting(Long id) {
        Boolean operationComplete = false; //Silme isleminin başarılı olup olmadığını tutan değişken
        try {
            this.meetingRepository.softDelete(id);//silme işlemi
            operationComplete = true;//sorunsuz bir şekilde silme yapıldı ise islem başarılı.
        } catch (Exception e) {
            operationComplete = false;//silme esnasında hata alırsa işlem başarısız.
            System.out.println("Toplantı silme sırasında hata oluştu.");
        }
        return operationComplete;
    }

    /**
     * Toplantıya personel ekler.
     *
     * @param meetingId  toplantı id
     * @param personelId personel id
     * @return boolean işlem sonucu
     */
    @Override
    public Boolean addPersonnelMeeting(Long meetingId, Long personelId) {
        MeetingEntityModel meetingEntityModel = this.meetingRepository.findOneActive(meetingId);//toplantı entitiy model
        PersonelEntityModel personelEntityModel = this.personnelRepository.findOneActive(personelId);//personel entity model
        Boolean isAvailable = this.personelIsAvailableInMeeting(meetingEntityModel, personelEntityModel);// personelin toplantıda olup olmadığına bakan metod.
        if (isAvailable) {
            return false;//toplantıda eklenmek istenen personel varsa işlem yapmaz.
        } else {//eğer toplantıda personel yoksa, personeli toplantı entity modele ekleyip, güncelleme işlemi yapılacak.
            List<PersonelEntityModel> personelEntityModelList = meetingEntityModel.getPersonelEntityModelList();//toplantıdaki peronel listesini aldık.
            personelEntityModelList.add(personelEntityModel);//eklemek istediğimiz personeli listeye ekledik.
            meetingEntityModel.setPersonelEntityModelList(personelEntityModelList);//listeyi toplantı entity modele atadık.
            return this.updateMeeting(meetingEntityModel);//boolean olarak işlem sonucu dönecek.
        }
    }

    /**
     * Toplantıdan personel siler.
     *
     * @param meetingId  toplantı id
     * @param personelId personel id
     * @return boolean işlem sonucu
     */
    @Override
    public Boolean deletePersonnelMeeting(Long meetingId, Long personelId) {
        MeetingEntityModel meetingEntityModel = this.meetingRepository.findOneActive(meetingId);
        PersonelEntityModel personelEntityModel = this.personnelRepository.findOneActive(personelId);
        Boolean isAvailable = this.personelIsAvailableInMeeting(meetingEntityModel, personelEntityModel);
        if (isAvailable) {
            List<PersonelEntityModel> personelEntityModelList = meetingEntityModel.getPersonelEntityModelList();//toplantıdaki peronel listesini aldık.
            personelEntityModelList.remove(personelEntityModel);//çıkarmak istediğimiz personeli listeden sildik.
            meetingEntityModel.setPersonelEntityModelList(personelEntityModelList);//listeyi toplantı entity modele atadık.
            return this.updateMeeting(meetingEntityModel);//boolean olarak işlem sonucu dönecek.
        } else {//eğer toplantıda personel yoksa silme işlemi yapmaz.
            return false;//toplantıda eklenmek istenen personel yoksa işlem yapmaz.
        }
    }


    /**
     * Toplantının Mevcut olup olmadığını kontrol eden metod.
     *
     * @param meetingEntityModel personel entity model
     * @return boolean mevcutMu
     */
    private Boolean meetingIsAvailable(MeetingEntityModel meetingEntityModel) {
        boolean isAvailable = false;
        Iterable<MeetingEntityModel> meetingEntityModelIterable = this.meetingRepository.findAllActive();//Kayıtlı olan toplantı listesini aldık.

        for (MeetingEntityModel m : meetingEntityModelIterable) {//tüm toplantılarla tek tek karşılaştır.
            if ((m.getId() == meetingEntityModel.getId()) || (m.getMeetingName().equals(meetingEntityModel.getMeetingName()))) {//Yeni kaydın id'si ve toplantı isimlerini karşılaştırdık .(karşılaştırma sorgusu daha da arttırılabilir aynı saate eklenemez   vs..)
                isAvailable = true;//toplantı ismi veya id den herhangi biri eşleşiyorsa kayıt mevcut.
            }
        }
        return isAvailable;//mevcut olup olmadığı bilgisini geri döner.
    }

    /**
     * Toplantının içerisinde aynı personelden var olup olmadığını kontrol eder..
     *
     * @param meetingEntityModel personel entity model
     * @return boolean mevcutMu
     */
    private Boolean personelIsAvailableInMeeting(MeetingEntityModel meetingEntityModel, PersonelEntityModel personelEntityModel) {
        boolean isAvailable = false;

        List<PersonelEntityModel> personelEntityModelList = meetingEntityModel.getPersonelEntityModelList();

        for (PersonelEntityModel p : personelEntityModelList) {//tüm personellerle tek tek karşılaştır.
            if ((p.getId() == personelEntityModel.getId())) {//Yeni kaydın id'si ve toplantının içerisindeki personel listesinde var ise true döner.
                isAvailable = true;
            }
        }
        return isAvailable;//mevcut olup olmadığı bilgisini geri döner.
    }



}
