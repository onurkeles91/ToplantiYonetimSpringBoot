package com.keles.toplanti.RestController;

import com.google.gson.Gson;
import com.keles.toplanti.CustomExceptions.CustomException;
import com.keles.toplanti.EntityModel.MeetingEntityModel;
import com.keles.toplanti.Repository.MeetingRepository;
import com.keles.toplanti.Service.MeetingService;
import com.keles.toplanti.ViewModel.MeetingViewModel;
import com.keles.toplanti.ViewModel.TransactionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Toplantı rest controller sınıfını temsil eder.
 *
 * @author Onur KELES
 * @since 10.10.2018
 */

@RestController
@RequestMapping("toplanti")
public class MeetingRestController {

    @Autowired
    private MeetingService meetingService;

@Autowired
   private MeetingRepository meetingRepository;
    /**
     * Toplanti Listele
     * @return ResponseEntity
     */
    @GetMapping(value = "/toplantiListele")
    public ResponseEntity<TransactionResult<?>> listMeeting(){
        List<MeetingViewModel> meetingViewModelList = this.meetingService.meetingList();//Toplanti lsitesini getir.
        return new ResponseEntity<>(new TransactionResult<>(meetingViewModelList), HttpStatus.CREATED);//İstemciye tüm personel listesini gönder.
    }

    /**
     * Toplanti Görüntüle , Personelleri Listele
     * @param id toplanti id
     * @return ResponseEntity
     */
    @GetMapping (value = "/toplantiGoruntule/{id}")
    public ResponseEntity<TransactionResult<?>> showMeeting(@PathVariable("id") Long id){
        MeetingViewModel meetingViewModel =  this.meetingService.findMeeting(id);//id ye göre toplantiyi getirir, yoksa null olarak döner.
        if(meetingViewModel != null) {
            return new ResponseEntity<>(new TransactionResult<>(meetingViewModel), HttpStatus.CREATED);//İstemciye sorgulanan toplantiyi gönder.
        }else {
            CustomException customException = new CustomException("Toplanti Sorgulama Hatası","Göderilen id hatalı olabilir veya böyle bir toplanti kayıtlarımızda bulunmamaktadır.",6555,200);
            return new ResponseEntity<>(new TransactionResult<>(customException), HttpStatus.BAD_REQUEST);//toplanti yoksa veya null döndü ise customExceptionu istemciye gönder.
        }
    }



    /**
     * Toplanti Görüntüle , Personelleri Listele
     * @param id toplanti id
     * @return ResponseEntity
     */
    @GetMapping (value = "/toplantiEntityModelGoruntule/{id}")
    public ResponseEntity<TransactionResult<?>> showMeetingEntityModel(@PathVariable("id") Long id){
        MeetingEntityModel meetingEntityModel =  this.meetingRepository.findOneActive(id);//id ye göre toplantiyi getirir, yoksa null olarak döner.
        if(meetingEntityModel != null) {
            return new ResponseEntity<>(new TransactionResult<>(meetingEntityModel), HttpStatus.CREATED);//İstemciye sorgulanan toplantiyi gönder.
        }else {
            CustomException customException = new CustomException("Toplanti Sorgulama Hatası","Göderilen id hatalı olabilir veya böyle bir toplanti kayıtlarımızda bulunmamaktadır.",6555,200);
            return new ResponseEntity<>(new TransactionResult<>(customException), HttpStatus.BAD_REQUEST);//toplanti yoksa veya null döndü ise customExceptionu istemciye gönder.
        }
    }


    /**
     * Toplanti eklemeyi tetikleyen rest controller.
     *
     * @param meetingJson meetingEntityModel'i temsil eden json.
     * @return ResponseEntity
     */
    @PostMapping(value = "/toplantiEkle")
    public ResponseEntity<TransactionResult<?>> addMeeting(@RequestBody @Valid String meetingJson) {

        Gson gson = new Gson();
        MeetingEntityModel meetingEntityModel =gson.fromJson(meetingJson, MeetingEntityModel.class);//Gelen jsonu clasımıza ceviriyoruz
        Boolean operationComplete = this.meetingService.addMeeting(meetingEntityModel);//Toplanti ekleme işlemi yap ve ekleme işlemi başarılımı değilmi boolean olarak dön.
        meetingEntityModel.setPersonelEntityModelList(null);//Toplantı tanımlanırken personel eklenmesine izin vermiyoruz.
        if (operationComplete) {
            List<MeetingViewModel> meetingViewModelList = this.meetingService.meetingList();//Ekleme işlemi başarılı ise oluşan yeni toplanti listesini getir.
            return new ResponseEntity<>(new TransactionResult<>(meetingViewModelList), HttpStatus.CREATED);//Ekleme işlemi başarılı ise istemciye tüm toplanti listesini gönder.
        } else {
            CustomException customException = new CustomException("Toplanti Ekleme Hatası","Göderilen json hatalı olabilir veya yinelenen veri hatası  olabilir, id,toplantı ismini başka bir kayıt tarafından kullanılıyor olabilir",6556,200);
            return new ResponseEntity<>(new TransactionResult<>(customException), HttpStatus.BAD_REQUEST);//ekleme işlemi başarısız ise customExceptionu istemciye gönder.
        }
    }


