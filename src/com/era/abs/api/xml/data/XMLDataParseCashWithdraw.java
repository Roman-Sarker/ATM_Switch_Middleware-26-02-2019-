/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.abs.api.xml.data;
 
import com.era.abs.api.model.ResponseDataCashWithdraw;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author roman
 */
public class XMLDataParseCashWithdraw {

    public ResponseDataCashWithdraw getResponseDataFromXML(String xml) {
        SAXBuilder builder = new SAXBuilder();
        ResponseDataCashWithdraw responseData = new ResponseDataCashWithdraw();

        try {
            InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            Document document = builder.build(stream);
            Element node = document.getRootElement(); 
            
            String responseCODE = node.getChildText("RESPONSE_CODE");
            responseData.setResponseCode(Long.parseLong((responseCODE)));
            
            String responseMSG = node.getChildText("RESPONSE_MSG");
            responseData.setResponseMessage(responseMSG);
            
             String refNo = node.getChild("REF_NO").getText();
            responseData.setRef_no(refNo);
             
            String reqNO = node.getChild("REQ_NO").getText();
            responseData.setReqNo(reqNO);
        }  catch (NullPointerException io) {
          //  System.out.println(io.getMessage());
        }catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
        return responseData;
    }

    public static void main(String[] args) {
//         XMLDataParseBI xmlDataParse = new XMLDataParseBI();
//         xmlDataParse.getResponseDataFromXML("<BALANCE_INQUIRY><REQ_NO>71</REQ_NO><OPERATING_BALANCE>14715.17</OPERATING_BALANCE><RESPONSE_CODE>000</RESPONSE_CODE><RESPONSE_MSG>Successfully Delivered.</RESPONSE_MSG></BALANCE_INQUIRY>");
    }

}
