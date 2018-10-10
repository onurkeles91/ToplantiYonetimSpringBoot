package com.keles.toplanti.Repository;

import com.keles.toplanti.EntityModel.MeetingEntityModel;
import com.keles.toplanti.EntityModel.PersonelEntityModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Toplanti repository sınıfını temsil eder.
 *
 * @author Onur KELES
 * @since 9.10.2018
 */

public interface MeetingRepository extends BaseRepository<MeetingEntityModel> {
//    @Query("select m from MeetingEntityModel m where m.softDelete = false and m.personelEntityModelList =")
//List<MeetingEntityModel> kelesDeneme(MeetingEntityModel meetingEntityModel);
//   List<PersonelEntityModel> findByMeetingAndSoftDeleteIsFalse(MeetingEntityModel meetingEntityModel);
}
