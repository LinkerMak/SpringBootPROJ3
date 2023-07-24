package com.example.springbootproj.service.excel;

import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;

public class ReportExcelStreamWriter {

    private final SXSSFWorkbook wb;
    private final SXSSFSheet sheet;

    public ReportExcelStreamWriter() {
        this.wb = new SXSSFWorkbook();
        this.sheet = wb.createSheet();
        createTitle();
    }

    public void createRow(int index, MessageData data) {
        SXSSFRow row = sheet.createRow(index);
        setCellValue(row.createCell(0), data.getMessageDate());
        setCellValue(row.createCell(1), data.getName());
        setCellValue(row.createCell(2), data.getRating());
        setCellValue(row.createCell(3), data.getText());
    }

    public void writeWorkbook() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(Instant.now().getEpochSecond() + ".xlsx");
        wb.write(fileOut);
        fileOut.close();
    }

    private void createTitle() {
        SXSSFRow rowTitle = sheet.createRow(0);
        setCellValue(rowTitle.createCell(0), "Name");
        setCellValue(rowTitle.createCell(1), "Email");
        setCellValue(rowTitle.createCell(2), "Number");
        setCellValue(rowTitle.createCell(3), "Book");
        setCellValue(rowTitle.createCell(3), "Author");
        setCellValue(rowTitle.createCell(3), "Date Take");
        setCellValue(rowTitle.createCell(3), "Date Return");
        setCellValue(rowTitle.createCell(3), "Date Fact Return");
        setCellValue(rowTitle.createCell(3), "Delay");
        setCellValue(rowTitle.createCell(3), "Merge");
    }

    private void setCellValue(SXSSFCell cell, String value) {
        cell.setCellValue(value);
    }

    private void setCellValue(SXSSFCell cell, long value) {
        cell.setCellValue(value);
    }

    private void setCellValue(SXSSFCell cell, Instant value) {
        cell.setCellValue(value.toString());
    }
}
