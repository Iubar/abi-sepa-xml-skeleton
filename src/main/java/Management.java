import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Management
{

    private void builtId(Document doc, Element elem, String Id, String Issr) throws ParserConfigurationException, TransformerException
    {
        // Id elements
        Element elemIdMain = doc.createElement("Id");
        elem.appendChild(elemIdMain);

        // OrgId elements
        Element elemOrgId = doc.createElement("OrgId");
        elemIdMain.appendChild(elemOrgId);

        // Othr elements
        Element elemOthr = doc.createElement("Othr");
        elemOrgId.appendChild(elemOthr);

        // Id elements
        Element elemId = doc.createElement("Id");
        elemId.appendChild(doc.createTextNode(Id));
        elemOthr.appendChild(elemId);

        // Issr elements
        Element elemIssr = doc.createElement("Issr");
        elemIssr.appendChild(doc.createTextNode(Issr));
        elemOthr.appendChild(elemIssr);
    }

    private void builtInitgPty(Document doc, Element elemGrpHdr) throws ParserConfigurationException, TransformerException
    {
        // InitgPty elements
        Element elemInitgPty = doc.createElement("InitgPty");
        elemGrpHdr.appendChild(elemInitgPty);

        // Nm elements
        Element elemNm = doc.createElement("Nm");
        elemNm.appendChild(doc.createTextNode("AB azienda di test SPA"));
        elemInitgPty.appendChild(elemNm);

        builtId(doc, elemInitgPty, "BRTFBA69T06H598H", "ADE");

    }

    private void builtGrpHdr(Document doc, Element elemCBIPaymentRequest) throws ParserConfigurationException, TransformerException
    {
        // GrpHdr elements
        Element elemGrpHdr = doc.createElement("GrpHdr");
        elemCBIPaymentRequest.appendChild(elemGrpHdr);

        // MsgId elements
        Element elemMsgId = doc.createElement("MsgId");
        elemMsgId.appendChild(doc.createTextNode("DistintaXml-200213-16.51"));
        elemGrpHdr.appendChild(elemMsgId);

        // CreDtTm elements
        Element elemCreDtTm = doc.createElement("CreDtTm");
        elemCreDtTm.appendChild(doc.createTextNode("2013-02-20T16:51:30+01:00"));
        elemGrpHdr.appendChild(elemCreDtTm);

        // NbOfTxs elements
        Element elemNbOfTxs = doc.createElement("NbOfTxs");
        elemNbOfTxs.appendChild(doc.createTextNode("3"));
        elemGrpHdr.appendChild(elemNbOfTxs);

        // CtrlSum elements
        Element elemCtrlSum = doc.createElement("CtrlSum");
        elemCtrlSum.appendChild(doc.createTextNode("524"));
        elemGrpHdr.appendChild(elemCtrlSum);

        builtInitgPty(doc, elemGrpHdr);

    }

    private void builtPmtTpInf(Document doc, Element elemPmtInf) throws ParserConfigurationException, TransformerException
    {
        // PmtTpInf elements
        Element elemPmtTpInf = doc.createElement("PmtTpInf");
        elemPmtInf.appendChild(elemPmtTpInf);

        // InstrPrty elements
        Element elemInstrPrty = doc.createElement("InstrPrty");
        elemInstrPrty.appendChild(doc.createTextNode("TRF"));
        elemPmtTpInf.appendChild(elemInstrPrty);

        // SvcLvl elements
        Element elemSvcLvl = doc.createElement("SvcLvl");
        elemPmtInf.appendChild(elemSvcLvl);

        // Cd elements
        Element elemCd = doc.createElement("Cd");
        elemCd.appendChild(doc.createTextNode("SEPA"));
        elemSvcLvl.appendChild(elemCd);
    }

    private void builtDbtr(Document doc, Element elemPmtInf) throws ParserConfigurationException, TransformerException
    {
        // Dbtr elements
        Element elemDbtr = doc.createElement("PmtInf");
        elemPmtInf.appendChild(elemDbtr);

        // Nm elements
        Element elemNm = doc.createElement("Nm");
        elemNm.appendChild(doc.createTextNode("AB azienda di test SPA"));
        elemPmtInf.appendChild(elemNm);

        builtId(doc, elemPmtInf, "BRTFBA69T06H598H", "ADE");

    }

    private void builtDbtrAcct(Document doc, Element elemPmtInf) throws ParserConfigurationException, TransformerException
    {
        // DbtrAcct elements
        Element elemDbtrAcct = doc.createElement("DbtrAcct");
        elemPmtInf.appendChild(elemDbtrAcct);

        // Id elements
        Element elemId = doc.createElement("Id");
        elemDbtrAcct.appendChild(elemId);

        // IBAN elements
        Element elemIBAN = doc.createElement("IBAN");
        elemIBAN.appendChild(doc.createTextNode("IT36T0350011200000000077745"));
        elemId.appendChild(elemIBAN);
    }


    private void builtDbtrAgt(Document doc, Element elemPmtInf) throws ParserConfigurationException, TransformerException
    {
        // DbtrAgt elements
        Element elemDbtrAgt = doc.createElement("DbtrAgt");
        elemPmtInf.appendChild(elemDbtrAgt);

        // FinInstnId elements
        Element elemFinInstnId = doc.createElement("FinInstnId");
        elemDbtrAgt.appendChild(elemFinInstnId);

        // ClrSysMmbId elements
        Element elemClrSysMmbId = doc.createElement("ClrSysMmbId");
        elemFinInstnId.appendChild(elemClrSysMmbId);

        // MmbId elements
        Element elemMmbId = doc.createElement("MmbId");
        elemMmbId.appendChild(doc.createTextNode("03500"));
        elemClrSysMmbId.appendChild(elemMmbId);
    }

    private void builtPmtInf(Document doc, Element elemCBIPaymentRequest) throws ParserConfigurationException, TransformerException
    {
        // PmtInf elements
        Element elemPmtInf = doc.createElement("PmtInf");
        elemCBIPaymentRequest.appendChild(elemPmtInf);

        // PmtInfId elements
        Element elemPmtInfId = doc.createElement("PmtInfId");
        elemPmtInfId.appendChild(doc.createTextNode("DistintaXml-200213-16.51"));
        elemPmtInf.appendChild(elemPmtInfId);

        // PmtMtd elements
        Element elemPmtMtd = doc.createElement("PmtMtd");
        elemPmtMtd.appendChild(doc.createTextNode("TRF"));
        elemPmtInf.appendChild(elemPmtMtd);

        builtPmtTpInf(doc, elemPmtInf);

        // ReqdExctnDt elements
        Element elemReqdExctnDt = doc.createElement("ReqdExctnDt");
        elemReqdExctnDt.appendChild(doc.createTextNode("2013-03-20"));
        elemPmtInf.appendChild(elemReqdExctnDt);

        builtDbtr(doc, elemPmtInf);

        builtDbtrAcct(doc, elemPmtInf);

        builtDbtrAgt(doc, elemPmtInf);

        // ChrgBr elements
        Element elemChrgBr = doc.createElement("ChrgBr");
        elemChrgBr.appendChild(doc.createTextNode("SLEV"));
        elemPmtInf.appendChild(elemChrgBr);
    }


    private void directionCdtTrfTxInf(Document doc, Element elemCBIPaymentRequest) throws ParserConfigurationException, TransformerException
    {
        builtCdtTrfTxInf1(doc, elemCBIPaymentRequest);

        builtCdtTrfTxInf2(doc, elemCBIPaymentRequest);

        builtCdtTrfTxInf3(doc, elemCBIPaymentRequest);
    }

    private void builtCdtTrfTxInf1(Document doc, Element elemCBIPaymentRequest) throws ParserConfigurationException, TransformerException
    {
        // CdtTrfTxInf elements
        Element elemCdtTrfTxInf = doc.createElement("CdtTrfTxInf");
        elemCBIPaymentRequest.appendChild(elemCdtTrfTxInf);

        // PmtId elements
        Element elemPmtId = doc.createElement("PmtId");
        elemCdtTrfTxInf.appendChild(elemPmtId);

        // InstrId elements
        Element elemInstrId = doc.createElement("InstrId");
        elemInstrId.appendChild(doc.createTextNode("1"));
        elemPmtId.appendChild(elemInstrId);

        // EndToEndId elements
        Element elemEndToEndId = doc.createElement("EndToEndId");
        elemEndToEndId.appendChild(doc.createTextNode("DistintaXml-200213-16.51-0001"));
        elemPmtId.appendChild(elemEndToEndId);

        // PmtTpInf elements
        Element elemPmtTpInf = doc.createElement("PmtTpInf");
        elemCdtTrfTxInf.appendChild(elemPmtTpInf);

        // CtgyPurp elements
        Element elemCtgyPurp = doc.createElement("CtgyPurp");
        elemPmtTpInf.appendChild(elemCtgyPurp);

        // Cd elements
        Element elemCd = doc.createElement("Cd");
        elemCd.appendChild(doc.createTextNode("SUPP"));
        elemCtgyPurp.appendChild(elemCd);

        // Amt elements
        Element elemAmt = doc.createElement("Amt");
        elemCdtTrfTxInf.appendChild(elemAmt);

        // InstdAmt elements
        Element elemInstdAmt = doc.createElement("InstdAmt");
        elemInstdAmt.appendChild(doc.createTextNode("10"));
        elemAmt.appendChild(elemInstdAmt);

        // Cdtr elements
        Element elemCdtr = doc.createElement("Cdtr");
        elemCdtTrfTxInf.appendChild(elemCdtr);

        // Nm elements
        Element elemNm = doc.createElement("Nm");
        elemNm.appendChild(doc.createTextNode("BLU SKY SRL"));
        elemCdtr.appendChild(elemNm);

        // CdtrAcct elements
        Element elemCdtrAcct = doc.createElement("CdtrAcct");
        elemCdtTrfTxInf.appendChild(elemCdtrAcct);

        // Id elements
        Element elemId = doc.createElement("Id");
        elemCdtrAcct.appendChild(elemId);

        // IBAN elements
        Element elemIBAN = doc.createElement("IBAN");
        elemIBAN.appendChild(doc.createTextNode("IT90X0350054590000000010133"));
        elemId.appendChild(elemIBAN);

        // RmtInf elements
        Element elemRmtInf = doc.createElement("RmtInf");
        elemCdtTrfTxInf.appendChild(elemRmtInf);

        // Ustrd elements
        Element elemUstrd = doc.createElement("Ustrd");
        elemUstrd.appendChild(doc.createTextNode("PAGAMENTO FATTURA 12"));
        elemRmtInf.appendChild(elemUstrd);
    }

    private void builtCdtTrfTxInf2(Document doc, Element elemCBIPaymentRequest) throws ParserConfigurationException, TransformerException
    {
        // CdtTrfTxInf elements
        Element elemCdtTrfTxInf = doc.createElement("CdtTrfTxInf");
        elemCBIPaymentRequest.appendChild(elemCdtTrfTxInf);

        // PmtId elements
        Element elemPmtId = doc.createElement("PmtId");
        elemCdtTrfTxInf.appendChild(elemPmtId);

        // InstrId elements
        Element elemInstrId = doc.createElement("InstrId");
        elemInstrId.appendChild(doc.createTextNode("2"));
        elemPmtId.appendChild(elemInstrId);

        // EndToEndId elements
        Element elemEndToEndId = doc.createElement("EndToEndId");
        elemEndToEndId.appendChild(doc.createTextNode("DistintaXml-200213-16.51-0002"));
        elemPmtId.appendChild(elemEndToEndId);

        // PmtTpInf elements
        Element elemPmtTpInf = doc.createElement("PmtTpInf");
        elemCdtTrfTxInf.appendChild(elemPmtTpInf);

        // CtgyPurp elements
        Element elemCtgyPurp = doc.createElement("CtgyPurp");
        elemPmtTpInf.appendChild(elemCtgyPurp);

        // Cd elements
        Element elemCd = doc.createElement("Cd");
        elemCd.appendChild(doc.createTextNode("SUPP"));
        elemCtgyPurp.appendChild(elemCd);

        // Amt elements
        Element elemAmt = doc.createElement("Amt");
        elemCdtTrfTxInf.appendChild(elemAmt);

        // InstdAmt elements
        Element elemInstdAmt = doc.createElement("InstdAmt");
        elemInstdAmt.appendChild(doc.createTextNode("500"));
        elemAmt.appendChild(elemInstdAmt);

        // CdtrAgt elements
        Element elemCdtrAgt = doc.createElement("CdtrAgt");
        elemCdtTrfTxInf.appendChild(elemCdtrAgt);

        // FinInstnId elements
        Element elemFinInstnId = doc.createElement("FinInstnId");
        elemCdtTrfTxInf.appendChild(elemFinInstnId);

        // BIC elements
        Element elemBIC = doc.createElement("BIC");
        elemBIC.appendChild(doc.createTextNode("NDEASESS"));
        elemFinInstnId.appendChild(elemBIC);

        // Cdtr elements
        Element elemCdtr = doc.createElement("Cdtr");
        elemCdtTrfTxInf.appendChild(elemCdtr);

        // Nm elements
        Element elemNm = doc.createElement("Nm");
        elemNm.appendChild(doc.createTextNode("500"));
        elemCdtr.appendChild(elemNm);

        // PstlAdr elements
        Element elemPstlAdr = doc.createElement("PstlAdr");
        elemCdtr.appendChild(elemPstlAdr);

        // AdrTp elements
        Element elemAdrTp = doc.createElement("AdrTp");
        elemAdrTp.appendChild(doc.createTextNode("ADDR"));
        elemPstlAdr.appendChild(elemAdrTp);

        // StrtNm elements
        Element elemStrtNm = doc.createElement("StrtNm");
        elemStrtNm.appendChild(doc.createTextNode("BERGERSTASSE"));
        elemPstlAdr.appendChild(elemStrtNm);

        // PstCd elements
        Element elemPstCd = doc.createElement("PstCd");
        elemPstCd.appendChild(doc.createTextNode("000912"));
        elemPstlAdr.appendChild(elemPstCd);

        // TwnNm elements
        Element elemTwnNm = doc.createElement("TwnNm");
        elemTwnNm.appendChild(doc.createTextNode("STOCKOLM"));
        elemPstlAdr.appendChild(elemTwnNm);

        // CtrySubDvsn elements
        Element elemCtrySubDvsn = doc.createElement("CtrySubDvsn");
        elemCtrySubDvsn.appendChild(doc.createTextNode("STK"));
        elemPstlAdr.appendChild(elemCtrySubDvsn);

        // Ctry elements
        Element elemCtry = doc.createElement("Ctry");
        elemCtry.appendChild(doc.createTextNode("SE"));
        elemPstlAdr.appendChild(elemCtry);


        // CdtrAcct elements
        Element elemCdtrAcct = doc.createElement("CdtrAcct");
        elemCdtTrfTxInf.appendChild(elemCdtrAcct);

        // Id elements
        Element elemId = doc.createElement("Id");
        elemCdtrAcct.appendChild(elemId);

        // IBAN elements
        Element elemIBAN = doc.createElement("IBAN");
        elemIBAN.appendChild(doc.createTextNode("SE1130000000039687767480"));
        elemId.appendChild(elemIBAN);

        // RgltryRptg elements
        Element elemRgltryRptg = doc.createElement("RgltryRptg");
        elemCdtTrfTxInf.appendChild(elemRgltryRptg);

        // DbtCdtRptgInd elements
        Element elemDbtCdtRptgInd = doc.createElement("DbtCdtRptgInd");
        elemDbtCdtRptgInd.appendChild(doc.createTextNode("DEBT"));
        elemRgltryRptg.appendChild(elemDbtCdtRptgInd);

        // Dtls elements
        Element elemDtls = doc.createElement("Dtls");
        elemRgltryRptg.appendChild(elemDtls);

        // Cd elements
        Element elemCd2 = doc.createElement("Cd");
        elemCd2.appendChild(doc.createTextNode("INF"));
        elemDtls.appendChild(elemCd2);

        // Amt elements
        Element elemAmt2 = doc.createElement("Amt");
        elemAmt2.appendChild(doc.createTextNode("500"));
        elemDtls.appendChild(elemAmt2);

        // RmtInf elements
        Element elemRmtInf = doc.createElement("RmtInf");
        elemCdtTrfTxInf.appendChild(elemRmtInf);

        // Ustrd elements
        Element elemUstrd = doc.createElement("Ustrd");
        elemUstrd.appendChild(doc.createTextNode("INVOICE N. 124"));
        elemRmtInf.appendChild(elemUstrd);
    }

    private void builtCdtTrfTxInf3(Document doc, Element elemCBIPaymentRequest) throws ParserConfigurationException, TransformerException
    {
        // CdtTrfTxInf elements
        Element elemCdtTrfTxInf = doc.createElement("CdtTrfTxInf");
        elemCBIPaymentRequest.appendChild(elemCdtTrfTxInf);

        // PmtId elements
        Element elemPmtId = doc.createElement("PmtId");
        elemCdtTrfTxInf.appendChild(elemPmtId);

        // InstrId elements
        Element elemInstrId = doc.createElement("InstrId");
        elemInstrId.appendChild(doc.createTextNode("3"));
        elemPmtId.appendChild(elemInstrId);

        // EndToEndId elements
        Element elemEndToEndId = doc.createElement("EndToEndId");
        elemEndToEndId.appendChild(doc.createTextNode("A0090"));
        elemPmtId.appendChild(elemEndToEndId);

        // PmtTpInf elements
        Element elemPmtTpInf = doc.createElement("PmtTpInf");
        elemCdtTrfTxInf.appendChild(elemPmtTpInf);

        // CtgyPurp elements
        Element elemCtgyPurp = doc.createElement("CtgyPurp");
        elemPmtTpInf.appendChild(elemCtgyPurp);

        // Cd elements
        Element elemCd = doc.createElement("Cd");
        elemCd.appendChild(doc.createTextNode("SUPP"));
        elemCtgyPurp.appendChild(elemCd);

        // Amt elements
        Element elemAmt = doc.createElement("Amt");
        elemCdtTrfTxInf.appendChild(elemAmt);

        // InstdAmt elements
        Element elemInstdAmt = doc.createElement("InstdAmt");
        elemInstdAmt.appendChild(doc.createTextNode("14"));
        elemAmt.appendChild(elemInstdAmt);

        // SrvInf elements
        Element elemSrvInf = doc.createElement("SrvInf");
        elemSrvInf.appendChild(doc.createTextNode("ESBEN"));
        elemCdtTrfTxInf.appendChild(elemSrvInf);

        // Cdtr elements
        Element elemCdtr = doc.createElement("Cdtr");
        elemCdtTrfTxInf.appendChild(elemCdtr);

        // Nm elements
        Element elemNm = doc.createElement("Nm");
        elemNm.appendChild(doc.createTextNode("SERE.CO. SPA"));
        elemCdtr.appendChild(elemNm);

        // PstlAdr elements
        Element elemPstlAdr = doc.createElement("PstlAdr");
        elemCdtr.appendChild(elemPstlAdr);

        // AdrTp elements
        Element elemAdrTp = doc.createElement("AdrTp");
        elemAdrTp.appendChild(doc.createTextNode("ADDR"));
        elemPstlAdr.appendChild(elemAdrTp);

        // StrtNm elements
        Element elemStrtNm = doc.createElement("StrtNm");
        elemStrtNm.appendChild(doc.createTextNode("VIA MONTEGRAPPA N.102"));
        elemPstlAdr.appendChild(elemStrtNm);

        // PstCd elements
        Element elemPstCd = doc.createElement("PstCd");
        elemPstCd.appendChild(doc.createTextNode("20100"));
        elemPstlAdr.appendChild(elemPstCd);

        // TwnNm elements
        Element elemTwnNm = doc.createElement("TwnNm");
        elemTwnNm.appendChild(doc.createTextNode("MILANO"));
        elemPstlAdr.appendChild(elemTwnNm);

        // CtrySubDvsn elements
        Element elemCtrySubDvsn = doc.createElement("CtrySubDvsn");
        elemCtrySubDvsn.appendChild(doc.createTextNode("MI"));
        elemPstlAdr.appendChild(elemCtrySubDvsn);

        // Ctry elements
        Element elemCtry = doc.createElement("Ctry");
        elemCtry.appendChild(doc.createTextNode("IT"));
        elemPstlAdr.appendChild(elemCtry);

        // Id elements
        Element elemId = doc.createElement("Id");
        elemCdtr.appendChild(elemId);

        // OrgId elements
        Element elemOrgId = doc.createElement("OrgId");
        elemId.appendChild(elemOrgId);

        // Othr elements
        Element elemOthr = doc.createElement("Othr");
        elemOrgId.appendChild(elemOthr);

        // Id elements
        Element elemId2 = doc.createElement("Id");
        elemId2.appendChild(doc.createTextNode("BRTFBA69T06H598H"));
        elemPstlAdr.appendChild(elemId2);

        // Issr elements
        Element elemIssr = doc.createElement("Issr");
        elemIssr.appendChild(doc.createTextNode("ADE"));
        elemPstlAdr.appendChild(elemIssr);

        // CdtrAcct elements
        Element elemCdtrAcct = doc.createElement("CdtrAcct");
        elemCdtTrfTxInf.appendChild(elemCdtrAcct);

        // Id elements
        Element elemIdIBAN = doc.createElement("Id");
        elemCdtrAcct.appendChild(elemIdIBAN);

        // IBAN elements
        Element elemIBAN = doc.createElement("IBAN");
        elemIBAN.appendChild(doc.createTextNode("IT81A0834052940000000072507"));
        elemIdIBAN.appendChild(elemIBAN);

        // DestCdtrRsp elements
        Element elemDestCdtrRsp = doc.createElement("DestCdtrRsp");
        elemCdtTrfTxInf.appendChild(elemDestCdtrRsp);

        // Nm elements
        Element elemNm2 = doc.createElement("Nm");
        elemNm2.appendChild(doc.createTextNode("SERE.CO. SPA"));
        elemDestCdtrRsp.appendChild(elemNm2);

        // Id elements
        Element elemId3 = doc.createElement("Id");
        elemDestCdtrRsp.appendChild(elemId3);

        // OrgId elements
        Element elemOrgId2 = doc.createElement("OrgId");
        elemId3.appendChild(elemOrgId2);

        // Othr elements
        Element elemOthr2 = doc.createElement("Othr");
        elemOrgId2.appendChild(elemOthr2);

        // Id elements
        Element elemId4 = doc.createElement("Id");
        elemId4.appendChild(doc.createTextNode("1234567A"));
        elemOthr2.appendChild(elemId4);

        // Issr elements
        Element elemIssr1 = doc.createElement("Issr");
        elemIssr1.appendChild(doc.createTextNode("CBI"));
        elemOthr2.appendChild(elemIssr1);

        // RmtInf elements
        Element elemRmtInf = doc.createElement("RmtInf");
        elemCdtTrfTxInf.appendChild(elemRmtInf);

        // Ustrd elements
        Element elemUstrd = doc.createElement("Ustrd");
        elemUstrd.appendChild(doc.createTextNode("PAGAMENTO FATTURA 197"));
        elemRmtInf.appendChild(elemUstrd);
    }

    private void begin(Document doc) throws ParserConfigurationException, TransformerException
    {
        // Elemento radice
        Element elemCBIBdyPaymentRequest = doc.createElement("CBIBdyPaymentRequest");
        doc.appendChild(elemCBIBdyPaymentRequest);

        // CBIPaymentRequest elements
        Element elemCBIPaymentRequest = doc.createElement("CBIPaymentRequest");
        elemCBIBdyPaymentRequest.appendChild(elemCBIPaymentRequest);

        builtGrpHdr(doc, elemCBIPaymentRequest);

        builtPmtInf(doc, elemCBIPaymentRequest);

        directionCdtTrfTxInf(doc, elemCBIPaymentRequest);
    }

    private void writeXML(Document doc) throws ParserConfigurationException, TransformerException
    {
        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("file.xml"));
        //result = new StreamResult(System.out);

        transformer.transform(source, result);
    }

    public void start()
    {
        try
        {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            // fase iniziale: realizzazione del file .xml
            begin(doc);

            // fase finale: scrittura sul file .xml
            writeXML(doc);

            System.out.println("File saved!");
        }
        catch(ParserConfigurationException pce){
            pce.printStackTrace();}
        catch(TransformerException tfe){
            tfe.printStackTrace();}
        catch(Exception exc){}
    }


    public static void main(String[] args)
    {
        Management prova = new Management();

        prova.start();
    }
}