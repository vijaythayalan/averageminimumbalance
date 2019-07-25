package per.amb;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.NavigableMap;
import java.util.TreeMap;

public class AverageMinimumBalance {
	private static final int URBAN_MINIMUM_BALANCE = 10000;
	private static final int SEMI_URBAN_MINIMUM_BALANCE = 5000;
	private static final String SYMBOL = "₹";

	private static void ambCheck(final int minimumBalance, NavigableMap<Integer, Double> amountInDatewise, int startDate) {
		LocalDate currentDate = LocalDate.now();
	
		int currentDay = currentDate.getDayOfMonth();
        

		NumberFormat nf1 = NumberFormat.getCurrencyInstance(new Locale("ta", "IN"));
		System.out.format("Your Account has: %s %s", nf1.format(amountInDatewise.lastEntry().getValue()), SYMBOL);
		System.out.println();
		int monthlyMaintainAmount = minimumBalance * (currentDate.lengthOfMonth() - (startDate - 1));
		System.out.format("Your Account should maintain %s %s amount in this Month.",
				nf1.format(monthlyMaintainAmount), SYMBOL);
		System.out.println();
		double tillYesterdayHolding = 0;
		double lastAmount = 0;

		for (int days = startDate; days < currentDay; days++) {
			if (amountInDatewise.containsKey(days)) {
				lastAmount = amountInDatewise.get(days);
				tillYesterdayHolding += lastAmount; 
			} else {
				tillYesterdayHolding += lastAmount;
			}
		}

		System.out.format("Your Account holding till yesterday %s %s", nf1.format(tillYesterdayHolding), SYMBOL);
		System.out.println();

		if (tillYesterdayHolding > monthlyMaintainAmount) {
			System.out.format("Your Account has maintained sufficient Average Monthly Balance!.");
			System.out.println();
		} else {
			int remainingDate = currentDate.lengthOfMonth() - currentDay + 1;
			double remainingAmount = (monthlyMaintainAmount - tillYesterdayHolding);
			System.out.format("Your Account need to maintain %s amount in %d days ie average of %s %s ", nf1.format(remainingAmount), remainingDate, nf1.format(remainingAmount / remainingDate), SYMBOL);
			System.out.println();
		}
	}

	public static void main(String[] args) {
		NavigableMap<Integer, Double> amountInDatewise = new TreeMap<>();
		amountInDatewise.put(1, 15965.45);
		amountInDatewise.put(2, 12465.45);
		amountInDatewise.put(4, 17465.45);
		amountInDatewise.put(5, 13965.45);
		amountInDatewise.put(11, 13434.45);
		amountInDatewise.put(18, 15434.45);

		ambCheck(URBAN_MINIMUM_BALANCE, amountInDatewise, 1);
		System.out.println();
		amountInDatewise = new TreeMap<>();
		amountInDatewise.put(4, 10000.00);
		amountInDatewise.put(6, 8000.00);
		amountInDatewise.put(8, 6696.56);
		amountInDatewise.put(10, 4743.10);
		amountInDatewise.put(15, 1743.10);
		amountInDatewise.put(17, 1507.10);
		amountInDatewise.put(18, 9507.10);
		
		ambCheck(SEMI_URBAN_MINIMUM_BALANCE, amountInDatewise, 4);
	}
}
