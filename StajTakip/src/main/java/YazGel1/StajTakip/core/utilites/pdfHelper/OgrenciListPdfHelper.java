package YazGel1.StajTakip.core.utilites.pdfHelper;

import YazGel1.StajTakip.entities.concretes.Ogrenci;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PatternColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class OgrenciListPdfHelper {
    static String[] HEADERs = { "Öğrenci No","Ad","Soyad","Okul","Fakülte","Bölüm","Sınıf","Email","Şifre","Telefon" };
    ByteBuffer byteBuffer = UTF_8.encode(String.valueOf(HEADERs[0]));

    //static String[] HEADERs = new String(ptext, UTF_8);
    private List<Ogrenci> ogrenciList;

    public OgrenciListPdfHelper(List<Ogrenci> ogrenciList){
        this.ogrenciList = ogrenciList;
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException{
        Document document = new Document(PageSize.A3);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        PdfPTable table = new PdfPTable(10);
        table.setWidthPercentage(105);
        table.setWidths(new float[]{ 2f, 1f, 1.2f, 1.2f, 1.5f, 1.2f, 1.2f, 1.2f, 1.2f, 1.5f });
        table.setSpacingBefore(15);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(PatternColor.WHITE);
        cell.setPadding(3);

        Font font = FontFactory.getFont(FontFactory.HELVETICA,12,BaseColor.BLUE);

        for (int col = 0; col < HEADERs.length; col++){
            cell.setPhrase(new Phrase(HEADERs[col], font));
            table.addCell(cell);
        }

        for (Ogrenci ogrenci : ogrenciList){
            table.addCell(String.valueOf(ogrenci.getOgrNo()));
            table.addCell(ogrenci.getOgrAd());
            table.addCell(ogrenci.getOgrSoyad());
            table.addCell(ogrenci.getOgrOkul());
            table.addCell(ogrenci.getOgrFakulte());
            table.addCell(ogrenci.getOgrBolum());
            table.addCell(ogrenci.getOgrSinif());
            table.addCell(ogrenci.getOgrEmail());
            table.addCell(ogrenci.getOgrSifre());
            table.addCell(ogrenci.getOgrTel());
        }
        document.add(table);
        document.addTitle("Öğrenci PDF");

        document.close();
    }
}
