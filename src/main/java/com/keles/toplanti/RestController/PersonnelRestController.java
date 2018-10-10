package com.keles.toplanti.RestController;

import com.google.gson.Gson;
import com.keles.toplanti.CustomExceptions.CustomException;
import com.keles.toplanti.EntityModel.PersonelEntityModel;
import com.keles.toplanti.Repository.PersonnelRepository;
import com.keles.toplanti.Service.PersonnelService;
import com.keles.toplanti.ViewModel.PersonnelViewModel;
import com.keles.toplanti.ViewModel.TransactionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Personel rest controller sınıfını temsil eder.
 *
 * @author Onur KELES
 * @since 9.10.2018
 */

@RestController
@RequestMapping("/personel")
public class PersonnelRestController {

    /**
     * Personel Servis
     */
    @Autowired
    private PersonnelService personnelService;

    @Autowired
    private PersonnelRepository personnelRepository;

    /**
     * Personel Listele
     *
     * @return ResponseEntity
     */
    @GetMapping(value = "/personelListele")
    public ResponseEntity<TransactionResult<?>> listPersonnel() {
        List<PersonnelViewModel> personnelViewModelList = this.personnelService.personnelList();//Personel lsitesini getir.
        return new ResponseEntity<>(new TransactionResult<>(personnelViewModelList), HttpStatus.CREATED);//İstemciye tüm personel listesini gönder.

    }

    /**
     * Personel Görüntüle
     *
     * @param id personel id
     * @return ResponseEntity
     */
    @GetMapping(value = "/personelGoruntule/{id}")
    public ResponseEntity<TransactionResult<?>> showPersonnel(@PathVariable("id") Long id) {
        PersonnelViewModel personnelViewModel = this.personnelService.findPersonnel(id);//id ye göre personeli getirir, yoksa null olarak döner.
        if (personnelViewModel != null) {
            return new ResponseEntity<>(new TransactionResult<>(personnelViewModel), HttpStatus.CREATED);//İstemciye sorgulanan personeli gönder.
        } else {
            CustomException customException = new CustomException("Personel Sorgulama Hatası", "Göderilen id hatalı olabilir veya böyle bir personel kayıtlarımızda bulunmamaktadır.", 5555, 200);
            return new ResponseEntity<>(new TransactionResult<>(customException), HttpStatus.BAD_REQUEST);//personel yoksa veya null döndü ise customExceptionu istemciye gönder.
        }
    }


    /**
     * Personel Entity Model Görüntüle
     *
     * @param id personel id
     * @return ResponseEntity
     */
    @GetMapping(value = "/personelEntityModelGoruntule/{id}")
    public ResponseEntity<TransactionResult<?>> showPersonnelEntityModel(@PathVariable("id") Long id) {
        PersonelEntityModel personelEntityModel = this.personnelRepository.findOneActive(id);//id ye göre personeli getirir, yoksa null olarak döner.
        if (personelEntityModel != null) {
            return new ResponseEntity<>(new TransactionResult<>(personelEntityModel), HttpStatus.CREATED);//İstemciye sorgulanan personeli gönder.
        } else {
            CustomException customException = new CustomException("Personel Sorgulama Hatası", "Göderilen id hatalı olabilir veya böyle bir personel kayıtlarımızda bulunmamaktadır.", 5555, 200);
            return new ResponseEntity<>(new TransactionResult<>(customException), HttpStatus.BAD_REQUEST);//personel yoksa veya null döndü ise customExceptionu istemciye gönder.
        }
    }


