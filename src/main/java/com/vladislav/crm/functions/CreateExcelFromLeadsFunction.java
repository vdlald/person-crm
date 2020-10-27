package com.vladislav.crm.functions;

import com.vladislav.crm.entities.Lead;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.data.util.Pair;

import java.util.Collection;
import java.util.function.Function;

public class CreateExcelFromLeadsFunction implements Function<Collection<Lead>, Pair<HSSFWorkbook, HSSFSheet>> {

    @Override
    public Pair<HSSFWorkbook, HSSFSheet> apply(Collection<Lead> leads) {
        final HSSFWorkbook workbook = new HSSFWorkbook();
        final HSSFSheet sheet = workbook.createSheet("Сделки");

        final HSSFRow rowHead = sheet.createRow(0);
        rowHead.createCell(0).setCellValue("Идентификатор");
        rowHead.createCell(1).setCellValue("Название");
        rowHead.createCell(2).setCellValue("Бюджет");
        rowHead.createCell(3).setCellValue("Статус");

        int i = 0;
        for (Lead lead : leads) {
            i++;
            final HSSFRow row = sheet.createRow(i);
            row.createCell(0).setCellValue(lead.getId());
            row.createCell(1).setCellValue(lead.getName());
            row.createCell(2).setCellValue(lead.getSale().toString());
            row.createCell(3).setCellValue(lead.getStatus().getName());
        }

        return Pair.of(workbook, sheet);
    }
}
