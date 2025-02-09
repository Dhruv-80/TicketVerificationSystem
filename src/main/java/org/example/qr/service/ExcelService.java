package org.example.qr.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.qr.entity.Attendee;
import org.example.qr.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ExcelService {
    @Autowired
    private AttendeeRepository attendeeRepository;

    public void importExcel(MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header row
            Attendee attendee = new Attendee();
            attendee.setTicketId(row.getCell(0).getStringCellValue());
            attendee.setName(row.getCell(1).getStringCellValue());
            attendee.setEmail(row.getCell(2).getStringCellValue());
            attendee.setCheckedIn(false);
            attendeeRepository.save(attendee);
        }
        workbook.close();
    }
}