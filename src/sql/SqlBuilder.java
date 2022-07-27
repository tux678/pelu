package sql;

public class SqlBuilder {
	
	private enum Tablas{
		turnos, usuarios;
	}
	private static String sqlInsertIni = "insert into ";
	private static String sqlUpdateIni = "update ";
	private static String[][] tablas = new String[][] {
		{"usuarios","(usuario ,nombre )","(?,?)"},
		{"turnos","(fecha ,cliente ,trabajo ,tiempo , importe)","(?,?,?,?,?)",
			" SET cliente = ?, trabajo = ?, tiempo = ?, importe = ? where id = ?"}};
	private StringBuilder sqlInsert;
	private StringBuilder sqlUpdate;
	private int indiceTablaActual;

	public void crearSqlInsert(String tabla) {
		sqlInsert = new StringBuilder(sqlInsertIni);
		String tablaNombre[] = tabla.split("_");
		indiceTablaActual = tablaNombre.length-1;
		sqlInsert.append(tabla);
		sqlInsert.append(tablas[tablaNombre.length-1][1]);
		sqlInsert.append(" values ");
		sqlInsert.append(tablas[tablaNombre.length-1][2]);
		
	}
	
	public void crearSqlUpdate(String tabla) {
		sqlUpdate = new StringBuilder(sqlUpdateIni);
		String tablaNombre[] = tabla.split("_");
		indiceTablaActual = tablaNombre.length-1;
		sqlUpdate.append(tabla);
		sqlUpdate.append(tablas[tablaNombre.length-1][3]);
	}
	
	public void agregarParametros(int cantidad) {
		for(int i = 0; i < cantidad; i++) {
			sqlInsert.append(", ");
			sqlInsert.append(tablas[indiceTablaActual][1]);
		}
	}
	public String getSqlStringInsert() {
		return sqlInsert.toString();
	}
	public String getSqlStringUpdate() {
		return sqlUpdate.toString();
	}

}
