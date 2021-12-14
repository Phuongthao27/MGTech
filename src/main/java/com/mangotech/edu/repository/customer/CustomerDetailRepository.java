package com.mangotech.edu.repository.customer;

import com.mangotech.edu.constants.StatusContants;
import com.mangotech.edu.domain.CustomerDetailEntity;
import com.mangotech.edu.service.customer.param.CustomerDetailParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDetailRepository extends JpaRepository<CustomerDetailEntity, Long> {

    @Query(value = "select cd from CustomerDetailEntity cd WHERE cd.removed = false " +
        " AND ( :#{#param.facilityId} IS NULL OR cd.facility.id = :#{#param.facilityId} ) " +
        " AND ( :#{#param.callDateStart} IS NULL OR cd.callDate >= :#{#param.callDateStart}  )" +
        " AND ( :#{#param.callDateEnd} IS NULL OR cd.callDate <= :#{#param.callDateEnd}  )" +
        " AND ( :#{#param.markPersionId} IS NULL OR cd.markPerson.id = :#{#param.markPersionId} )" +
        " AND ( :#{#param.customerName} IS NULL OR cd.customer.name LIKE %:#{#param.customerName}% )" +
        " AND ( :#{#param.customerPhone} IS NULL OR cd.customer.phone LIKE %:#{#param.customerPhone}% )" +
        " AND ( :#{#param.customerFaceBook} IS NULL OR cd.customer.faceBook LIKE %:#{#param.customerFaceBook}% )" +
        " AND ( :#{#param.customerEmail} IS NULL OR cd.customer.email LIKE %:#{#param.customerEmail}% )" +
        " AND ( :#{#param.customerGenderId} IS NULL OR cd.customer.genderId = :#{#param.customerGenderId} )" +
        " AND ( :#{#param.appointmentDateStart} IS NULL OR cd.appointmentDate >= :#{#param.appointmentDateStart} )" +
        " AND ( :#{#param.appointmentDateEnd} IS NULL OR cd.appointmentDate <= :#{#param.appointmentDateEnd} )" +
        " AND ( :#{#param.courseId} IS NULL OR cd.course.id <= :#{#param.courseId} )" +
        " AND ( :#{#param.deposit} IS NULL OR cd.deposit = :#{#param.deposit} )" +
        " AND ( :#{#param.depositHolderId} IS NULL OR cd.depositHolder.id = :#{#param.depositHolderId} )" +
        " AND ( :#{#param.tuition} IS NULL OR cd.tuition1 + cd.tuition2 + cd.tuition3  >= :#{#param.tuition} )" +
        " AND ( :#{#param.tuitionHolderId} IS NULL OR cd.tuitionHolder1.id = :#{#param.tuitionHolderId} " +
        " OR cd.tuitionHolder2.id = :#{#param.tuitionHolderId} " +
        " OR cd.tuitionHolder3.id = :#{#param.tuitionHolderId} )" +
        " AND ( :#{#param.customerNote} IS NULL OR cd.customer.note like %:#{#param.customerNote}% )" +
        " AND ( :#{#param.appointmentDepositStart} IS NULL OR cd.appointmentDeposit >= :#{#param.appointmentDepositStart} )" +
        " AND ( :#{#param.appointmentDepositEnd} IS NULL OR cd.appointmentDeposit <= :#{#param.appointmentDepositEnd} )" +
        " AND ( :#{#param.actionStatusId} IS NULL OR cd.actionStatusId = :#{#param.actionStatusId} )" +
        " AND ( :#{#param.customerStatusId} IS NULL OR cd.customerStatusId = :#{#param.customerStatusId} )"
    )
    Page<CustomerDetailEntity> searchAll(@Param(value = "param") CustomerDetailParam param, Pageable pageable);

    @Query(value = "select cd from CustomerDetailEntity cd WHERE cd.removed = false " +
        " AND ( cd.facility.id IS NULL OR cd.appointmentDate IS NULL)" +
        " AND ( :#{#param.facilityId} IS NULL OR cd.facility.id = :#{#param.facilityId} ) " +
        " AND ( :#{#param.appointmentDateStart} IS NULL OR cd.appointmentDate >= :#{#param.appointmentDateStart} )" +
        " AND ( :#{#param.appointmentDateEnd} IS NULL OR cd.appointmentDate <= :#{#param.appointmentDateEnd} )" +
        " AND ( :#{#param.callDateStart} IS NULL OR cd.callDate >= :#{#param.callDateStart}  )" +
        " AND ( :#{#param.callDateEnd} IS NULL OR cd.callDate <= :#{#param.callDateEnd}  )" +
        " AND ( :#{#param.markPersionId} IS NULL OR cd.markPerson.id = :#{#param.markPersionId} )" +
        " AND ( :#{#param.customerName} IS NULL OR cd.customer.name LIKE %:#{#param.customerName}% )" +
        " AND ( :#{#param.customerPhone} IS NULL OR cd.customer.phone LIKE %:#{#param.customerPhone}% )" +
        " AND ( :#{#param.customerFaceBook} IS NULL OR cd.customer.faceBook LIKE %:#{#param.customerFaceBook}% )" +
        " AND ( :#{#param.customerEmail} IS NULL OR cd.customer.email LIKE %:#{#param.customerEmail}% )" +
        " AND ( :#{#param.customerGenderId} IS NULL OR cd.customer.genderId = :#{#param.customerGenderId} )" +
        " AND ( :#{#param.courseId} IS NULL OR cd.course.id <= :#{#param.courseId} )" +
        " AND ( :#{#param.deposit} IS NULL OR cd.deposit = :#{#param.deposit} )" +
        " AND ( :#{#param.depositHolderId} IS NULL OR cd.depositHolder.id = :#{#param.depositHolderId} )" +
        " AND ( :#{#param.tuition} IS NULL OR cd.tuition1 + cd.tuition2 + cd.tuition3  >= :#{#param.tuition} )" +
        " AND ( :#{#param.tuitionHolderId} IS NULL OR cd.tuitionHolder1.id = :#{#param.tuitionHolderId} " +
        " OR cd.tuitionHolder2.id = :#{#param.tuitionHolderId} " +
        " OR cd.tuitionHolder3.id = :#{#param.tuitionHolderId} )" +
        " AND ( :#{#param.customerNote} IS NULL OR cd.customer.note like %:#{#param.customerNote}% )" +
        " AND ( :#{#param.appointmentDepositStart} IS NULL OR cd.appointmentDeposit >= :#{#param.appointmentDepositStart} )" +
        " AND ( :#{#param.appointmentDepositEnd} IS NULL OR cd.appointmentDeposit <= :#{#param.appointmentDepositEnd} )" +
        " AND ( :#{#param.actionStatusId} IS NULL OR cd.actionStatusId = :#{#param.actionStatusId} )" +
        " AND ( :#{#param.customerStatusId} IS NULL OR cd.customerStatusId = :#{#param.customerStatusId} )"
    )
    Page<CustomerDetailEntity> searchCustomer(@Param(value = "param") CustomerDetailParam param, Pageable pageable);

    @Query(value = "select cd from CustomerDetailEntity cd WHERE cd.removed = false " +
        " AND ( cd.facility.id IS NOT NULL AND cd.appointmentDate IS NOT NULL)" +
        " AND ( :#{#param.facilityId} IS NULL OR cd.facility.id = :#{#param.facilityId} ) " +
        " AND ( :#{#param.appointmentDateStart} IS NULL OR cd.appointmentDate >= :#{#param.appointmentDateStart} )" +
        " AND ( :#{#param.appointmentDateEnd} IS NULL OR cd.appointmentDate <= :#{#param.appointmentDateEnd} )" +
        " AND ( :#{#param.callDateStart} IS NULL OR cd.callDate >= :#{#param.callDateStart}  )" +
        " AND ( :#{#param.callDateEnd} IS NULL OR cd.callDate <= :#{#param.callDateEnd}  )" +
        " AND ( :#{#param.markPersionId} IS NULL OR cd.markPerson.id = :#{#param.markPersionId} )" +
        " AND ( :#{#param.customerName} IS NULL OR cd.customer.name LIKE %:#{#param.customerName}% )" +
        " AND ( :#{#param.customerPhone} IS NULL OR cd.customer.phone LIKE %:#{#param.customerPhone}% )" +
        " AND ( :#{#param.customerFaceBook} IS NULL OR cd.customer.faceBook LIKE %:#{#param.customerFaceBook}% )" +
        " AND ( :#{#param.customerEmail} IS NULL OR cd.customer.email LIKE %:#{#param.customerEmail}% )" +
        " AND ( :#{#param.customerGenderId} IS NULL OR cd.customer.genderId = :#{#param.customerGenderId} )" +
        " AND ( :#{#param.courseId} IS NULL OR cd.course.id <= :#{#param.courseId} )" +
        " AND ( :#{#param.deposit} IS NULL OR cd.deposit = :#{#param.deposit} )" +
        " AND ( :#{#param.depositHolderId} IS NULL OR cd.depositHolder.id = :#{#param.depositHolderId} )" +
        " AND ( :#{#param.tuition} IS NULL OR cd.tuition1 + cd.tuition2 + cd.tuition3  >= :#{#param.tuition} )" +
        " AND ( :#{#param.tuitionHolderId} IS NULL OR cd.tuitionHolder1.id = :#{#param.tuitionHolderId} " +
        " OR cd.tuitionHolder2.id = :#{#param.tuitionHolderId} " +
        " OR cd.tuitionHolder3.id = :#{#param.tuitionHolderId} )" +
        " AND ( :#{#param.customerNote} IS NULL OR cd.customer.note like %:#{#param.customerNote}% )" +
        " AND ( :#{#param.appointmentDepositStart} IS NULL OR cd.appointmentDeposit >= :#{#param.appointmentDepositStart} )" +
        " AND ( :#{#param.appointmentDepositEnd} IS NULL OR cd.appointmentDeposit <= :#{#param.appointmentDepositEnd} )" +
        " AND ( :#{#param.actionStatusId} IS NULL OR cd.actionStatusId = :#{#param.actionStatusId} )" +
        " AND ( :#{#param.customerStatusId} IS NULL OR cd.customerStatusId = :#{#param.customerStatusId} )"
    )
    Page<CustomerDetailEntity> searchAppointedCustomer(@Param(value = "param") CustomerDetailParam param, Pageable pageable);

    @Query(value = "select cd from CustomerDetailEntity cd WHERE cd.removed = false " +
        " AND ( cd.actionStatusId = " + StatusContants.TAKE_CARE_AFTER + " ) " +
        " AND ( :#{#param.facilityId} IS NULL OR cd.facility.id = :#{#param.facilityId} ) " +
        " AND ( :#{#param.appointmentDateStart} IS NULL OR cd.appointmentDate >= :#{#param.appointmentDateStart} )" +
        " AND ( :#{#param.appointmentDateEnd} IS NULL OR cd.appointmentDate <= :#{#param.appointmentDateEnd} )" +
        " AND ( :#{#param.callDateStart} IS NULL OR cd.callDate >= :#{#param.callDateStart}  )" +
        " AND ( :#{#param.callDateEnd} IS NULL OR cd.callDate <= :#{#param.callDateEnd}  )" +
        " AND ( :#{#param.markPersionId} IS NULL OR cd.markPerson.id = :#{#param.markPersionId} )" +
        " AND ( :#{#param.customerName} IS NULL OR cd.customer.name LIKE %:#{#param.customerName}% )" +
        " AND ( :#{#param.customerPhone} IS NULL OR cd.customer.phone LIKE %:#{#param.customerPhone}% )" +
        " AND ( :#{#param.customerFaceBook} IS NULL OR cd.customer.faceBook LIKE %:#{#param.customerFaceBook}% )" +
        " AND ( :#{#param.customerEmail} IS NULL OR cd.customer.email LIKE %:#{#param.customerEmail}% )" +
        " AND ( :#{#param.customerGenderId} IS NULL OR cd.customer.genderId = :#{#param.customerGenderId} )" +
        " AND ( :#{#param.courseId} IS NULL OR cd.course.id <= :#{#param.courseId} )" +
        " AND ( :#{#param.deposit} IS NULL OR cd.deposit = :#{#param.deposit} )" +
        " AND ( :#{#param.depositHolderId} IS NULL OR cd.depositHolder.id = :#{#param.depositHolderId} )" +
        " AND ( :#{#param.tuition} IS NULL OR cd.tuition1 + cd.tuition2 + cd.tuition3  >= :#{#param.tuition} )" +
        " AND ( :#{#param.tuitionHolderId} IS NULL OR cd.tuitionHolder1.id = :#{#param.tuitionHolderId} " +
        " OR cd.tuitionHolder2.id = :#{#param.tuitionHolderId} " +
        " OR cd.tuitionHolder3.id = :#{#param.tuitionHolderId} )" +
        " AND ( :#{#param.customerNote} IS NULL OR cd.customer.note like %:#{#param.customerNote}% )" +
        " AND ( :#{#param.appointmentDepositStart} IS NULL OR cd.appointmentDeposit >= :#{#param.appointmentDepositStart} )" +
        " AND ( :#{#param.appointmentDepositEnd} IS NULL OR cd.appointmentDeposit <= :#{#param.appointmentDepositEnd} )" +
        " AND ( :#{#param.customerStatusId} IS NULL OR cd.customerStatusId = :#{#param.customerStatusId} )"
    )
    Page<CustomerDetailEntity> searchTakeCareAfter(@Param(value = "param") CustomerDetailParam param, Pageable pageable);
}
