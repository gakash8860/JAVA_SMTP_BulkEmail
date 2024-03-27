package com.bulkemail.BulkEmail.controller;

import com.bulkemail.BulkEmail.EmailSenderService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class ExcelReader {

    @Autowired
    private EmailSenderService senderService;

    public  List<String> readEmailColumn(InputStream inputStream) throws IOException {
        ArrayList<String> emails = new ArrayList<>();
        Workbook workbook = null;

        try {
            workbook = WorkbookFactory.create(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        // Skip the first row assuming it contains headers like "Sr No." and "Email"
        if (rowIterator.hasNext()) {
            rowIterator.next(); // Skip the header row
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell emailCell = row.getCell(1); // Assuming "Email" column is at index 1 (0-indexed)
            if (emailCell != null) {
                String email = getCellValueAsString(emailCell);
                emails.add(email);
            }
        }

        workbook.close();
        sendEmail(emails);
        return emails;
    }

    private  String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return Double.toString(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return Boolean.toString(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
    public  void sendEmail(ArrayList<String> arrayList){


//        arrayList.add("escaper68@gmail.com");
        arrayList.add("gakash8860@gmail.com");
        arrayList.add("santosh20gtc279@student.gangatechnicalcampus.com");
        arrayList.add("shubham20gtc433@student.gangatechnicalcampus.com");

        senderService.sendEmail(arrayList,"this is dummy Bulk Email","this is Bulk Email body");
    }
}
