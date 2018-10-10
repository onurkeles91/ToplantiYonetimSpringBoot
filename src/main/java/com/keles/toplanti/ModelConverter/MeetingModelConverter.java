package com.keles.toplanti.ModelConverter;

import com.keles.toplanti.EntityModel.MeetingEntityModel;
import com.keles.toplanti.EntityModel.PersonelEntityModel;
import com.keles.toplanti.ViewModel.MeetingViewModel;
import com.keles.toplanti.ViewModel.PersonnelViewModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

/**
 * Toplantı entiity modeli ,view modele dönüştüren sınıfı temsil eder.
 *
 * @author Onur KELES
 * @since 10.10.2018
 *
 * @see PersonnelModelConverter toplantı entity modelin içerisindeki personel entity model listesini  , Toplantı view modelin içerisindeki personel view model listesine dönüştürmek için  kullanılmıştır.
 */

@Mapper(componentModel = "spring")
public interface MeetingModelConverter {
    /**
     * View model oluştur.
     *
     * @param meetingEntityModel toplanti entitiy model
     * @return toplanti view model.
     */
    MeetingViewModel createViewModel(MeetingEntityModel meetingEntityModel,
                                     @Context PersonnelModelConverter personnelModelConverter);

    /**
     * Mapping bittikten sonra yapılacak işlemlerin yer aldığı metodtur.
     * @param meetingEntityModel toplantı entity model
     * @param meetingViewModel toplanti view model
     * @param personnelModelConverter persone entity modeli view modele dönüştüren mapper.
     */
    @AfterMapping
    default void afterMapping(MeetingEntityModel meetingEntityModel,
                              @MappingTarget MeetingViewModel meetingViewModel,
                              @Context PersonnelModelConverter personnelModelConverter){

        List<PersonelEntityModel> personelEntityModelList = meetingEntityModel.getPersonelEntityModelList();//entity modeldeki personel listesini çekiyoruz.
        List<PersonnelViewModel> personnelViewModelList = new ArrayList<>();//yeni bir personel view model list oluşturduk.

        for (PersonelEntityModel p : personelEntityModelList)
        {
            personnelViewModelList.add(personnelModelConverter.createViewModel(p));//personel entity model listesini  PersonnelModelConverter ile view model listesine çevirdik.
        }
        meetingViewModel.setPersonnelViewModelList(personnelViewModelList);//View modele  oluşturduğumuz  personeview modeli ekliyoruz.
    }

}