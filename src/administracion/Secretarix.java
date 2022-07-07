package administracion;

public class Secretarix extends Usuario {

	@Override
	public void setMenu(Peluqueria w) {
		// TODO Auto-generated method stub
		w.getMenuBar().getMenu(1).getItem(0).setEnabled(false);
		w.getMenuBar().getMenu(1).getItem(1).setEnabled(false);
	}

}
