import java.util.*;

public class StockTradingPlatform {

    static class Stock {
        String name;
        double price;

        Stock(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Stock> market = new ArrayList<>();
        market.add(new Stock("Apple", 185));
        market.add(new Stock("Google", 2800));
        market.add(new Stock("Tesla", 720));
        market.add(new Stock("Microsoft", 340));

        HashMap<String, Integer> portfolio = new HashMap<>();
        ArrayList<String> history = new ArrayList<>();

        int choice;

        do {
            System.out.println("\n========== STOCK TRADING PLATFORM ==========");
            System.out.println("1. View Stocks");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("\nAvailable Stocks");
                    for (Stock s : market) {
                        System.out.println(s.name + " - ₹" + s.price);
                    }
                    break;

                case 2:
                    System.out.print("Enter Stock Name: ");
                    String buy = sc.nextLine();

                    boolean found = false;
                    for (Stock s : market) {
                        if (s.name.equalsIgnoreCase(buy)) {
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Stock not found!");
                        break;
                    }

                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();

                    portfolio.put(buy, portfolio.getOrDefault(buy, 0) + qty);
                    history.add("BUY : " + buy + " (" + qty + " Shares)");

                    System.out.println("Stock Purchased Successfully.");
                    break;

                case 3:
                    System.out.print("Enter Stock Name: ");
                    String sell = sc.nextLine();

                    if (!portfolio.containsKey(sell)) {
                        System.out.println("You don't own this stock.");
                        break;
                    }

                    System.out.print("Enter Quantity: ");
                    int sellQty = sc.nextInt();

                    int current = portfolio.get(sell);

                    if (sellQty > current) {
                        System.out.println("Not enough shares.");
                    } else {
                        if (sellQty == current)
                            portfolio.remove(sell);
                        else
                            portfolio.put(sell, current - sellQty);

                        history.add("SELL : " + sell + " (" + sellQty + " Shares)");
                        System.out.println("Stock Sold Successfully.");
                    }
                    break;

                case 4:
                    System.out.println("\n----- Portfolio -----");

                    if (portfolio.isEmpty()) {
                        System.out.println("Portfolio is Empty.");
                    } else {
                        for (String stock : portfolio.keySet()) {
                            System.out.println(stock + " : " + portfolio.get(stock) + " Shares");
                        }
                    }
                    break;

                case 5:
                    System.out.println("\n----- Transaction History -----");

                    if (history.isEmpty()) {
                        System.out.println("No Transactions Yet.");
                    } else {
                        for (String h : history) {
                            System.out.println(h);
                        }
                    }
                    break;

                case 6:
                    System.out.println("Thank You for Using Stock Trading Platform!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}