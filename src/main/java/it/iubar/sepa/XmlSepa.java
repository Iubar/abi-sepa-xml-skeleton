package it.iubar.sepa;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlSepa {
	
	private Document doc = null;
	private final String VERSION = "00.03.09";
	private final String OUT_PATH = "C:\\Users\\Daniele\\Desktop\\file.xml";
	
	private String identificativo = null;
	
	//http://ubibanca.edulife.eu/sepa/mod2/comefare/2.Guida_rapida%20per%20composizione%20distinta%20SCT%20XML.pdf

	public void createXml(){
        try {
            this.doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            
            initXml();
            writeXml();
            
            System.out.println("File saved!");
        } catch(Exception e){
        	e.printStackTrace();
        }
    }
	
	 private void writeXml(){
		 try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(this.doc);
			StreamResult result = new StreamResult(new File(OUT_PATH));
			transformer.transform(source, result);
		 } catch (TransformerConfigurationException e) {
			 e.printStackTrace();
		 } catch (TransformerException e) {
			 e.printStackTrace();
		 }
	 }
	
	private void initXml() throws ParserConfigurationException, TransformerException{
		this.identificativo = this.getIdentificativo();
		
        Element elem_cbib_paym_req = this.doc.createElement("CBIBdyPaymentRequest");
        elem_cbib_paym_req.setAttribute("xmlns", "urn:CBI:xsd:CBIBdyPaymentRequest." + VERSION);
        elem_cbib_paym_req.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        elem_cbib_paym_req.setAttribute("xsi:schemaLocation", "urn:CBI:xsd:CBIBdyPaymentRequest." + VERSION + " CBIBdyPaymentRequest." + VERSION + ".xsd");
        this.doc.appendChild(elem_cbib_paym_req);

        Element elem_cbie_paym_req = this.doc.createElement("CBIEnvelPaymentRequest");
        elem_cbib_paym_req.appendChild(elem_cbie_paym_req);

        Element elem_cbip_paym_req = this.doc.createElement("CBIPaymentRequest");
        elem_cbie_paym_req.appendChild(elem_cbip_paym_req);
        
        this.initInfoDistinta(elem_cbip_paym_req);
        this.initInfoContabili(elem_cbip_paym_req);
        this.initDisposizioni(elem_cbip_paym_req); // TODO: foreach lavoratori
    }
	
	private void initInfoDistinta(Element element_root) {
		Element elem_grp_hdr = this.doc.createElement("GrpHdr");
        // elem_grp_hdr.setAttribute("xmlns", "urn:CBI:xsd:CBIPaymentRequest." + VERSION); // TODO: verificare (nella documentazione c'Ã¨ ma quando viene validato con l'xsd da eccezione)
        element_root.appendChild(elem_grp_hdr);

		// Identificativo univoco messaggio <MsgId> [Max35Text]
        // Riferimento assegnato dal Mittente per identificare univocamente la distinta (Messaggio logico); dev'essere univoco a paritÃ  di data
        // creazione e Mittente; si raccomanda di utilizzare il medesimo valore per il campo <PmtInfId>; tale dato verrÃ  riportato sul
        // corrispondente movimento di addebito che verrÃ  registrato sullâ€™estratto conto. 
        // PoichÃ© NON Ã¨ possibile riutilizzare lo stesso identificativo per piÃ¹ distinte, si consiglia di utilizzare valori dinamici calcolati, ad
        // esempio, sulla base di data e ora che ne garantiscono l'univocitÃ , come ad esempio â€œDistintaXml-080413-18.19â€�; evitare invece di
        // inserire un valore statico come potrebbe essere il nome dellâ€™azienda.
        elem_grp_hdr.appendChild(this.createElement("MsgId", this.identificativo));
        
        // Data e Ora di Creazione <CreDtTm> [ISO DateTime, es. â€œ2013-04-08T18:19:00+01:00â€�]
        elem_grp_hdr.appendChild(this.createElement("CreDtTm", this.getTimeStampIso()));
        
        // 	Numero transazioni incluse nella distinta <NbOfTxs> [Max15NumericText]
        elem_grp_hdr.appendChild(this.createElement("NbOfTxs", "2")); // TODO: valorizzare
        
        // Totale importi delle transazioni incluse nelle distinta <CtrlSum> [DecimalNumber es â€œ180.51â€�].
        // L'importo deve essere compreso tra 0.01 e 999999999999999.99; la parte decimale deve essere max di 2 cifre ma puÃ² essere
        // anche assente; come separatore decimale deve essere utilizzato il punto.
        elem_grp_hdr.appendChild(this.createElement("CtrlSum", "180.51")); // TODO: valorizzare
                 
        Element elem_initg_pty = this.doc.createElement("InitgPty");
        elem_grp_hdr.appendChild(elem_initg_pty);

        // Nome azienda mittente <InitgPty><Nm> [Max70Text]
        elem_initg_pty.appendChild(this.createElement("Nm", "TODO"));
        
        Element elem_id = this.doc.createElement("Id");
        elem_initg_pty.appendChild(elem_id);

        Element elem_org_id = this.doc.createElement("OrgId");
        elem_id.appendChild(elem_org_id);
        
        Element elem_othr = this.doc.createElement("Othr");
        elem_org_id.appendChild(elem_othr);
        
        // Codice CUC azienda mittente <OrgId><Othr><Id> [8Text]
        // Codice Unico CBI, giÃ  assegnato a ciascuna azienda; sostituisce il codice SIA e puÃ² essere richiesto alla vostra filiale.
        elem_othr.appendChild(this.createElement("Id", "TODO"));
        elem_othr.appendChild(this.createElement("Issr", "CBI")); // TODO: corretto?
	}
	
	private void initInfoContabili(Element element_root) {
		Element elem_pmt_inf = this.doc.createElement("PmtInf");
        // elem_pmt_inf.setAttribute("xmlns", "urn:CBI:xsd:CBIPaymentRequest." + VERSION); // TODO: verificare (nella documentazione c'Ã¨ ma quando viene validato con l'xsd da eccezione)
        element_root.appendChild(elem_pmt_inf);
        
        // Identificativo informazioni di addebito < PmtInfId> [Max35Text]			
		// Stesso valore contenuto in <MsgId>
        elem_pmt_inf.appendChild(this.createElement("PmtInfId", this.identificativo));
        
        ///////////////////// TODO : chiarire questo blocco, non Ã¨ documentato
        elem_pmt_inf.appendChild(this.createElement("PmtMtd", "TRF"));
        
        Element elem_pmt_tp_inf = this.doc.createElement("PmtTpInf");
        elem_pmt_inf.appendChild(elem_pmt_tp_inf);
        
        elem_pmt_tp_inf.appendChild(this.createElement("InstrPrty", "NORM"));
        
        Element elem_svc_lvl = this.doc.createElement("SvcLvl");
        elem_pmt_tp_inf.appendChild(elem_svc_lvl);
        
        elem_svc_lvl.appendChild(this.createElement("Cd", "SEPA"));

        ///////////////////////////////////////////
        
        // Data di esecuzione richiesta <ReqdExctnDt> [ISODate, es. â€œ2013-04-09â€�]
        elem_pmt_inf.appendChild(this.createElement("ReqdExctnDt", this.getDataRichiesta()));
        
        Element elem_dbtr = this.doc.createElement("Dbtr");
        elem_pmt_inf.appendChild(elem_dbtr);
        
		// Nome azienda ordinante <Dbtr> <Nm> [Max70Text]			
		// Nome dellâ€™azianda titolare del conto corrente di addebito
        elem_dbtr.appendChild(this.createElement("Nm", "TODO"));
        
        Element elem_id = this.doc.createElement("Id");
        elem_dbtr.appendChild(elem_id);

        Element elem_org_id = this.doc.createElement("OrgId");
        elem_id.appendChild(elem_org_id);
        
        Element elem_othr = this.doc.createElement("Othr");
        elem_org_id.appendChild(elem_othr);

        // Codifica fiscale debitore <Dbtr> <Id> <OrgId> <Othr> <Id> [Max16Text]			
		// Codice fiscale o Partita Iva del debitore; lungo 11 caratteri se numerico, con allineamento a sinistra, 16 se alfanumerico.
        elem_othr.appendChild(this.createElement("Id", "TODO"));
        elem_othr.appendChild(this.createElement("Issr", "ADE")); // TODO: corretto?
        
        Element elem_dbtr_acct = this.doc.createElement("DbtrAcct");
        elem_pmt_inf.appendChild(elem_dbtr_acct);
        
        Element elem_id_acct = this.doc.createElement("Id");
        elem_dbtr_acct.appendChild(elem_id_acct);
        
		// IBAN conto di addebito <DbtrAcct> <Id> <IBAN> [IBAN2007Identifier]
        elem_id_acct.appendChild(this.createElement("IBAN", "TODO"));
        
        Element elem_dbtr_agt = this.doc.createElement("DbtrAgt");
        elem_pmt_inf.appendChild(elem_dbtr_agt);
        
        Element elem_fin_instn_id = this.doc.createElement("FinInstnId");
        elem_dbtr_agt.appendChild(elem_fin_instn_id);
        
        Element elem_clr_sys_mmd_id = this.doc.createElement("ClrSysMmbId");
        elem_fin_instn_id.appendChild(elem_clr_sys_mmd_id);
        
        // Abi Banca debitore <DbtrAgt> <FinInstnId> <ClrSysMmbId><MmbId> [Max5Text]
        // Codice ABI valido, espresso in forma di 5 caratteri numerici allineati a sinistra.
        elem_clr_sys_mmd_id.appendChild(this.createElement("MmbId", "TODO"));
        
        elem_pmt_inf.appendChild(this.createElement("ChrgBr", "SLEV")); // TODO: corretto?
	}

	private void initDisposizioni(Element element_root) {
		Element elem_inf = this.doc.createElement("CdtTrfTxInf");
        // elem_inf.setAttribute("xmlns", "urn:CBI:xsd:CBIPaymentRequest." + VERSION); // TODO: verificare (nella documentazione c'Ã¨ ma quando viene validato con l'xsd da eccezione)
        element_root.appendChild(elem_inf);
        
        Element elem_pmt_id = this.doc.createElement("PmtId");
        elem_inf.appendChild(elem_pmt_id);
        
		// Progressivo disposizione <InstrId> [Max35Text]
		// Identificativo univoco, a livello di distinta, assegnato all'istruzione dal Mittente nei confronti della sua Banca; si consiglia di utilizzare
		// una numerazione sequenziale (1,2,3,â€¦): la prima disposizione sarÃ  quindi caratterizzata dal valore 1, la seconda dal valore 2,
        // la terza dal valore 3, â€¦
        elem_pmt_id.appendChild(this.createElement("InstrId", "TODO"));
        
        // Identificativo end-to-end <EndToEndId> [Max35Text]
        // Identificativo URI assegnato dal Mittente e che identifica la singola disposizione di pagamento per tutta la catena di
        // pagamento fino al beneficiario. E' univoco all'interno della distinta.
        elem_pmt_id.appendChild(this.createElement("EndToEndId", this.getIdentificativo())); // TODO: corretto?
        
        Element elem_pmt_inf = this.doc.createElement("PmtTpInf");
        elem_inf.appendChild(elem_pmt_inf);
        
        Element elem_ctgy_purp = this.doc.createElement("CtgyPurp");
        elem_pmt_inf.appendChild(elem_ctgy_purp);
        
        // Causale bancaria (category purpose) <PmtTpInf> <CtgyPurp> <Cd> [4Text]
        // Identifica la causale interbancaria, basata su un set predefinito di categorie; obbligatorio se IBAN c/c di accredito riferito ad IT.
        // Utilizzare â€œSUPPâ€� per bonifici generici, â€œSALAâ€� per stipendi, â€œINTCâ€� per giroconti/girofondi;
        // La lista completa dei codici Ã¨ disponibile all'indirizzo: http://www.iso20022.org/external_code_list.page (â€œExternal Code Lists spreadsheetâ€� foglio â€œ4 CategoryPurposeâ€�).
        elem_ctgy_purp.appendChild(this.createElement("Cd", "SALA")); // TODO: corretto?
        
        Element elem_amt = this.doc.createElement("Amt");
        elem_inf.appendChild(elem_amt);
        
        // Divisa e importo <Amt> <InstdAmt> [ActiveOrHistoricCurrencyAndAmount]
        // Eâ€™ consentito indicare come divisa solo EUR, l'importo deve essere compreso tra 0.01 e 999999999.99; la parte decimale
        // deve essere max di 2 cifre ma puÃ² essere anche assente; come separatore decimale deve essere utilizzato il punto.
        elem_amt.appendChild(this.createElement("InstdAmt", "TODO"));
        elem_amt.setAttribute("Ccy", "EUR");
        
        Element elem_cdtr = this.doc.createElement("Cdtr");
        elem_inf.appendChild(elem_cdtr);
        
        // Nome del beneficiario <Cdtr> <Nm> [Max35Text]
        // Nome del titolare del conto corrente di accredito.
        elem_cdtr.appendChild(this.createElement("Nm", "TODO"));
        
        Element elem_cdtr_acct = this.doc.createElement("CdtrAcct");
        elem_inf.appendChild(elem_cdtr_acct);
        
        Element elem_id = this.doc.createElement("Id");
        elem_cdtr_acct.appendChild(elem_id);
        
        // IBAN conto del creditore <CdtrAcct> <Id> <IBAN> [IBAN2007Identifier]
        elem_id.appendChild(this.createElement("IBAN", "TODO"));
        
        Element elem_rmt_inf = this.doc.createElement("RmtInf");
        elem_inf.appendChild(elem_rmt_inf);
        
        // Informazioni/Causale <RmtInf> <Ustrd> [Max140Text]
        // Informazioni sul pagamento comunicate dallâ€™ordinante al beneficiario (Remittance Information); ad esempio â€œPagamento
        // fattura 10 del 13/03/12â€�, â€œStipendio mese di gennaioâ€�
        elem_rmt_inf.appendChild(this.createElement("Ustrd", "TODO"));
	}
	
	private Element createElement(String key, String value){
		Element elem = this.doc.createElement(key);
		elem.appendChild(this.doc.createTextNode(value));
		
		return elem;
	}
	
	private String getIdentificativo() {
		DateFormat date_format = new SimpleDateFormat("ddMMYY-HH.mm", Locale.ITALY);
		Date date = new Date();
		
		return "DistintaXml-" + date_format.format(date);
	}
	
	private String getTimeStampIso() {
		SimpleDateFormat iso_date_format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.ITALY);
		Date date = new Date();
		
		return iso_date_format.format(date);
	}
	
	private String getDataRichiesta() {
		SimpleDateFormat iso_date_format = new SimpleDateFormat("yyyy-MM-dd", Locale.ITALY);
		Date date = new Date();
		
		return iso_date_format.format(date);
	}
	
}
