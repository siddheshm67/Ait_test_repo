package com.hsa.co.service;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hsa.co.Repo.CoRepo;
import com.hsa.co.enity.AppEligibilityInfo;
import com.hsa.co.enity.CoEntity;
import com.hsa.co.util.EmailUtil;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class CoServiceImpl implements CoService {

	@Autowired
	private CoRepo coRepo;
	
	@Autowired
	private EmailUtil emailUtil;

	@Override
	public List<CoEntity> getNoticesByAppNoAndStatus(Integer appNum, String status) {
		return coRepo.getNotices(appNum, status);
	}
	
	@Override
	public CoEntity getNoticeByAppNo(Integer appNo) {
		RestTemplate restTemplate = new RestTemplate();
		CoEntity coEntity = new CoEntity();
		CoEntity entity = null;
		// get ElegibilityInfo From AppNum
				ResponseEntity<AppEligibilityInfo> appElegInfo = restTemplate
						.getForEntity("http://localhost:8084/elegInfo/" + appNo, AppEligibilityInfo.class);
		
		if (appElegInfo.getBody().geteId() != null) {
			CoEntity e1 = coRepo.findByAppNum(appNo);
			if (e1 == null) {
				coEntity.setAppNum(appNo);
				coEntity.setNoticePrintDate("NA");
				coEntity.setNoticeS3Url("NA");
				coEntity.setNoticeStatus("Pending");
				entity = coRepo.save(coEntity);
			}else {
				entity = coRepo.findByAppNum(appNo);
			}
		}		
		return entity;
	}

	@Override
	public boolean printNotices(Integer coId) throws FileNotFoundException {
		RestTemplate restTemplate = new RestTemplate();
		File noticePdf = new File(coId+".pdf");
		
		CoEntity coEntity = coRepo.findById(coId).orElseThrow();
		Integer appNum = coEntity.getAppNum();
		//get citizen application from appNum
		ResponseEntity<com.hsa.co.enity.CitizenApp> citizenApp = restTemplate
				.getForEntity("http://localhost:8082/app/" + appNum, com.hsa.co.enity.CitizenApp.class);

		// get ElegibilityInfo From AppNum
		ResponseEntity<AppEligibilityInfo> appElegInfo = restTemplate
				.getForEntity("http://localhost:8084/elegInfo/" + appNum, AppEligibilityInfo.class);
		
		String planStatus = appElegInfo.getBody().getPlanStatus();
		
		
		if ("APPROVED".equalsIgnoreCase(planStatus)) {
			generateApprovednotice(appElegInfo.getBody(),citizenApp.getBody(),noticePdf);
		}else {
			generateDeniednotice(appElegInfo.getBody(),citizenApp.getBody(),noticePdf);
		}
		
		String url = uploadnoticeToS3(noticePdf);
		
		coEntity.setNoticeS3Url(url);
		coEntity.setNoticeStatus("HISTORY");
		coEntity.setNoticePrintDate(LocalDate.now().toString());
		coRepo.save(coEntity);
		
		
		//send pdf file as an attachment
		
		String subject = "";
		String body = "";
		String toAdd = citizenApp.getBody().getEmail();
		
		emailUtil.sendEmail(subject, body, toAdd, noticePdf);

		return false;
	}

	private void generateApprovednotice(AppEligibilityInfo appElegInfo, com.hsa.co.enity.CitizenApp citizenApp,File pdFile) throws FileNotFoundException {
		FileOutputStream fileOutputStream = new FileOutputStream(pdFile);
		Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, fileOutputStream);
         
        document.open();
        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Elegibility Approved notice", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
        
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f, 3.5f});
        table.setSpacingBefore(10);
        
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        com.lowagie.text.Font f = FontFactory.getFont(FontFactory.HELVETICA);
        f.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Application No", f));
         
        table.addCell(cell);
        table.addCell(String.valueOf(citizenApp.getAppNum()));
         
        cell.setPhrase(new Phrase("Name", f));
        table.addCell(cell);
        table.addCell(citizenApp.getName());
         
        cell.setPhrase(new Phrase("Plan Name", f));
        table.addCell(cell);
        table.addCell(appElegInfo.getPlanName());
         
        cell.setPhrase(new Phrase("Plan Status", f));
        table.addCell(cell);
        table.addCell(appElegInfo.getPlanStatus());
         
        cell.setPhrase(new Phrase("Plan Start Date", f));
        table.addCell(cell); 
        table.addCell(appElegInfo.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        
        cell.setPhrase(new Phrase("Plan End Date", f));
        table.addCell(cell); 
        table.addCell(appElegInfo.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        
        cell.setPhrase(new Phrase("Benifit Amount", f));
        table.addCell(cell); 
        table.addCell(String.valueOf(appElegInfo.getBenifitAmt()));
        
        document.add(table);
        
        try {
        	document.close();
			fileOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
		
	}
	
	private void generateDeniednotice(AppEligibilityInfo appElegInfo, com.hsa.co.enity.CitizenApp citizenApp,File pdFile) throws FileNotFoundException {
		
		FileOutputStream fileOutputStream = new FileOutputStream(pdFile);
		Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, fileOutputStream);
         
        document.open();
        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Elegibility Approved notice", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
        
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.5f, 3.5f});
        table.setSpacingBefore(10);
        
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        com.lowagie.text.Font f = FontFactory.getFont(FontFactory.HELVETICA);
        f.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Application No", f));
         
        table.addCell(cell);
        table.addCell(String.valueOf(citizenApp.getAppNum()));
         
        cell.setPhrase(new Phrase("Name", f));
        table.addCell(cell);
        table.addCell(citizenApp.getName());
         
        cell.setPhrase(new Phrase("Plan Name", f));
        table.addCell(cell);
        table.addCell(appElegInfo.getPlanName());
         
        cell.setPhrase(new Phrase("Plan Status", f));
        table.addCell(cell);
        table.addCell(appElegInfo.getPlanStatus());
         
        cell.setPhrase(new Phrase("Plan Denial Reason", f));
        table.addCell(cell); 
        table.addCell(appElegInfo.getDenialReason());
        
        document.add(table);
        
        try {
        	document.close();
			fileOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private String uploadnoticeToS3(File notice) {
		return"uploaded...";
	}

}
