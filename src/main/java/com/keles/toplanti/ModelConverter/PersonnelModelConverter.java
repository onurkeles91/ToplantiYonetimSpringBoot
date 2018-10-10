package com.keles.toplanti.ModelConverter;

import com.keles.toplanti.EntityModel.PersonelEntityModel;
import com.keles.toplanti.ViewModel.PersonnelViewModel;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

/**
 * Personnel entitiy modeli ,viev modele dönüştüren sınıfı temsil eder.
 *
 * @author Onur KELES
 * @since 9.10.2018
 */

@Mapper(componentModel = "spring" )
public interface PersonnelModelConverter {
    /**
     * View model oluştur.
     * @param personelEntityModel personen entitiy model
     * @return personel view model.
     */

    PersonnelViewModel createViewModel( PersonelEntityModel personelEntityModel);

}
