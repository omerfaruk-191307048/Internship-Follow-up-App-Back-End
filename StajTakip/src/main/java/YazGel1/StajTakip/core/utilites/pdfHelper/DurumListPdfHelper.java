package YazGel1.StajTakip.core.utilites.pdfHelper;

import YazGel1.StajTakip.entities.concretes.Durum;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PatternColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DurumListPdfHelper {
    static String[] HEADERs = { "Durum No","Durum Adı" };

    private List<Durum> durumList;

    public DurumListPdfHelper(List<Durum> durumList){
        this.durumList = durumList;
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

        for (Durum durum : durumList){
            table.addCell(String.valueOf(durum.getDurumId()));
            table.addCell(durum.getDurumAdi());
        }

        document.add(table);
        document.addTitle("DurumPDF");

        document.close();
    }
}
