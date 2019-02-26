/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.abs.api.web.api;

import com.era.json.JSONParserPost;
import org.json.JSONException;
import org.json.JSONObject; 

/**
 *
 * @author roman
 */
public class ABS_API_Transaction {
    
    public String generalAPICall(String URL , String request){ 
        String responeXML = null;
        
        try{
            JSONParserPost jsonParserpost = new JSONParserPost();
            String output = jsonParserpost.makeHttpRequest(URL, "POST", request); 
             
            JSONObject jsonObject =new JSONObject(output);
            if(jsonObject != null)  
                responeXML = (String)jsonObject.get("response");  
            else
                System.out.println("jsonObject is null");
        }
        catch(JSONException ex)
        {
            ex.printStackTrace();
        }
        return responeXML;
    }
    
    public static void main(String[] args) {
        ABS_API_Transaction abs_API_Transaction = new ABS_API_Transaction();
         String URL = "http://10.11.201.170:8089/webservice/emob/nrb/agentbanking/transaction" ; 
        String request= "<TRANSACTION><HEADER><USER_ID>NRB</USER_ID><USER_PASS>NRB$ERA#WBS@</USER_PASS><TOKEN_NO>1</TOKEN_NO><REQ_NO>71</REQ_NO><REFERENCE1>71</REFERENCE1><REFERENCE2>71</REFERENCE2><REFERENCE3>71</REFERENCE3></HEADER><BODY><DEBIT><BRANCH>998</BRANCH><AC_TYPE>CA</AC_TYPE><AC_NO>9982990000001</AC_NO><AMOUNT>200</AMOUNT><NARRATION>atm requested for cash withdraw</NARRATION></DEBIT><CREDIT><BRANCH>998</BRANCH><AC_TYPE>BA</AC_TYPE><AC_NO>406000000</AC_NO><AMOUNT>200</AMOUNT><NARRATION>credited to atm account of bank</NARRATION></CREDIT></BODY></TRANSACTION>" ; 
        abs_API_Transaction.generalAPICall(URL, request);
    }
    
}
