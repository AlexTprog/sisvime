package com.sisvime.app.view.pdf;

import com.sisvime.app.models.entity.Personal;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;
import java.util.Map;

@Component("/views/personal/listarper.pdf")
public class ListarPersonalPdf extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
        List<Personal> listadopersonal = (List<Personal>) model.get("personales");

        /*FUENTES, TAMAÑOS Y COLORES PARA CADA SECCION*/
        Font fuenteTitulo = FontFactory.getFont("Helvetica", 15, Color.black);
        Font fuenteTituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6, Color.BLACK);
        Font fuenteDataCeldas = FontFactory.getFont(FontFactory.COURIER, 7, Color.BLACK);

        document.setPageSize(PageSize.LETTER.rotate());
        document.setMargins(-40, -40, 50, 50);
        document.open();
        PdfPCell celda = null;

        /*TABLA PARA EL TITULO DEL PDF*/
        PdfPTable tablaTitulo = new PdfPTable(1);

        celda = new PdfPCell(new Phrase("LISTADO GENERAL DEL PERSONAL MEDICO FAP", fuenteTitulo));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(57, 108, 183));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(13);

        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(30);

        /*TABLA PARA MOSTRAR EL LISTADO DEL PERSONAL*/
        PdfPTable tablaPersonal = new PdfPTable(12);
        tablaPersonal.setWidths(new float[]{1.2f, 1.7f, 2f, 2f, 1.4f, 2.2f, 2f, 1.9f, 2f, 2f, 2.5f, 2.5f});

        celda = new PdfPCell(new Phrase("NSA", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaPersonal.addCell(celda);

        celda = new PdfPCell(new Phrase("NOMBRES", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaPersonal.addCell(celda);

        celda = new PdfPCell(new Phrase("APELLIDO PATERNO", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaPersonal.addCell(celda);

        celda = new PdfPCell(new Phrase("APELLIDO MATERNO", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaPersonal.addCell(celda);

        celda = new PdfPCell(new Phrase("SEXO", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaPersonal.addCell(celda);

        celda = new PdfPCell(new Phrase("FECHA DE NACIMIENTO", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaPersonal.addCell(celda);

        celda = new PdfPCell(new Phrase("ESTADO CIVIL", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaPersonal.addCell(celda);

        celda = new PdfPCell(new Phrase("CORREO", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaPersonal.addCell(celda);

        celda = new PdfPCell(new Phrase("CELULAR", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaPersonal.addCell(celda);

        celda = new PdfPCell(new Phrase("TELEFONO", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaPersonal.addCell(celda);

        celda = new PdfPCell(new Phrase("DIRECCION", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaPersonal.addCell(celda);

        celda = new PdfPCell(new Phrase("ESPECIALIDAD", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaPersonal.addCell(celda);



        /*BUCLE FOR, MOSTRAR TODOS LOS DATOS DE LOS CLIENTES*/

        for (Personal personal : listadopersonal) {
            celda = new PdfPCell(new Phrase(personal.getId().toString(), fuenteDataCeldas));
            celda.setPadding(5);
            tablaPersonal.addCell(celda);

            celda = new PdfPCell(new Phrase(personal.getNombre(), fuenteDataCeldas));
            celda.setPadding(5);
            tablaPersonal.addCell(celda);

            celda = new PdfPCell(new Phrase(personal.getApellidopat(), fuenteDataCeldas));
            celda.setPadding(5);
            tablaPersonal.addCell(celda);

            celda = new PdfPCell(new Phrase(personal.getApellidomat(), fuenteDataCeldas));
            celda.setPadding(5);
            tablaPersonal.addCell(celda);

            celda = new PdfPCell(new Phrase(personal.getSexo(), fuenteDataCeldas));
            celda.setPadding(5);
            tablaPersonal.addCell(celda);

            celda = new PdfPCell(new Phrase(personal.getFechanacimiento().toString(), fuenteDataCeldas));
            celda.setPadding(5);
            tablaPersonal.addCell(celda);

            celda = new PdfPCell(new Phrase(personal.getEstadocivil(), fuenteDataCeldas));
            celda.setPadding(5);
            tablaPersonal.addCell(celda);

            celda = new PdfPCell(new Phrase(personal.getCorreo(), fuenteDataCeldas));
            celda.setPadding(5);
            tablaPersonal.addCell(celda);

            celda = new PdfPCell(new Phrase(personal.getCelular().toString(), fuenteDataCeldas));
            celda.setPadding(5);
            tablaPersonal.addCell(celda);

            celda = new PdfPCell(new Phrase(personal.getTelefonofijo().toString(), fuenteDataCeldas));
            celda.setPadding(5);
            tablaPersonal.addCell(celda);

            celda = new PdfPCell(new Phrase(personal.getDireccion(), fuenteDataCeldas));
            celda.setPadding(5);
            tablaPersonal.addCell(celda);

            celda = new PdfPCell(new Phrase(personal.getEspec().getNomespecialidad(), fuenteDataCeldas));
            celda.setPadding(5);
            tablaPersonal.addCell(celda);
        }
		
		
		
		
		
		
		
		
		/*
		listadopersonal.forEach(personal ->{
			
			tablaPersonal.addCell(personal.getId().toString());
			tablaPersonal.addCell(personal.getDni());
			tablaPersonal.addCell(personal.getNombre());
			tablaPersonal.addCell(personal.getApellidopat());
			tablaPersonal.addCell(personal.getApellidomat());
			tablaPersonal.addCell(personal.getGen_id());
			tablaPersonal.addCell(personal.getFechanacimiento().toString());
			tablaPersonal.addCell(personal.getEst_id());
			tablaPersonal.addCell(personal.getCorreo());
			tablaPersonal.addCell(personal.getCelular());
			tablaPersonal.addCell(personal.getTelefonofijo());
			tablaPersonal.addCell(personal.getDireccion());
			tablaPersonal.addCell(personal.getEspec().getNomespecialidad());
			
		}); */

        /*ANEXAMOS  LAS TABLAS AL DOCUMENTO*/
        document.add(tablaTitulo);
        document.add(tablaPersonal);
    }

}
