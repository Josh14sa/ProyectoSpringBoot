package com.cibertec.demo.reportes;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import com.cibertec.demo.modelo.Empleado;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.RGBColor;


import jakarta.servlet.http.HttpServletResponse;

public class PersonasExporterPDF {
	
	private List<Empleado> listaPersonas;

	public PersonasExporterPDF(List<Empleado> listaPersonas) {
		
		this.listaPersonas = listaPersonas;
	}
	
	private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
        PdfPCell celda = new PdfPCell();
        celda.setBackgroundColor(new Color(51, 122, 183)); // Azul oscuro
        celda.setPadding(8);

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(RGBColor.WHITE);

        celda.setPhrase(new Phrase("ID", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Dni", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Cargo", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Nombre", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Apellido", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Direccion", fuente));
        tabla.addCell(celda);
        
        celda.setPhrase(new Phrase("Correo", fuente));
        tabla.addCell(celda);
    }

    private void escribirDatosDeLaTabla(PdfPTable tabla) {
        for (Empleado persona : listaPersonas) {
            tabla.addCell(String.valueOf(persona.getId()));
            tabla.addCell(persona.getDni());
            tabla.addCell(persona.getCargo());
            tabla.addCell(persona.getNombre());
            tabla.addCell(persona.getApellido());
            tabla.addCell(persona.getDireccion());
            tabla.addCell(persona.getCorreo());
        }
    }

    public void exportar(HttpServletResponse response) throws DocumentException, IOException {
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();

        Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuenteTitulo.setColor(new Color(51, 122, 183)); // Azul oscuro
        fuenteTitulo.setSize(24);

        Paragraph titulo = new Paragraph("Lista de Empleados", fuenteTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(20); // Espaciado después del título
        documento.add(titulo);

        PdfPTable tabla = new PdfPTable(7); // Cambiado a 7 para incluir todos los datos
        tabla.setWidthPercentage(100);
        tabla.setSpacingAfter(10);
        tabla.setWidths(new float[]{4f, 10f, 8f, 10f, 10f, 18f, 18f}); // Ajusta los anchos según tus necesidades
        
        
        

        // Resto del código

        escribirCabeceraDeLaTabla(tabla);
        escribirDatosDeLaTabla(tabla);

        documento.add(tabla);
        documento.close();
    }
}