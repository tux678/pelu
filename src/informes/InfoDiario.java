package informes;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;

import conexion.Conexion;
import utilidades.Util;

public class InfoDiario  {
	
	public InfoDiario(String fecha) throws ClassNotFoundException, SQLException, IOException {
		Conexion cnx;
		PreparedStatement ps;
		String sql;
		Calendar f =Calendar.getInstance(new Locale("es", "ES"));
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

		if (Util.esFechaValida(fecha)) {
			try {
				f.setTime(formatoFecha.parse(fecha));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		f.set(Calendar.HOUR_OF_DAY, 0);
		f.set(Calendar.MINUTE, 0);
		cnx = Conexion.getConexion();
		cnx.conectar();
		sql = "Select fecha, cliente, trabajo, tiempo, usuarios.usuario, importe, sum(importe) as total from turnos, usuarios where fecha between ? and ? and hecho and usuarios.id = turnos.idProfesional";
		ps = cnx.getConect().prepareStatement(sql);
		ps.setTimestamp(1, new java.sql.Timestamp(f.getTimeInMillis()));
		f.set(Calendar.HOUR_OF_DAY, 23);
		f.set(Calendar.MINUTE, 59);
		ps.setTimestamp(2, new java.sql.Timestamp(f.getTimeInMillis()));
//		System.out.println(ps.toString());
		ResultSet rs = ps.executeQuery();
		
		File file = new File("infoDiario.pdf");
		PdfWriter pdfWriter = new PdfWriter(file);
		
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		
		Document infoDiario = new Document(pdfDocument);
		infoDiario.setMargins(120, 36, 120, 36);
		
		PdfFont fuenteTitulo = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
		Text titulo = new Text("Jara").setFont(fuenteTitulo);
		titulo.setFontSize(16);
		Text subTitulo = new Text("Movimientos del " + formatoFecha.format( f.getTime())).setFont(fuenteTitulo);
		subTitulo.setFontSize(13);
		Paragraph cabecera = new Paragraph().add(titulo);
		cabecera.setTextAlignment(TextAlignment.CENTER);
		Paragraph siguienteCabecera = new Paragraph().add(subTitulo);
		siguienteCabecera.setTextAlignment(TextAlignment.CENTER);
		infoDiario.add(cabecera);
		infoDiario.add(siguienteCabecera);
		
		Table diario = new Table(rs.getMetaData().getColumnCount()-1);
		diario.setWidth(infoDiario.getPageEffectiveArea(pdfDocument.getDefaultPageSize()).getWidth());
		for(int i=1; i <= rs.getMetaData().getColumnCount()-1; i++) {
			Paragraph p  = new Paragraph(rs.getMetaData().getColumnLabel(i));
			p.setFontSize(11);
			p.setTextAlignment(TextAlignment.CENTER);
			diario.addHeaderCell(new Cell().add(p));
		}
		Cell total = null;
		if(rs.next()) {
			
			for(int i=1; i <= rs.getMetaData().getColumnCount()-1; i++) {
				Paragraph p  = new Paragraph(String.valueOf( rs.getObject(i)));
				p.setFontSize(10);
				p.setTextAlignment(TextAlignment.RIGHT);
				diario.addCell(new Cell().add(p));
			}
			Paragraph q  = new Paragraph(String.valueOf( rs.getObject(rs.getMetaData().getColumnCount())));
			q.setFontSize(11);
			q.setTextAlignment(TextAlignment.RIGHT);
			total = new Cell();
			total.add(q);
		}
		while(rs.next()) {
			for(int i=1; i <= rs.getMetaData().getColumnCount()-1; i++) {
				Paragraph p  = new Paragraph(String.valueOf( rs.getObject(i)));
				p.setFontSize(10);
				p.setTextAlignment(TextAlignment.RIGHT);
				diario.addCell(new Cell().add(p));
			}
		}
		
		agregarFilaTotal(rs.getMetaData().getColumnCount()-1, diario, total);
		infoDiario.add(diario);
		infoDiario.close();
		pdfWriter.close();
		rs.close();
		cnx.desconectar();
		
	}

	private void agregarFilaTotal(int columnas, Table diario, Cell total) throws SQLException {
		// TODO Auto-generated method stub
		
		Paragraph p  = new Paragraph("Total");
		p.setFontSize(11);
		p.setTextAlignment(TextAlignment.CENTER);
		diario.addCell(new Cell().add(p));
		
		for(int i=2; i <= columnas-1; i++) {
			diario.addCell(new Cell());
		}
		diario.addCell(total);
		
	}


}
