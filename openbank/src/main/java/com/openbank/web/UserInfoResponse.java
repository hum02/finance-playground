package com.openbank.web;

import java.util.List;

public record UserInfoResponse(
        String api_tran_id,
        String api_tran_dtm,
        String rsp_code,
        String rsp_message,
        String user_seq_no,
        String user_ci,
        String user_name,
        String res_cnt,
        List<AccountResponse> res_list,
        String inquiry_card_cnt,
        List<Object> inquiry_card_list,
        String inquiry_pay_cnt,
        List<Object> inquiry_pay_list,
        String inquiry_insurance_cnt,
        List<Object> inquiry_insurance_list,
        String inquiry_loan_cnt,
        List<Object> inquiry_loan_list
) {

    @Override
    public String toString() {
        return "UserInfoResponse{" +
                "api_tran_id='" + api_tran_id + '\'' +
                ", api_tran_dtm='" + api_tran_dtm + '\'' +
                ", rsp_code='" + rsp_code + '\'' +
                ", rsp_message='" + rsp_message + '\'' +
                ", user_seq_no='" + user_seq_no + '\'' +
                ", user_ci='" + user_ci + '\'' +
                ", user_name='" + user_name + '\'' +
                ", res_cnt='" + res_cnt + '\'' +
                ", res_list=" + res_list +
                ", inquiry_card_cnt='" + inquiry_card_cnt + '\'' +
                ", inquiry_card_list=" + inquiry_card_list +
                ", inquiry_pay_cnt='" + inquiry_pay_cnt + '\'' +
                ", inquiry_pay_list=" + inquiry_pay_list +
                ", inquiry_insurance_cnt='" + inquiry_insurance_cnt + '\'' +
                ", inquiry_insurance_list=" + inquiry_insurance_list +
                ", inquiry_loan_cnt='" + inquiry_loan_cnt + '\'' +
                ", inquiry_loan_list=" + inquiry_loan_list +
                '}';
    }
}
