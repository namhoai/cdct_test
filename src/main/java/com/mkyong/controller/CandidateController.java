package com.mkyong.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mkyong.entites.Candidate;
import com.mkyong.entites.Faculty;
import com.mkyong.service.CandidateService;
import com.mkyong.service.FacultyService;

import net.sf.jasperreports.engine.JRException;

@Controller
public class CandidateController {
	
	@Autowired
	private CandidateService candidateService;
	@Autowired
	private FacultyService facultyService;
	@Autowired
	private ServletContext context;
	
	
	@GetMapping("/user/listCandidate")
    public String showListCandidate(@Param("type") int type, Model model) {
		List<Candidate> listCandidateByType = candidateService.getListCandidateByType(type);
		model.addAttribute("listCandidate", listCandidateByType);
		if(type == 1) {
			return "listCandidate_student";
		} 
		return "listCandidate_overseas";
    	
    }
	
	@RequestMapping(value = {"/addCandidate"}, method = RequestMethod.POST)
    public String addCandidate(@ModelAttribute("candidate") Candidate candidate,  @RequestParam(value = "typeCandidate", required = false) String type) {
		candidate.setDateCreated(new Date());
		candidate.setTypeCandidate(Integer.parseInt(type));
		candidateService.addCandidate(candidate);
    	return "index";
    }
	
	@RequestMapping(value = {"/admin/printReport"})
    public void generateExcel(Model model, HttpServletResponse response,HttpServletRequest request) throws JRException, IOException {
	    List<Candidate> listCandidateByType = candidateService.getListCandidateByType(1);
	    model.addAttribute("listCandidate", listCandidateByType);
	    
	    String filePath = context.getRealPath("/resources/reports");
	    File file = new File(filePath);
	    boolean exists = new File(filePath).exists();
	    if(!exists) {
	    	new File(filePath).mkdirs();
	    }
	    
	    try {
	    	FileOutputStream outputStream = new FileOutputStream(file + "/" +"danhsachchinhquy.xls");
	    	HSSFWorkbook workbook = new HSSFWorkbook();
	 		HSSFSheet sheet = workbook.createSheet("Danh sách ứng viên đăng kí chính quy");
	 		sheet.setDefaultColumnWidth(30);
	 	    HSSFRow header = sheet.createRow(0);
	 	   
	 	    HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
	 	    hssfCellStyle.setFillBackgroundColor(HSSFColor.BLUE.index);
	 	     
	 	    header.createCell(0).setCellValue("NGÀY ĐĂNG KÍ");
	  	    header.createCell(1).setCellValue("TÊN ỨNG VIÊN");
	 	    header.createCell(2).setCellValue("SỐ ĐIỆN THOẠI");
	 	    header.createCell(3).setCellValue("ĐỊA CHỈ");
	 	    header.createCell(4).setCellValue("KHOA ĐĂNG KÍ");
	 	    header.createCell(5).setCellValue("NGÀY SINH");
	 	    header.createCell(6).setCellValue("SỐ ĐIỂM THI");
	 	    header.createCell(7).setCellValue("KHỐI THI");
	 	    
	 	    int rowNum = 1;
	 	    
	 	    for(Candidate candidate : listCandidateByType){
	 	    	 HSSFRow row = sheet.createRow(rowNum++);
	 		     row.createCell(0).setCellValue(candidate.getDateCreated().toString().substring(0,10));
	 		     row.createCell(1).setCellValue(candidate.getFullName());
	 		     row.createCell(2).setCellValue(candidate.getPhoneNumber());
	 		     row.createCell(3).setCellValue(candidate.getAddress());
	 		     row.createCell(4).setCellValue(candidate.getFacultyName());
	 		     row.createCell(5).setCellValue(candidate.getDateOfBirth().toString().substring(0,10));
	 		     row.createCell(6).setCellValue(candidate.getTotalScore());
	 		     row.createCell(7).setCellValue(candidate.getGrade());
	 	    }
	 	   workbook.write(outputStream);
	 	   outputStream.flush();
	 	   outputStream.close();
	 	   
	 	   String fullPath = request.getServletContext().getRealPath("resources/reports/danhsachchinhquy.xls");
	 	   fileDownload(fullPath, response, "danhsachchinhquy.xls");
	    } catch (Exception e) {
	    	e.printStackTrace();
		}
	    
	}
	
