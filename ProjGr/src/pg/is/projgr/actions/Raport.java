package pg.is.projgr.actions;

import java.util.Date;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Raporty")
public class Raport {

	@DatabaseField(generatedId = true)
	int id;
	@DatabaseField
	int miesiac;
	@DatabaseField
	int rok;
	@DatabaseField
	float budzet;
	@ForeignCollectionField(eager = true)
	ForeignCollection<Wydatek> wydatki;
	@DatabaseField
	float oszczednosci;
	@DatabaseField
	float laczneWydatki;

	public Raport() {
	}

	public Raport(int miesiac, int rok, float budzet, float oszczednosci, float laczneWydatki) {

		this.miesiac = miesiac;
		this.rok = rok;
		this.oszczednosci = oszczednosci;
		this.laczneWydatki = laczneWydatki;
		this.budzet = budzet;
	}


	public Raport(int id, float budzet, float oszczednosci) {

		this.id = id;
		this.budzet = budzet;
		this.oszczednosci = oszczednosci;
	}

	public int getID() {
		return this.id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getMiesiac() {
		return this.miesiac;
	}

	public void setMiesiac(int miesiac) {
		this.miesiac = miesiac;
	}

	public int getRok() {
		return this.rok;
	}

	public void setRok(int rok) {
		this.rok = rok;
	}

	public float getBudzet() {
		return this.budzet;
	}

	public void setBudzet(float budzet) {
		this.budzet = budzet;
	}

	public ForeignCollection<Wydatek> getWydatki() {
		return this.wydatki;
	}

	public void addWydatek(Wydatek wydatek) {
		this.wydatki.add(wydatek);
	}

	public float getOszczednosci() {
		return this.oszczednosci;
	}

	public void setOszczednosci(float oszczednosci) {
		this.oszczednosci = oszczednosci;
	}

	public float getLaczneWydatki() {
		return this.laczneWydatki;
	}

	public void setLacznewydatki(float laczneWydatki) {
		this.laczneWydatki = laczneWydatki;
	}
	
	public void odejmijWydatek(float wydatek){
		this.laczneWydatki += wydatek;
		this.oszczednosci -= wydatek;
	}

}
