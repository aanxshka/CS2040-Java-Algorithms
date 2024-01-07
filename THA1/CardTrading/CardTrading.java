
import java.util.*;

public class CardTrading {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //no. of cards Anthony has
        int m = sc.nextInt(); //no. of card types
        int k = sc.nextInt(); //no. of combos to achieve

        int[] cards = new int[n];

        for (int i = 0; i < n; i++) {
            cards[i] = sc.nextInt();
        }

        int[] count = new int[m];
        for(int i = 0; i < n; i++) {
            count[cards[i] - 1] += 1; //add occurence of each card type in Anthony's deck 
        }

        int[] buyPrices = new int[n]; //list of buy prices for all cards
        int[] sellPrices = new int[n]; //list of sell prices for all cards

        for (int i = 0; i < m; i++) {
            buyPrices[i] = sc.nextInt();
            sellPrices[i] = sc.nextInt();
        }


        List<Price> priceList = new ArrayList<>();

        for(int i = 0; i < m; i++) {
            int qty = count[i];
            int buyamt = Math.max(0,(2- qty)) * buyPrices[i];
            int sellamt = qty * sellPrices[i];
            priceList.add(new Price(i+1, buyamt, sellamt));
        }

        Collections.sort(priceList, new PriceComp());

        long profit = 0;

        for (int i = 0; i < k; i++) {
            Price next = priceList.get(i);
            profit -= next.buy;
            System.out.println(next.buy);
        }

        for (int i = k; i < m; i++) {
            Price next = priceList.get(i);
            profit += next.sell;
            System.out.println(next.sell);
        }

        System.out.println(profit);

        sc.close();
    }

    static class Price { 
        private final int card;
        private final int buy;
        private final int sell;

        Price(int card, int buy, int sell) {
            this.card = card;
            this.buy = buy;
            this.sell = sell;

        }

    }

    static class PriceComp implements Comparator<Price> {
        @Override
        public int compare(Price p1, Price p2) {
            int p1cost = p1.sell - p1.buy;
            int p2cost = p2.sell - p2.buy;
            if (p1cost > p2cost) {
                return 1;
            } else if (p1cost < p2cost) {
                return -1;
            } else if (p1.buy > p2.buy) {
                return 1;
            } else if (p1.buy < p2.buy) {
                return -1;
            } else {
                return 0;
            }
        }
    }

}
