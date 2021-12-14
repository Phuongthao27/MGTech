package com.mangotech.edu.domain;

import java.util.List;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "edu_customer")
@Getter
@Setter
@NoArgsConstructor
public class CustomerEntity extends BaseEntity<Long> {

    @Column(name = "customer_code")
    /* Mã học viên */
    private String customerCode;

    @ManyToMany
    @JoinTable(
        name = "edu_customer_details",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "facility_id")
    )
    /* Danh sách từng cơ sở ứng với mỗi khóa học*/
    private List<FacilityEntity> facilities;

    @ManyToMany
    @JoinTable(
        name = "edu_customer_details",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "marketing_id")
    )
    /* Danh sách từng nhân viên marketing ứng với mỗi khóa học*/
    private List<UserEntity> markPeople;

    @Column(name = "name", length = 150)
    /* Tên học viên */
    private String name;

    @Column(name = "phone", length = 21)
    /* Số điện thoại của học viên */
    private String phone;

    @Column(name = "face_book", length = 150)
    /* Nick facebook của học viên */
    private String faceBook;

    @Column(name = "email", length = 150)
    /* Địa chỉ email của học viên */
    private String email;

    @Column(name = "gender_id")
    /* Giới tính của học viên */
    private Short genderId;

    @ManyToMany
    @JoinTable(
        name = "edu_customer_details",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    /* Danh sách các khóa học của học viên */
    private List<CourseEntity> courses;

    @ManyToMany
    @JoinTable(
        name = "edu_customer_details",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "deposit_holder_id")
    )
    /* Danh sách từng nhân viên giữ tiền cọc ứng với mỗi khóa học của học viên */
    private List<UserEntity> depHolderPeople;

    @ManyToMany
    @JoinTable(
        name = "edu_customer_details",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "tuition_holder_id_1")
    )
    /* Danh sách từng nhân viên giữ tiền học phí đợt 1 ứng với mỗi khóa học của học viên */
    private List<UserEntity> tuitionHolderPeople1;

    @ManyToMany
    @JoinTable(
        name = "edu_customer_details",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "tuition_holder_id_2")
    )
    /* Danh sách từng nhân viên giữ tiền học phí đợt 2 ứng với mỗi khóa học của học viên */
    private List<UserEntity> tuitionHolderPeople2;

    @ManyToMany
    @JoinTable(
        name = "edu_customer_details",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "tuition_holder_id_3")
    )
    /* Danh sách từng nhân viên giữ tiền học phí đợt 3 ứng với mỗi khóa học của học viên */
    private List<UserEntity> tuitionHolderPeople3;

    @Column(name = "note", length = 1505)
    /* Ghi chú về học viên */
    private String note;

    @OneToMany(mappedBy = "customer")
    /* Danh sách các thông tin chi tiết của học viên ứng với mỗi khóa học */
    private List<CustomerDetailEntity> customerDetails;
}
