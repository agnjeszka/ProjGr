package pg.is.projgr.actions;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Kategorie")
public class Kategoria {

	@DatabaseField(generatedId = true)
	int id;
	@DatabaseField(index = true)
	String nazwa;
	// klucz obcy
	@ForeignCollectionField(eager = true)
	ForeignCollection<Podkategoria> podkategorie;

	public Kategoria() {

	}

	public Kategoria(String nazwa) {

		this.nazwa = nazwa;
	}

	public Kategoria(int id, String nazwa) {
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

	public ForeignCollection<Podkategoria> getPodkategorie(){
		return this.podkategorie;
	}
}
