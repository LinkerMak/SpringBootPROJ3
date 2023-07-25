package com.example.springbootproj.service.excel;

import com.example.springbootproj.entity.Book;
import com.example.springbootproj.entity.Form1;
import com.example.springbootproj.entity.Reader;
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

    public void createRow(int index, Form1 form, Book book, Reader reader) {
        SXSSFRow row = sheet.createRow(index);
        setCellValue(row.createCell(0), reader.getName());
        setCellValue(row.createCell(1), reader.getEmail());
        setCellValue(row.createCell(2), reader.getNumber());
        setCellValue(row.createCell(3), book.getName());
        setCellValue(row.createCell(4), book.getAuthor());
        setCellValue(row.createCell(5), form.getDate_take());
        setCellValue(row.createCell(6), form.getDate_return());
        setCellValue(row.createCell(7), form.getDate_fact_return());
        setCellValue(row.createCell(8), form.getCount());
        setCellValue(row.createCell(9), form.getMerge());
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
        setCellValue(rowTitle.createCell(4), "Author");
        setCellValue(rowTitle.createCell(5), "Date Take");
        setCellValue(rowTitle.createCell(6), "Date Return");
        setCellValue(rowTitle.createCell(7), "Date Fact Return");
        setCellValue(rowTitle.createCell(8), "Delay");
        setCellValue(rowTitle.createCell(9), "Merge");
    }

    private void setCellValue(SXSSFCell cell, String value) {
        cell.setCellValue(value);
    }

    private void setCellValue(SXSSFCell cell, int value) {
        cell.setCellValue(value);
    }

}
