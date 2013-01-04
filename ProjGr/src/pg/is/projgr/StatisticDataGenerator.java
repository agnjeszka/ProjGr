package pg.is.projgr;

public class StatisticDataGenerator implements IStatisticDataGenerator {

	public int GetRaportsCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public float[] GetExpensesMonthly() {
		// TODO Auto-generated method stub
		return null;
	}

	public float[] GetIncomeMonthly() {
		// TODO Auto-generated method stub
		return null;
	}

	public float[] GetSavingsMonthly() {
		// TODO Auto-generated method stub
		return null;
	}

	public float[] GetIthMonthExpenses(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] GetMainCategories() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String[] GetSubCategories(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	public float[] GetIthMonthExpensesByCategories(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public float[] GetIthMonthExpensesBySubcategories(int i, String category) {
		// TODO Auto-generated method stub
		return null;
	}

	public static int GetMax(float[] array){
		float max = array[0];
		      for (int j = 1; j < array.length; j++) {
		          if (array[j] > max) {
		              max = array[j];
		          }
		      }
		return (int)max;
	}
	
	public static int GetMin(float[] array){
		float min = array[0];
		      for (int j = 1; j < array.length; j++) {
		          if (array[j] < min) {
		              min = array[j];
		          }
		      }
		return (int)min;
	}
}
