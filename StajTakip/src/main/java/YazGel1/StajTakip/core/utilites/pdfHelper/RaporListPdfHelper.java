package YazGel1.StajTakip.core.utilites.pdfHelper;

import YazGel1.StajTakip.entities.concretes.Rapor;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RaporListPdfHelper {
    static String[] HEADERs = { "Rapor No","Öğrenci Adı","Öğrenci Soyadı","Hoca Adı","Hoca Soyadı","Durum","Not","Staj Türü","Staj Başlangıç","Staj Bitiş","Staj Gün","Staj Firma","Staj Dönem","Rapor Dosya Yolu" };

    private List<Rapor> raporList;

    public RaporListPdfHelper(List<Rapor> raporList){
        this.raporList = raporList;
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException{
        Document document = new Document(PageSize.A2);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        PdfPTable table = new PdfPTable(14);
        table.setWidthPercentage(105);
        table.setWidths(new float[]{ 2f, 2.1f, 2.5f, 1.6f, 2.1f, 1.5f, 1.5f, 2.1f, 2.5f, 2.2f, 2f, 2.2f, 2.2f, 2.5f});
        table.setSpacingBefore(15);

        PdfPCell cell = new PdfPCell();
        cell.setPadding(3);

        Font font = FontFactory.getFont(FontFactory.HELVETICA,12, BaseColor.BLUE);

        for (int col = 0; col < HEADERs.length; col++){
            cell.setPhrase(new Phrase(HEADERs[col], font));
            table.addCell(cell);
        }

        for (Rapor rapor : raporList){
            table.addCell(String.valueOf(rapor.getRaporId()));
            table.addCell(rapor.getOgrenci().getOgrAd());
            table.addCell(rapor.getOgrenci().getOgrSoyad());
            table.addCell(rapor.getHoca().getHocaAd());
            table.addCell(rapor.getHoca().getHocaSoyad());
            table.addCell(rapor.getDurum().getDurumAdi());
            table.addCell(rapor.getNott().getNottAdi());
            table.addCell(rapor.getStjTur().getStjTurAd());
            table.addCell(rapor.getStjBas());
            table.addCell(rapor.getStjBit());
            table.addCell(rapor.getStjGun());
            table.addCell(rapor.getStjFirma());
            table.addCell(rapor.getStjDonem());
            table.addCell(rapor.getRaporPath());
        }

        document.add(table);
        document.addTitle("RaporPDF");

        document.close();
    }
}
