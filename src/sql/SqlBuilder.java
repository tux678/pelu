package sql;

public class SqlBuilder {
	
	private enum Tablas{
		usuarios, turnos, trabajos;
	}
	private static String sqlInsertIni = "insert into ";
	private static String sqlUpdateIni = "update ";
	private String sqlDeleteIni = "Delete from ";
	private static String[][] tablas = new String[][] {
		{"usuarios","(usuario ,nombre )","(?,?)"},
		{"turnos","(idProfesional, fecha ,cliente ,trabajo ,tiempo , importe, hecho)","(?,?,?,?,?, ?, ?)",
			" SET cliente = ?, trabajo = ?, tiempo = ?, importe = ?, hecho= ? where id = ?"," where id = ?"},
		{"trabajos","(idProfesional, fecha ,cliente ,trabajo ,tiempo , importe)","(?,?,?,?,?, ?)",
				" SET cliente = ?, trabajo = ?, tiempo = ?, importe = ? where id = ?"," where id = ?"}};
	private StringBuilder sqlInsert;
	private StringBuilder sqlUpdate;
	private StringBuilder sqlDelete;

	public void crearSqlInsert(String tabla) {
		sqlInsert = new StringBuilder(sqlInsertIni);
		sqlInsert.append(tabla);
		sqlInsert.append(tablas[Tablas.valueOf(tabla).ordinal()][1]);
		sqlInsert.append(" values ");
		sqlInsert.append(tablas[Tablas.valueOf(tabla).ordinal()][2]);
		
	}
	
	public void crearSqlUpdate(String tabla) {
		sqlUpdate = new StringBuilder(sqlUpdateIni);
		sqlUpdate.append(tabla);
		sqlUpdate.append(tablas[Tablas.valueOf(tabla).ordinal()][3]);
	}
	
	public void agregarParametros(int cantidad) {
		for(int i = 0; i < cantidad; i++) {
			sqlInsert.append(", ");
//			sqlInsert.append(tablas[Tablas.valueOf(tabla).ordinal()][1]);
		}
	}
	public String getSqlStringInsert() {
		return sqlInsert.toString();
	}
	public String getSqlStringUpdate() {
		return sqlUpdate.toString();
	}

	public void crearSqlDelete(String tabla) {
		this.sqlDelete = new StringBuilder(sqlDeleteIni ); 
		sqlDelete.append(tabla);
		sqlDelete.append(tablas[Tablas.valueOf(tabla).ordinal()][4]);
	}

	public String getSqlStringDelete() {
		// TODO Auto-generated method stub
		return sqlDelete.toString();
	}
	
}
