package YazGel1.StajTakip.core.utilites.pdfHelper;

import YazGel1.StajTakip.entities.concretes.Yetki;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PatternColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class YetkiListPdfHelper {
    static String[] HEADERs = { "Yetki No","Yetki Adı" };

    private List<Yetki> yetkiList;

    public YetkiListPdfHelper(List<Yetki> yetkiList){
        this.yetkiList = yetkiList;
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException{
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(105);
        table.setWidths(new float[]{ 2f, 2f });
        table.setSpacingBefore(15);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(PatternColor.WHITE);
        cell.setPadding(3);

        Font font = FontFactory.getFont(FontFactory.HELVETICA,12,BaseColor.BLUE);

        for (int col = 0; col < HEADERs.length; col++){
            cell.setPhrase(new Phrase(HEADERs[col], font));
            table.addCell(cell);
        }

        for (Yetki yetki : yetkiList){
            table.addCell(String.valueOf(yetki.getYetkiId()));
            table.addCell(yetki.getYetkiAdi());
        }

        document.add(table);
        document.addTitle("YetkiPDF");

        document.close();
    }
}