    /**
     * Personel eklemeyi tetikleyen rest controller.
     *
     * @param personnelJson personelEntityModel'i temsil eden json.
     * @return ResponseEntity
     */
    @PostMapping(value = "/personelEkle")
    public ResponseEntity<TransactionResult<?>> addPersonnel(@RequestBody @Valid String personnelJson) {

        Gson gson = new Gson();
        PersonelEntityModel personelEntityModel = gson.fromJson(personnelJson, PersonelEntityModel.class);//Gelen jsonu clasımıza ceviriyoruz
        Boolean operationComplete = this.personnelService.addPersonnel(personelEntityModel);//Personel ekleme işlemi yap ve ekleme işlemi başarılımı değilmi boolean olarak dön.

        if (operationComplete) {
            List<PersonnelViewModel> personnelViewModelList = this.personnelService.personnelList();//Ekleme işlemi başarılı ise oluşan yeni personel listesini getir.
            return new ResponseEntity<>(new TransactionResult<>(personnelViewModelList), HttpStatus.CREATED);//Ekleme işlemi başarılı ise istemciye tüm personel listesini gönder.
        } else {
            CustomException customException = new CustomException("Personel Ekleme Hatası", "Göderilen json hatalı olabilir veya yinelenen veri hatası  olabilir, id, tcNo veya personelNo başka bir personel tarafından kullanılıyor olabilir", 5556, 200);
            return new ResponseEntity<>(new TransactionResult<>(customException), HttpStatus.BAD_REQUEST);//ekleme işlemi başarısız ise customExceptionu istemciye gönder.
        }
    }


    /**
     * Personel güncellemeyi tetikleyen rest controller.
     *
     * @param personnelJson personelEntityModel'i temsil eden json.
     * @return ResponseEntity
     */
    @PutMapping(value = "/personelGuncelle")
    public ResponseEntity<TransactionResult<?>> updateersonnel(@RequestBody @Valid String personnelJson) {

        Gson gson = new Gson();
        PersonelEntityModel personelEntityModel = gson.fromJson(personnelJson, PersonelEntityModel.class);//Gelen jsonu clasımıza ceviriyoruz
        Boolean operationComplete = this.personnelService.updatePersonnel(personelEntityModel);//Personel güncelleme işlemi yap ve  işlem başarılı mı değil mi boolean olarak dön.

        if (operationComplete) {
            List<PersonnelViewModel> personnelViewModelList = this.personnelService.personnelList();//Guncelleme işlemi başarılı ise oluşan yeni personel listesini getir.
            return new ResponseEntity<>(new TransactionResult<>(personnelViewModelList), HttpStatus.CREATED);//Guncelleme işlemi başarılı ise istemciye tüm personel listesini gönder.
        } else {
            CustomException customException = new CustomException("Personel Guncelleme Hatası", "Göderilen json hatalı olabilir veya bu id ile bir kayıt bulunmamaktadır.", 5557, 200);
            return new ResponseEntity<>(new TransactionResult<>(customException), HttpStatus.BAD_REQUEST);//Guncelleme işlemi başarısız ise customExceptionu istemciye gönder.
        }
    }

    /**
     * Personel Sil
     *
     * @param id personel id
     * @return ResponseEntity
     */
    @DeleteMapping(value = "/personelSil/{id}")
    public ResponseEntity<TransactionResult<?>> deletePersonnel(@PathVariable("id") Long id) {
        Boolean operationComplete = this.personnelService.deletePersonnel(id);//personel silme işlemini yapar  işlem başarısını boolean olarak döner.
        if (operationComplete) {
            List<PersonnelViewModel> personnelViewModelList = this.personnelService.personnelList();//Silme işlemi başarılı ise oluşan yeni personel listesini getir.
            return new ResponseEntity<>(new TransactionResult<>(personnelViewModelList), HttpStatus.OK);//Silme işlemi başarılı ise istemciye yeni personel listesini gönder.
        } else {
            CustomException customException = new CustomException("Personel Silme Hatası", "Göderilen id hatalı olabir veya böyle bir personel kayıtlarımızda bulunmamış olabilir.", 5558, 200);
            return new ResponseEntity<>(new TransactionResult<>(customException), HttpStatus.BAD_REQUEST);//personel yoksa veya null dönmüşse customExceptionu istemciye gönder.
        }
    }
}
