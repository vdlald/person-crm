package com.vladislav.crm.functions;

import com.vladislav.crm.entities.Lead;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.data.util.Pair;

import java.util.Collection;
import java.util.function.Function;

public interface CreateExcelFromLeadsFunction extends Function<Collection<Lead>, Pair<HSSFWorkbook, HSSFSheet>> {
}
