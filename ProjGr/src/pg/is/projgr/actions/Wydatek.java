package pg.is.projgr.actions;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Wydatki")
public class Wydatek {

	@DatabaseField(generatedId = true)
	int id;
	@DatabaseField(index = true)
	String nazwa;
	@DatabaseField
	float cena;
	@DatabaseField
	Date data;
	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	Produkt produkt;
	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	Raport raport;

	public Wydatek() {}

	public Wydatek(String nazwa, float cena, Date data) {

		this.nazwa = nazwa;
		this.cena = cena;
		this.data = data;
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

	
	public Produkt getProdukt() {
		return this.produkt;
	}

	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}
	
	public Raport getIDraport() {
		return this.raport;
	}

	public void setIDraport(Raport raport) {
		this.raport = raport;
	}
	
	public float getCena() {
		return this.cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
