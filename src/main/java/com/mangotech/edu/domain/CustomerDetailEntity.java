package com.mangotech.edu.domain;

import com.mangotech.edu.constants.StayReturnConstant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "edu_customer_details")
@Getter
@Setter
/* Thông tin chi tiết của học viên ứng với mỗi khóa học */
public class CustomerDetailEntity extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_id", referencedColumnName = "id")
    /* Cơ sở tương ứng với khóa học */
    private FacilityEntity facility;

    @Column(name = "call_date")
    /* Ngày mà nhân viên marketing gọi tương ứng với khóa học */
    private Long callDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marketing_id", referencedColumnName = "id")
    /* Nhân viên marketing tương ứng với khóa học*/
    private UserEntity markPerson;

    @Column(name = "stay_return_id")
    /* Trạng thái của học viên tương ứng với khóa học: Đi hay Ở (Mặc định là Ở) */
    private Short stayReturnId = StayReturnConstant.STAY;

    @Column(name = "appointment_date")
    /* Ngày hẹn gặp mặt học viên tương ứng với khóa học */
    private Long appointmentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    /* Khóa học tương ứng */
    private CourseEntity course;

    @Column(name = "deposit")
    /* Tiền cọc học viên đã đóng tương ứng với khóa học */
    private Double deposit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposit_holder_id", referencedColumnName = "id")
    /* Người giữ tiền cọc cho khóa học tương ứng */
    private UserEntity depositHolder;

    @Column(name = "tuition_1")
    /* Học phí mà học viên đã đóng trong đợt 1 của khóa học tương ứng */
    private Double tuition1;

    @Column(name = "tuition_2")
    /* Học phí mà học viên đã đóng trong đợt 2 của khóa học tương ứng */
    private Double tuition2;

    @Column(name = "tuition_3")
    /* Học phí mà học viên đã đóng trong đợt 3 của khóa học tương ứng */
    private Double tuition3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tuition_holder_id_1", referencedColumnName = "id")
    /* Người giữ tiền học phí đợt 1 của học viên cho khóa học tương ứng */
    private UserEntity tuitionHolder1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tuition_holder_id_2", referencedColumnName = "id")
    /* Người giữ tiền học phí đợt 2 của học viên cho khóa học tương ứng */
    private UserEntity tuitionHolder2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tuition_holder_id_3", referencedColumnName = "id")
    /* Người giữ tiền học phí đợt 3 của học viên cho khóa học tương ứng */
    private UserEntity tuitionHolder3;

    @Column(name = "appointment_deposit")
    /* Ngày hẹn đóng cọc tương ứng của khóa học */
    private Long appointmentDeposit;

    @Column(name = "action_status_id")
    /* Trạng thái của học viên với thời gian hẹn gặp mặt:
    Đã đến, Chưa đến, Đã cọc
     */
    private Short actionStatusId;

    @Column(name = "customer_status_id")
    /* Trang thái học tập của học viên ứng với khóa học tương ứng:
    Học viên mới, Học viên cũ, Combo, Bảo lưu
    */
    private Short customerStatusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    /* Các thông tin có tính chất duy nhất, không thay đổi của học viên */
    private CustomerEntity customer;
}