	@RequestMapping(value = {"/admin/printReportOversea"})
    public void generateExcelOversea(Model model, HttpServletResponse response,HttpServletRequest request) throws JRException, IOException {
	    List<Candidate> listCandidateByType = candidateService.getListCandidateByType(2);
	    model.addAttribute("listCandidate", listCandidateByType);
	    
	    String filePath = context.getRealPath("/resources/reports");
	    File file = new File(filePath);
	    boolean exists = new File(filePath).exists();
	    if(!exists) {
	    	new File(filePath).mkdirs();
	    }
	    
	    try {
	    	FileOutputStream outputStream = new FileOutputStream(file + "/" +"danhsachduhoc.xls");
	    	HSSFWorkbook workbook = new HSSFWorkbook();
	 		HSSFSheet sheet = workbook.createSheet("Danh sách ứng viên đăng kí du học");
	 		sheet.setDefaultColumnWidth(30);
	 	    HSSFRow header = sheet.createRow(0);
	 	   
	 	    HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
	 	    hssfCellStyle.setFillBackgroundColor(HSSFColor.BLUE.index);
	 	     
	 	    header.createCell(0).setCellValue("NGÀY ĐĂNG KÍ");
	  	    header.createCell(1).setCellValue("TÊN ỨNG VIÊN");
	 	    header.createCell(2).setCellValue("SỐ ĐIỆN THOẠI");
	 	    header.createCell(3).setCellValue("ĐỊA CHỈ");
	 	    header.createCell(4).setCellValue("NƯỚC ĐĂNG KÍ");
	 	    header.createCell(5).setCellValue("KHỐI NGÀNH");
	 	    header.createCell(6).setCellValue("NGÀY SINH");
	 	    header.createCell(7).setCellValue("SỐ ĐIỂM THI");
	 	    header.createCell(8).setCellValue("KHỐI THI");
	 	    
	 	    int rowNum = 1;
	 	    
	 	    for(Candidate candidate : listCandidateByType){
	 	    	 HSSFRow row = sheet.createRow(rowNum++);
	 		     row.createCell(0).setCellValue(candidate.getDateCreated().toString().substring(0,10));
	 		     row.createCell(1).setCellValue(candidate.getFullName());
	 		     row.createCell(2).setCellValue(candidate.getPhoneNumber());
	 		     row.createCell(3).setCellValue(candidate.getAddress());
	 		     row.createCell(4).setCellValue(candidate.getCountryName());
	 		     row.createCell(5).setCellValue(candidate.getMajorName());
	 		     row.createCell(6).setCellValue(candidate.getDateOfBirth().toString().substring(0,10));
	 		     row.createCell(7).setCellValue(candidate.getTotalScore());
	 		     row.createCell(8).setCellValue(candidate.getGrade());
	 	    }
	 	   workbook.write(outputStream);
	 	   outputStream.flush();
	 	   outputStream.close();
	 	   
	 	   String fullPath = request.getServletContext().getRealPath("resources/reports/danhsachduhoc.xls");
	 	   fileDownload(fullPath, response, "danhsachduhoc.xls");
	    } catch (Exception e) {
	    	e.printStackTrace();
		}
	    
	}
	
	
	private void fileDownload(String fullPath, HttpServletResponse response, String fileName) {
		File file = new File(fullPath);
		final int BUFFER_SIZE = 4096;
		
		if(file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				String mileType = context.getMimeType(fileName);
				response.setContentType(mileType);
				response.setHeader("content-disposition", "attachment; filename="+fileName);
 				OutputStream outputStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int byteRead = -1;
				
				while((byteRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer,0,byteRead);
				}
				inputStream.close();
				outputStream.close();
				file.delete();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
