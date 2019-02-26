package com.era.abs.api.xml.data;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author roman
 */
public class XMLDataBuildCashWithdraw {

    public String getXMLData(long amount,String accountNo) {
        Element root = new Element("TRANSACTION");
        Document doc = new Document();

        Element header = new Element("HEADER");
        Element headerUserId = new Element("USER_ID");
        headerUserId.addContent("NRB");

        Element headerUserPass = new Element("USER_PASS");
        headerUserPass.addContent("NRB$ERA#WBS@");

        Element headerTokenNo = new Element("TOKEN_NO");
        headerTokenNo.addContent("1");

        Element headerReqNo = new Element("REQ_NO");
        headerReqNo.addContent("71");

        Element headerRef1 = new Element("REFERENCE1");
        headerRef1.addContent("71");

        Element headerRef2 = new Element("REFERENCE2");
        headerRef2.addContent("71");

        Element headerRef3 = new Element("REFERENCE3");
        headerRef3.addContent("71");

        //child1.addContent("NRB");
        Element body = new Element("BODY");
        Element bodyDebit = new Element("DEBIT");
        Element bodyDebitBranch = new Element("BRANCH");
        bodyDebitBranch.addContent("998");

        Element bodyDebitAcType = new Element("AC_TYPE");
        bodyDebitAcType.addContent("CA");

        Element bodyDebitAccNo = new Element("AC_NO");
        bodyDebitAccNo.addContent(accountNo);

        Element bodyDebitAmount = new Element("AMOUNT");
        bodyDebitAmount.addContent(String.valueOf(amount));

        Element bodyDebitNarration = new Element("NARRATION");
        bodyDebitNarration.addContent("atm requested for cash withdraw");
        
        
        
        Element bodyCredit = new Element("CREDIT");
        Element bodyCreditBranch = new Element("BRANCH");
        bodyCreditBranch.addContent("998");

        Element bodyCreditAcType = new Element("AC_TYPE");
        bodyCreditAcType.addContent("BA");

        Element bodyCreditAccNo = new Element("AC_NO");
        bodyCreditAccNo.addContent("406000000");

        Element bodyCreditAmount = new Element("AMOUNT");
        bodyCreditAmount.addContent(String.valueOf(amount));

        Element bodyCreditNarration = new Element("NARRATION");
        bodyCreditNarration.addContent("credited to atm account of bank");
        
        
        root.addContent(header);
        header.addContent(headerUserId);
        header.addContent(headerUserPass);
        header.addContent(headerTokenNo);
        header.addContent(headerReqNo);
        header.addContent(headerRef1);
        header.addContent(headerRef2);
        header.addContent(headerRef3);

        root.addContent(body);
        body.addContent(bodyDebit);
        body.addContent(bodyCredit);
        
        bodyDebit.addContent(bodyDebitBranch);
        bodyDebit.addContent(bodyDebitAcType);
        bodyDebit.addContent(bodyDebitAccNo);
        bodyDebit.addContent(bodyDebitAmount);
        bodyDebit.addContent(bodyDebitNarration);
        
        bodyCredit.addContent(bodyCreditBranch);
        bodyCredit.addContent(bodyCreditAcType);
        bodyCredit.addContent(bodyCreditAccNo);
        bodyCredit.addContent(bodyCreditAmount);
        bodyCredit.addContent(bodyCreditNarration);
 
        doc.setRootElement(root);

        XMLOutputter outter = new XMLOutputter();
        outter.setFormat(Format.getCompactFormat().setOmitDeclaration(true));
        String xml = outter.outputString(doc);
        return xml;
    }

    public static void main(String[] args) {
        XMLDataBuildCashWithdraw xmlDataBuild = new XMLDataBuildCashWithdraw();
        System.out.println(xmlDataBuild.getXMLData(200 , "9982990000001"));
    }
}
