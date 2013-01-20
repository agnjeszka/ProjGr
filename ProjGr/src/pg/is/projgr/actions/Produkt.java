package pg.is.projgr.actions;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "Produkty")
public class Produkt {
	
	@DatabaseField(generatedId = true)
	int id;
	@DatabaseField(index = true)
	String nazwa;
	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	Podkategoria podkategoria;

	public Produkt(){}
	
	public Produkt(int id, String nazwa) {
		this.id = id;
		this.nazwa = nazwa;
	}

	public Produkt(String nazwa, Podkategoria podkategoria) {
		this.podkategoria = podkategoria;
		this.nazwa = nazwa;
	}

	public int getID() {
		return this.id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getNazwa() {
		return this.nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public Podkategoria getPodkategoria() {
		return this.podkategoria;
	}

	public void setPodkategoria(Podkategoria podkategoria) {
		this.podkategoria = podkategoria;
	}

}
