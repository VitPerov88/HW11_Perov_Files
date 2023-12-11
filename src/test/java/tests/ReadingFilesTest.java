package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class ReadingFilesTest {
    ClassLoader cl = ReadingFilesTest.class.getClassLoader();

    @Test
    void readingPdfFromZip() throws Exception {
        try (InputStream zipFile = cl.getResourceAsStream("archive.zip");
             ZipInputStream zipInStream = new ZipInputStream(zipFile)) {
            ZipEntry entry;
            while ((entry = zipInStream.getNextEntry()) != null) {
                if (entry.getName().contains(".pdf")) {
                    PDF pdf = new PDF(zipInStream);
                    Assertions.assertEquals("Java - Quick Guide", pdf.title);
                    Assertions.assertEquals(13, pdf.numberOfPages);
                    break;
                }
            }
        }
    }
    @Test
    void readingXlsxFromZip() throws Exception {
        try (InputStream zipFile = cl.getResourceAsStream("archive.zip");
             ZipInputStream zipInStream = new ZipInputStream(zipFile)) {
            ZipEntry entry;
            while ((entry = zipInStream.getNextEntry()) != null) {
                if (entry.getName().contains(".xlsx")) {
                    XLS xls = new XLS(zipInStream);
                    Assertions.assertEquals(xls.excel.getSheetAt(0).getRow(0).getCell(1)
                            .getStringCellValue(), "Коммерческое предложение.");
                    break;
                }
            }
        }
    }
    @Test
    void readingCSVFromZip() throws Exception {
        try (InputStream zipFile = cl.getResourceAsStream("archive.zip");
             ZipInputStream zipInStream = new ZipInputStream(zipFile)) {
            ZipEntry entry;
            while ((entry = zipInStream.getNextEntry()) != null) {
                if (entry.getName().contains(".csv")) {
                    CSVReader csvReader = new CSVReader( new InputStreamReader(zipInStream));
                    List<String[]> csvContent = csvReader.readAll();
                    Assertions.assertArrayEquals(new String[]{"2021","Level 1","AA","Agriculture, Forestry and Fishing","Percentage","H36","Current ratio","Financial ratios","105","ANZSIC06 division A"}, csvContent.get(58));
                    break;
                }
            }
        }
    }
}