    /**
     * Toplanti güncellemeyi tetikleyen rest controller.
     *
     * @param meetingJson personelEntityModel'i temsil eden json.
     * @return ResponseEntity
     */
    @PutMapping(value = "/toplantiBilgileriniGuncelle")
    public ResponseEntity<TransactionResult<?>> updateMeeting(@RequestBody @Valid String meetingJson) {

        Gson gson = new Gson();
        MeetingEntityModel meetingEntityModel =gson.fromJson(meetingJson, MeetingEntityModel.class);//Gelen jsonu clasımıza ceviriyoruz
        Boolean operationComplete = this.meetingService.updateMeetingInfo(meetingEntityModel);//Toplanti güncelleme işlemi yap ve  işlem başarılı mı değil mi boolean olarak dön.

        if (operationComplete) {
            List<MeetingViewModel> meetingViewModelList = this.meetingService.meetingList();//Guncelleme işlemi başarılı ise oluşan yeni toplanti listesini getir.
            return new ResponseEntity<>(new TransactionResult<>(meetingViewModelList), HttpStatus.CREATED);//Guncelleme işlemi başarılı ise istemciye tüm toplanti listesini gönder.
        } else {
            CustomException customException = new CustomException("Toplanti Guncelleme Hatası","Göderilen json hatalı olabilir veya bu id ile bir kayıt bulunmamaktadır.",6557,200);
            return new ResponseEntity<>(new TransactionResult<>(customException), HttpStatus.BAD_REQUEST);//Guncelleme işlemi başarısız ise customExceptionu istemciye gönder.
        }
    }

    /**
     * Toplanti Sil
     * @param id toplanti id
     * @return ResponseEntity
     */
    @DeleteMapping (value = "/toplantiSil/{id}")
    public ResponseEntity<TransactionResult<?>> deleteMeeting(@PathVariable("id") Long id){
        Boolean operationComplete  =  this.meetingService.deleteMeeting(id);//toplanti silme işlemini yapar  işlem başarısını boolean olarak döner.
        if(operationComplete) {
            List<MeetingViewModel> meetingViewModelList = this.meetingService.meetingList();//Silme işlemi başarılı ise oluşan yeni toplanti listesini getir.
            return new ResponseEntity<>(new TransactionResult<>(meetingViewModelList), HttpStatus.OK);//Silme işlemi başarılı ise istemciye yeni toplanti listesini gönder.
        }else {
            CustomException customException = new CustomException("Toplanti Silme Hatası","Göderilen id hatalı olabir veya böyle bir toplanti kayıtlarımızda bulunmamış olabilir.",6558,200);
            return new ResponseEntity<>(new TransactionResult<>(customException), HttpStatus.BAD_REQUEST);//toplanti yoksa veya null dönmüşse customExceptionu istemciye gönder.
        }
    }

    /**
     * Toplantıya personel ekle
     * @param toplantiId toplanti id
     * @param personelId persone id
     * @return ResponseEntity
     */
    @GetMapping (value = "/toplantiyaPersonelEkle/{toplantiId}/{personelId}")
    public ResponseEntity<TransactionResult<?>> addPersonnel(@PathVariable("toplantiId") Long toplantiId,@PathVariable("personelId") Long personelId) {
        Boolean operationComplete  =  this.meetingService.addPersonnelMeeting(toplantiId,personelId);//toplantiya personel ekleme işlemini yapar  işlem başarısını boolean olarak döner.
        if(operationComplete) {
            List<MeetingViewModel> meetingViewModelList = this.meetingService.meetingList();//Personel Ekleme işlemi başarılı ise oluşan yeni toplanti listesini getir.
            return new ResponseEntity<>(new TransactionResult<>(meetingViewModelList), HttpStatus.CREATED);//Ekleme işlemi başarılı ise istemciye yeni toplanti listesini gönder.
        }else {
            CustomException customException = new CustomException("Toplantiya Personel Ekleme Hatası","Göderilen id hatalı olabir veya böyle bir toplanti yada personel kayıtlarımızda bulunmamış olabilir.",6559,200);
            return new ResponseEntity<>(new TransactionResult<>(customException), HttpStatus.BAD_REQUEST);//toplanti yoksa veya null dönmüşse customExceptionu istemciye gönder.
        }
    }


    /**
     * Toplantıdan personel sil
     * @param toplantiId toplanti id
     * @param personelId persone id
     * @return ResponseEntity
     */
    @GetMapping (value = "/toplantidanPersoneSil/{toplantiId}/{personelId}")
    public ResponseEntity<TransactionResult<?>> deletePersonnel(@PathVariable("toplantiId") Long toplantiId,@PathVariable("personelId") Long personelId) {
        Boolean operationComplete  =  this.meetingService.deletePersonnelMeeting(toplantiId,personelId);//toplantidan personel silme işlemini yapar  işlem başarısını boolean olarak döner.
        if(operationComplete) {
            List<MeetingViewModel> meetingViewModelList = this.meetingService.meetingList();//Personel silme işlemi başarılı ise oluşan yeni toplanti listesini getir.
            return new ResponseEntity<>(new TransactionResult<>(meetingViewModelList), HttpStatus.CREATED);//Silme işlemi başarılı ise istemciye yeni toplanti listesini gönder.
        }else {
            CustomException customException = new CustomException("Toplantidan Personel Silme Hatası","Göderilen id hatalı olabir veya böyle bir toplanti yada personel kayıtlarımızda bulunmamış olabilir.",6562,200);
            return new ResponseEntity<>(new TransactionResult<>(customException), HttpStatus.BAD_REQUEST);//toplanti yoksa veya null dönmüşse customExceptionu istemciye gönder.
        }
    }

}
