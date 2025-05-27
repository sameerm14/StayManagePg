package com.pg.StayManage.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pg.StayManage.Model.Rent;
import com.pg.StayManage.Repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PdfGenerationService {

    @Autowired
    private RentRepository rentRepository;

    public void generateRentReport(OutputStream outputStream, int year, int month)
            throws DocumentException {

        List<Rent> rentList = rentRepository.findAll().stream()
                .filter(rent -> rent.getMonth() != null &&
                        rent.getMonth().getYear() == year &&
                        rent.getMonth().getMonthValue() == month)
                .collect(Collectors.toList());

        int paidCount = 0;
        int unpaidCount = 0;
        for (Rent rent : rentList) {
            if (rent.isPaid())
                paidCount++;
            else
                unpaidCount++;
        }

        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        Font titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Font bodyFont = new Font(Font.FontFamily.HELVETICA, 11);

        document.add(new Paragraph("Rent Status Report", titleFont));
        document.add(new Paragraph("Year: " + year + " | Month: " + month + "\n\n"));

        document.add(new Paragraph("Paid Rents: " + paidCount));
        document.add(new Paragraph("Unpaid Rents: " + unpaidCount + "\n\n"));

        // ================= PAID RENTS TABLE =================
        document.add(new Paragraph("Paid Rents:\n", headerFont));
        PdfPTable paidTable = new PdfPTable(5); // 5 columns
        paidTable.setWidthPercentage(100);
        paidTable.setSpacingBefore(10f);
        paidTable.setSpacingAfter(10f);

        addTableHeader(paidTable, new String[] { "TNo", "Phone", "Month", "Paid", "Payment Method" }, headerFont);

        for (Rent rent : rentList.stream().filter(Rent::isPaid).toList()) {
            paidTable.addCell(new PdfPCell(new Phrase(String.valueOf(rent.getTno()), bodyFont)));
            paidTable.addCell(new PdfPCell(new Phrase(rent.getPhone(), bodyFont)));
            paidTable.addCell(new PdfPCell(new Phrase(rent.getMonth().toString(), bodyFont)));
            paidTable.addCell(new PdfPCell(new Phrase("Yes", bodyFont)));
            paidTable.addCell(new PdfPCell(new Phrase(rent.getPmethod(), bodyFont)));
        }
        document.add(paidTable);

        // ================= UNPAID RENTS TABLE =================
        document.add(new Paragraph("Unpaid Rents:\n", headerFont));
        PdfPTable unpaidTable = new PdfPTable(4); // 4 columns
        unpaidTable.setWidthPercentage(100);
        unpaidTable.setSpacingBefore(10f);
        unpaidTable.setSpacingAfter(10f);

        addTableHeader(unpaidTable, new String[] { "TNo", "Phone", "Month", "Due Date" }, headerFont);

        for (Rent rent : rentList.stream().filter(r -> !r.isPaid()).toList()) {
            unpaidTable.addCell(new PdfPCell(new Phrase(String.valueOf(rent.getTno()), bodyFont)));
            unpaidTable.addCell(new PdfPCell(new Phrase(rent.getPhone(), bodyFont)));
            unpaidTable.addCell(new PdfPCell(new Phrase(rent.getMonth().toString(), bodyFont)));
            unpaidTable.addCell(new PdfPCell(new Phrase(rent.getDueDate().toString(), bodyFont)));
        }
        document.add(unpaidTable);

        document.close();
    }

    private void addTableHeader(PdfPTable table, String[] headers, Font font) {
        for (String header : headers) {
            PdfPCell headerCell = new PdfPCell();
            headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            headerCell.setBorderWidth(1);
            headerCell.setPhrase(new Phrase(header, font));
            table.addCell(headerCell);
        }
    }

}
