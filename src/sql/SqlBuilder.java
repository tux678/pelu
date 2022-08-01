package sql;

public class SqlBuilder {
	
	private enum Tablas{
		usuarios, turnos;
	}
	private static String sqlInsertIni = "insert into ";
	private static String sqlUpdateIni = "update ";
	private String sqlDeleteIni = "Delete from ";
	private static String[][] tablas = new String[][] {
		{"usuarios","(usuario ,nombre )","(?,?)"},
		{"turnos","(fecha ,cliente ,trabajo ,tiempo , importe)","(?,?,?,?,?)",
			" SET cliente = ?, trabajo = ?, tiempo = ?, importe = ? where id = ?"," where id = ?"}};
	private StringBuilder sqlInsert;
	private StringBuilder sqlUpdate;
	private StringBuilder sqlDelete;
	private Tablas nombreCorto;

	public void crearSqlInsert(String tabla) {
		setNombreCorto(tabla);
		sqlInsert = new StringBuilder(sqlInsertIni);
		sqlInsert.append(tabla);
		sqlInsert.append(tablas[nombreCorto.ordinal()][1]);
		sqlInsert.append(" values ");
		sqlInsert.append(tablas[nombreCorto.ordinal()][2]);
		
	}
	
	public void crearSqlUpdate(String tabla) {
		setNombreCorto(tabla);
		sqlUpdate = new StringBuilder(sqlUpdateIni);
		sqlUpdate.append(tabla);
		sqlUpdate.append(tablas[nombreCorto.ordinal()][3]);
	}
	
	private void setNombreCorto(String tabla) {
		// TODO Auto-generated method stub
		String[] t = tabla.split("_");
		nombreCorto = Tablas.valueOf(t[t.length-1]);
	}

	public void agregarParametros(int cantidad) {
		for(int i = 0; i < cantidad; i++) {
			sqlInsert.append(", ");
			sqlInsert.append(tablas[nombreCorto.ordinal()][1]);
		}
	}
	public String getSqlStringInsert() {
		return sqlInsert.toString();
	}
	public String getSqlStringUpdate() {
		return sqlUpdate.toString();
	}

	public void crearSqlDelete(String tabla) {
		setNombreCorto(tabla);
		this.sqlDelete = new StringBuilder(sqlDeleteIni ); 
		sqlDelete.append(tabla);
		sqlDelete.append(tablas[nombreCorto.ordinal()][4]);
	}

	public String getSqlStringDelete() {
		// TODO Auto-generated method stub
		return sqlDelete.toString();
	}
	
}
