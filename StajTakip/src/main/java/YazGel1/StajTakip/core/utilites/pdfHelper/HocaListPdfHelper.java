package YazGel1.StajTakip.core.utilites.pdfHelper;

import YazGel1.StajTakip.entities.concretes.Hoca;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PatternColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HocaListPdfHelper {
    static String[] HEADERs = { "Hoca No","Ad","Soyad","Fakülte","Bölüm","Email","Şifre","Telefon","Yetki" };

    private List<Hoca> hocaList;

    public HocaListPdfHelper(List<Hoca> hocaList){
        this.hocaList = hocaList;
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException{
        Document document = new Document(PageSize.A3);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(105);
        table.setWidths(new float[]{ 2f, 1f, 1.2f, 1.5f, 1.2f, 1.2f, 1.2f, 1.5f, 1.2f });
        table.setSpacingBefore(15);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(PatternColor.WHITE);
        cell.setPadding(3);

        Font font = FontFactory.getFont(FontFactory.HELVETICA,12,BaseColor.BLUE);

        for (int col = 0; col < HEADERs.length; col++){
            cell.setPhrase(new Phrase(HEADERs[col], font));
            table.addCell(cell);
        }

        for (Hoca hoca : hocaList){
            table.addCell(String.valueOf(hoca.getHocaId()));
            table.addCell(hoca.getHocaAd());
            table.addCell(hoca.getHocaSoyad());
            table.addCell(hoca.getHocaFakulte());
            table.addCell(hoca.getHocaBolum());
            table.addCell(hoca.getHocaEmail());
            table.addCell(hoca.getHocaSifre());
            table.addCell(hoca.getHocaTel());
            table.addCell(hoca.getYetki().getYetkiAdi());
        }

        document.add(table);
        document.addTitle("HocaPDF");

        document.close();
    }
}
