package YazGel1.StajTakip.core.utilites.pdfHelper;

import YazGel1.StajTakip.entities.concretes.StjTur;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PatternColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StjTurListPdfHelper {
    static String[] HEADERs = { "Staj Türü No","Staj Türü Adı" };

    private List<StjTur> stjTurList;

    public StjTurListPdfHelper(List<StjTur> stjTurList){
        this.stjTurList = stjTurList;
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

        for (StjTur stjTur : stjTurList){
            table.addCell(String.valueOf(stjTur.getStjTurId()));
            table.addCell(stjTur.getStjTurAd());
        }

        document.add(table);
        document.addTitle("StajDurumPDF");

        document.close();
    }
}
