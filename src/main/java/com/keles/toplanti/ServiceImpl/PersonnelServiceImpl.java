package com.keles.toplanti.ServiceImpl;

import com.keles.toplanti.EntityModel.PersonelEntityModel;
import com.keles.toplanti.ModelConverter.PersonnelModelConverter;
import com.keles.toplanti.Repository.PersonnelRepository;
import com.keles.toplanti.Service.PersonnelService;
import com.keles.toplanti.ViewModel.PersonnelViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Personel Serivis Impl sınıfını temsil eder.
 *
 * @author Onur KELES
 * @since 9.10.2018
 */

@Service
public class PersonnelServiceImpl implements PersonnelService {

    /**
     * Personel Repository
     */
    @Autowired
    private PersonnelRepository personnelRepository;

    /**
     * Personel view model dönüştürücü
     */
    @Autowired
    private PersonnelModelConverter personnelModelConverter;

    /**
     * Personel Listele
     * @return personel view model
     */
    @Override
    public List<PersonnelViewModel> personnelList() {
        Iterable<PersonelEntityModel> personelEntityModelList = this.personnelRepository.findAllActive();//softDelete'i false olan tüm kayıtların listesini alma işlemi
        Stream<PersonelEntityModel> stream = StreamSupport.stream(personelEntityModelList.spliterator(), true);
        List<PersonnelViewModel> personnelViewModelList = stream.map(x -> this.personnelModelConverter.createViewModel(x)).collect(Collectors.toList());//stream ile personnelModelConverter a entitiy modellerimizi gönderip view modelleri oluşturuyoruz.

        return personnelViewModelList;
    }

    /**
     * Personel Getir
     * @param id personel id
     * @return personel view model
     */
    @Override
    public PersonnelViewModel findPersonnel(Long id) {
        PersonelEntityModel personelEntityModel = this.personnelRepository.findOneActive(id);//Id ye göre  softdelete i false olan personeli getirir.
        if (personelEntityModel != null) {
            PersonnelViewModel personnelViewModel = this.personnelModelConverter.createViewModel(personelEntityModel);//PersonelEntitiy modeli view modele dönüştürür.
            return personnelViewModel;
        } else {
            return null;
        }
    }

    /**
     * Personel ekle
     * @param personelEntityModel personel entity model
     * @return islem başarılı mı sonucu
     */
    @Override
    public Boolean addPersonnel(PersonelEntityModel personelEntityModel) {
        Boolean isAvailable = personnelIsAvailable(personelEntityModel);//Personelin Mevcut olup olmadığını kontrol eder.
        Boolean operationComplete = false;//Kayıt etme isleminin başarılı olup olmadığını tutan değişken
        if (!isAvailable) {
            try {
                this.personnelRepository.save(personelEntityModel);//mevcut değilse kayıt işlemini yapar.
                operationComplete = true;//sorunsuz bir şekilde kayıt yapılırsa islem başarılı.
            } catch (Exception e) {
                operationComplete = false;//kayıt esnasında hata alırsa işlem başarısız.
                System.out.println("Personel ekleme sırasında hata oluştu.");
            }
        }
        return operationComplete;
    }

    /**
     * Personel Güncelle
     * @param personelEntityModel personel entity model
     * @return islem başarılı mı sonucu
     */
    @Override
    public Boolean updatePersonnel(PersonelEntityModel personelEntityModel) {
        Boolean operationComplete = false;//Güncelleme  isleminin başarılı olup olmadığını tutan değişken
            try {
                this.personnelRepository.save(personelEntityModel);//Güncelleme işlemini yapar.
                operationComplete = true;//sorunsuz bir şekilde güncelleme yapılırsa islem başarılı.
            } catch (Exception e) {
                operationComplete = false;//güncelleme esnasında hata alırsa işlem başarısız.
                System.out.println("Personel güncelleme sırasında hata oluştu.");
            }
        return operationComplete;
    }

    /**
     * PErsonel Sil
     * @param id personel id.
     * @return islem başarılı mı sonucu
     */
    @Override
    public Boolean deletePersonnel(Long id) {
        Boolean operationComplete = false; //Silme isleminin başarılı olup olmadığını tutan değişken
        try {
            this.personnelRepository.softDelete(id);//silme işlemi
            operationComplete = true;//sorunsuz bir şekilde silme yapıldı ise islem başarılı.
        } catch (Exception e) {
            operationComplete = false;//silme esnasında hata alırsa işlem başarısız.
            System.out.println("Personel silme sırasında hata oluştu.");
        }
        return operationComplete;
    }


    /**
     * Personelin Mevcut olup olmadığını kontrol eden metod.
     *
     * @param personelEntityModel personel entity model
     * @return boolean mevcutMu
     */
    private Boolean personnelIsAvailable(PersonelEntityModel personelEntityModel) {
        boolean isAvailable = false;
        Iterable<PersonelEntityModel> personelEntityModelList = this.personnelRepository.findAllActive();//Kayıtlı olan personel listesini aldık.

        for (PersonelEntityModel p : personelEntityModelList) {//tüm personellerle tek tek karşılaştır.
            if ((p.getId() == personelEntityModel.getId()) || (p.getTcNo().equals(personelEntityModel.getTcNo())) || (p.getPersonnelNo() == personelEntityModel.getPersonnelNo())) {//Yeni kaydın id'si , tc numarasını ve personel numarasını karşılaştırdık.(karşılaştırma sorgusu daha da arttırılabilir sgk no  vs..)
                isAvailable = true;//Tc,personelNo veya id den herhangi biri eşleşiyorsa kayıt mevcut.
            }
        }
        return isAvailable;//mevcut olup olmadığı bilgisini geri döner.
    }
}
