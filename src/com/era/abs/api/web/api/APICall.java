package com.era.abs.api.web.api;

import com.era.abs.api.model.ResponseDataBI;
import com.era.abs.api.model.ResponseDataCashWithdraw;
import com.era.abs.api.xml.data.XMLDataBuildBalanceInquiry;
import com.era.abs.api.xml.data.XMLDataBuildCashWithdraw;
import com.era.abs.api.xml.data.XMLDataParseBI;
import com.era.abs.api.xml.data.XMLDataParseCashWithdraw;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.RequestDataBI;

public class APICall {

    public String mankeXMLDataOneLine(String xmlData) {
        Reader inputString = new StringReader(xmlData);
        BufferedReader reader = new BufferedReader(inputString);

        String line;
        StringBuilder sb = new StringBuilder();

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line.trim());
            }
        } catch (IOException ex) {
            Logger.getLogger(APICall.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }

    private String removeLastChar(String str) {
        return str.substring(0, 10);//str.length() - 6);
    }

    public ResponseDataBI balanceInquiryAPICall(String URL, long accountNo) {
        XMLDataBuildBalanceInquiry xmlDataBuildForBalanceInquiry = new XMLDataBuildBalanceInquiry();
        String xmlRequestData = mankeXMLDataOneLine(xmlDataBuildForBalanceInquiry.getXMLDataForBalanceInquiry(accountNo));
        ABS_API_Transaction abs_atm_transaction = new ABS_API_Transaction();
        String xmlResponseData = abs_atm_transaction.generalAPICall(URL, xmlRequestData);
        XMLDataParseBI xmlDataParse = new XMLDataParseBI();
        return xmlDataParse.getResponseDataFromXML(xmlResponseData);
    }

    public ResponseDataCashWithdraw cashWithdrawlAPICall(String URL,long amount,String accountNo) {
        XMLDataBuildCashWithdraw xmlDataBuildCashWithdraw = new XMLDataBuildCashWithdraw();
        String xmlData = mankeXMLDataOneLine(xmlDataBuildCashWithdraw.getXMLData(amount,accountNo));
         ABS_API_Transaction abs_atm_transaction = new ABS_API_Transaction();
        String xmlResponseData = abs_atm_transaction.generalAPICall(URL, xmlData);
        XMLDataParseCashWithdraw xmlDataParseCashWithdraw= new XMLDataParseCashWithdraw(); 
        return xmlDataParseCashWithdraw.getResponseDataFromXML(xmlResponseData);
    }

    public ResponseDataBI transactionReversalAPICall(RequestDataBI requestData) {
        return null;
    }

    public static void main(String[] args) {
        APICall apiCall = new APICall();
        String URL = "http://10.11.201.170:8089/webservice/emob/nrb/agentbanking/transaction";
        ResponseDataCashWithdraw responseDataCashWithdraw = apiCall.cashWithdrawlAPICall(URL,200, "9982990000001");
        if(responseDataCashWithdraw != null){
            System.out.println("response code: "+responseDataCashWithdraw.getResponseCode());
            System.out.println("response message: "+responseDataCashWithdraw.getResponseMessage());
        }else{
        
        }
        
        
    }
}
