package com.pratt.fps.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.pratt.fps.pojo.TxnDetails;

public class ExcelExport extends AbstractExcelView {

	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<TxnDetails> tL = (ArrayList<TxnDetails>) model.get("exceldata");
		HttpSession session = (HttpSession) request.getSession();

		HSSFSheet sheet = workbook.createSheet("Sales Report");

		HSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("Transaction ID");
		header.createCell(1).setCellValue("Date");
		header.createCell(2).setCellValue("From Account ID");
		header.createCell(3).setCellValue("To Account ID");
		header.createCell(4).setCellValue("Amount");
		header.createCell(5).setCellValue("Mode");
		header.createCell(6).setCellValue("Status");
		header.createCell(7).setCellValue("Transaction Type");

		int rowNum = 1;
		for (TxnDetails tx : tL) {
			// create the row data
			HSSFRow row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(tx.getTxnId());
			row.createCell(1).setCellValue(tx.getDate());
			row.createCell(2).setCellValue(tx.getFromAccountId());
			row.createCell(3).setCellValue(tx.getToAccountId());
			row.createCell(4).setCellValue(tx.getAmount());
			row.createCell(5).setCellValue(tx.getMode());
			row.createCell(6).setCellValue(tx.getStatus());
			row.createCell(7).setCellValue(tx.getTxnType());

		}

	}

}
