package com.keles.toplanti.Service;

import com.keles.toplanti.EntityModel.PersonelEntityModel;
import com.keles.toplanti.ViewModel.PersonnelViewModel;

import java.util.List;

/**
 * Personel servis , interface sınıfını temsil eder.
 *
 * @author Onur KELES
 * @since 9.10.2018
 */

public interface PersonnelService {

    /**
     * Personelleri Listele.
     * @return personel view model list
     */
    List<PersonnelViewModel> personnelList();

    /**
     * Personel Getir.
     * @param id personel id
     * @return persone view model
     */
    PersonnelViewModel findPersonnel(Long id);

    /**Personel Ekle
     * @param personelEntityModel personel entity model
     * @return islem Sonucu
     */
    Boolean addPersonnel(PersonelEntityModel personelEntityModel);

    /**
     * PErsonel Güncelle
     * @param personelEntityModel personel entity model
     * @return islem Sonucu
     */
     Boolean updatePersonnel(PersonelEntityModel personelEntityModel);

    /**Personel Sil
     * @param id personel id.
     * @return islem Sonucu
     */
    Boolean deletePersonnel(Long id);
}
