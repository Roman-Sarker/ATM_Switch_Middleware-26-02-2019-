/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.abs.api.model;

/**
 *
 * @author sultan
 */
public class ResponseDataCashWithdraw {
    
    String reqNo,responseMessage,ref_no;
    long responseCode; 

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    public String getRef_no() {
        return ref_no;
    }

    public void setRef_no(String ref_no) {
        this.ref_no = ref_no;
    }
     
    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public long getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(long responseCode) {
        this.responseCode = responseCode;
    }  

    
}
