package com.mangotech.edu.service.customer.param;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDetailParam {
    /*id cơ sở*/
    private Long facilityId;
    /*id marketing*/
    private Long markPersionId;
    /*Trạng thái: Đã đến,...*/
    private Short actionStatusId;
    /*id cơ sở*/
    private String stayReturn;
    /*Thời gian hẹn*/
    private Long appointmentDateStart;
    private Long appointmentDateEnd;
    /*id khóa học*/
    private Long courseId;
    /*Tiền cọc*/
    private Double deposit;
    /*ID người nhận tiền cọc*/
    private Long depositHolderId;
    /*tiền đã đóng học*/
    private Double tuition;
    /*id người thi tiền*/
    private Long tuitionHolderId;
    /*hẹn hp*/
    private Long appointmentDepositStart;
    private Long appointmentDepositEnd;
    /*Trạng thái học viên : HV mới, HV cũ,...*/
    private Short customerStatusId;
    /*Ngày gọi*/
    private Long callDateStart;
    private Long callDateEnd;
    /*Tên khách hàng*/
    private String customerName;
    /*Số điện thoại của khách hàng*/
    private String customerPhone;
    /*FB*/
    private String customerFaceBook;
    /*Giới tính*/
    private Short customerGenderId;
    /*Ghi chú*/
    private String customerNote;
    /*Email*/
    private String customerEmail;
}
