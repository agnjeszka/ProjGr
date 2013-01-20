package pg.is.projgr.actions;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Podkategorie")
public class Podkategoria {


	@DatabaseField(generatedId = true)
	int id;
	@DatabaseField(index = true)
	String nazwa;
	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	Kategoria kategoria;
	
	
	public Podkategoria() {}

	public Podkategoria(String nazwa) {
		this.nazwa = nazwa;
	}

	public Podkategoria(String nazwa, Kategoria kategoria) {
		this.id = id;
		this.nazwa = nazwa;
		this.kategoria = kategoria;
	}
	
	public Podkategoria(int id, String nazwa) {
		this.id = id;
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

	public Kategoria getKategoria() {
		return this.kategoria;
	}

	public void setKategoria(Kategoria kategoria) {
		this.kategoria = kategoria;
	}
}
