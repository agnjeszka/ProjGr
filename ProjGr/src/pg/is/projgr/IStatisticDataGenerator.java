package pg.is.projgr;

public interface IStatisticDataGenerator {

	public int GetRaportsCount();
	//numer ostatniego id raportu (nie jest to konieczna metoda, 
	//ale kod bedzie jasniejszy, jak ja bede miala, zamiast uzywac dlugosci 
	//ponizszych wektorow)
	
	public float[] GetExpensesMonthly();
	//kolejno po raportach od najstarszego do najnowszego wartosci z kolumny wydatków
	
	public float[] GetIncomeMonthly();
	//kolejno po raportach od najstarszego do najnowszego wartosci z kolumny przychodu
	
	public float[] GetSavingsMonthly();
	//kolejno po raportach od najstarszego do najnowszego wartosci z kolumny oszczednosci
	
	public float[] GetIthMonthExpenses(int i);
	//na podstawie tabeli wydatkow, ktore maja pzypisane id ostatniego-i raportu 
	//(czyli dla i=0 mamy akualny/najnowszy raport), zwrocic wartosci z kolumny 
	//koszt (czy jakkolwiek sie ta kolumna nazywa), i w zakresie 
	//od (0) do (liczby raportów-1)
	
	public String[] GetMainCategories();
	//wszystkie glowne kategorie (pewnie cztery czy piêæ)
	
	public String[] GetSubCategories(String category);
	//wszystkie podkategorie danej kategorii
	
	public float[] GetIthMonthExpensesByCategories(int i);
	//dlugosc tego wektora powinna byc taka sama jak tego z metody wyzej 
	//i w takiej samej kolejnosci, z ostatniego-i raportu	
	
	public float[] GetIthMonthExpensesBySubcategories(int i,String category);
	//jak wyzej, tylko na podkategoriach podanej kategorii
}
