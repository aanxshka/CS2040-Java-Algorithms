
import java.util.*;
import java.util.stream.Collectors;


public class CardTrade {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //no. of cards Anthony has
        int m = sc.nextInt(); //no. of card types
        int k = sc.nextInt(); //no. of combos to achieve

        int[] cards = new int[n];

        for (int i = 0; i < n; i++) {
            cards[i] = sc.nextInt();
        }

        int[] counts = new int[m];
        for(int i = 0; i < n; i++) {
            counts[cards[i] - 1] += 1; //add occurence of each card type in Anthony's deck 
        }

        List<Card> cardList = new ArrayList<>();
        
        int num = 1;
        for(int i : counts) {  //list of cards 
            int buy = sc.nextInt();
            int sell = sc.nextInt();
            int count = i;            
            Card c = new Card(num, count, buy, sell);
            cardList.add(c);
            num +=1;
        }

        List<Card> combos = cardList.stream().filter(c -> c.count >= 2).collect(Collectors.toList());
        int a = combos.size();

        List<Card> singles = cardList.stream().filter(c -> c.count < 2).collect(Collectors.toList());
        int b = singles.size();

        long profit = 0;
        Comparator<Card> sellComp = Comparator.comparing(Card::getSell).reversed();
        Comparator<Card> buyComp = Comparator.comparing(Card::getBuy);

        if (a == k) {
            for (Card c : singles) {
                profit += c.getSell();
            }
        } else {
            Collections.sort(singles, buyComp); 
            int case1 = 0;
            int case2 = 0;
            
            //Case 1 if all combos are kept and other cards are bought to form remaining combos 
            for (int i = 0; i < k-a; i++) {
                case1 -= singles.get(i).getBuy();

            }
            for (int i = k-a; i < b; i++) {
                case1 += singles.get(i).getSell();

            }

            //Case 2 if 1 or more combos are sold + other cards are bought 
            for (Card c : combos) {
                case2 += c.getSell();
            }

            for (int i = 0; i < k; i++) {
                case2 -= singles.get(i).getBuy();
            }
            
            

            if (case1 > case2) {
                profit = case1;
            } else {
                profit = case2;
            }

        }
        
        System.out.println(profit);

        sc.close();
    }

    static class Card { 
        private final int card;
        private final int count;
        private final int buy;
        private final int sell;

        Card(int card, int count, int buy, int sell) {
            this.card = card;
            this.buy = buy;
            this.count = count;
            this.sell = sell;

        }

        int getSell() {
            return this.sell * this.count;
        }

        int getBuy() {
            return this.buy;
        }

    }

}


