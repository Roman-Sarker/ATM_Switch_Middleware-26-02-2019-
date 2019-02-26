/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.Request.Cash.Withdraw;

import com.era.Request.Balance_Enquiry.BalanceEnquery; 
import com.era.abs.api.model.ResponseDataCashWithdraw;
import com.era.abs.api.web.api.APICall;
import com.jpos.iso8583.ISOField;
import com.jpos.iso8583.ISOMessageBuild;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException; 
import org.xml.sax.SAXException;

/**
 *
 * @author Administrator
 */
public class CashWithDraw {
    
    private InputStream stream ;
     
    public CashWithDraw(InputStream stream){
        this.stream=stream;
    }
    
    private long getResponseFromWebAPI(String amount,String accountNo){
        APICall apiCall = new APICall();
        String URL = "http://10.11.201.170:8089/webservice/emob/nrb/agentbanking/transaction" ; 
        ResponseDataCashWithdraw responseData = apiCall.cashWithdrawlAPICall(URL,getAmount(amount),accountNo);
        return responseData.getResponseCode();
    }
    
    public ISOField getISOField(int index,String value){
        ISOField isoField = new ISOField();
        isoField.setIndex(index);
        isoField.setValue(value); 
        return isoField;
    } 
    
    private long getAmount(String amountStr){
        long amount = Long.parseLong(amountStr);
        return 250;// amount/1000;
    }
    
    private List<ISOField> getListAndProcess(List<ISOField> list){
        
        String accountNo = "",amount="" ; 
        for(ISOField iso:list){
            if(iso.getIndex()==102)
                accountNo = iso.getValue();
            else if(iso.getIndex()==4)
                amount = iso.getValue();
        }
        
        System.out.print("Account no is "+accountNo);
        System.out.print("withdraw amount is "+getAmount(amount));
        
        long responseCode =  getResponseFromWebAPI(amount,accountNo); 
        if(responseCode == 0 ) 
            JOptionPane.showMessageDialog(null, "transaction is successfully completed");
        else if(responseCode == 105)
            JOptionPane.showMessageDialog(null, "Sorry! Transaction is failed");
        else
            JOptionPane.showMessageDialog(null, "response code is "+responseCode);
            
        ISOField isoField = getISOField(38,"00001");
        list.add(isoField);
        isoField = getISOField(39,"00001");
        list.add(isoField);  
        return list;
    }

    public String receiveMsgAndParse(List<ISOField> list) {
        try {
            List<ISOField> processISOField = getListAndProcess(list);  
            ISOMessageBuild isoMessageBuild = new ISOMessageBuild(stream);
            String[] saDaE = isoMessageBuild.convertListToSringArray(processISOField);
            String message2 = isoMessageBuild.Build(saDaE, "0210","A4M08000");
            return message2;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(BalanceEnquery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BalanceEnquery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(BalanceEnquery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    
    
}
