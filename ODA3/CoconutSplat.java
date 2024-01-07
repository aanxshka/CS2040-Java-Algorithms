/* PSEUDOCODE 
 * 
 * Create a Player class 
 * 
 * In main:
 * 
 * Use scanner to take in syllables and player input 
 * Create a linked list of players 
 * Iterate through the list, changing state for each player accordingly
 *  - the next player will be determined by (id + syl - 1) % num
 *  - if player has two fists (state 2) then add another player that represents 
 * this players second hand in order to account for "more" players 
 * 
 * Iterate until only one player is left
 * Return player id + 1
 * 
 */

import java.util.*;

public class CoconutSplat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int syl = sc.nextInt();
        int num = sc.nextInt();

        List<Player> players = new LinkedList<Player>();
        for (int i = 0; i < num; i++) {
            players.add(new Player(i, 1));
        }

        int next = 0;
        while (players.size() > 1) {
            next = (next + syl - 1) % players.size();
            Player nextPlayer = players.get(next);
            if (nextPlayer.getState() == 1) {
                nextPlayer.changeState();
                Player second = new Player(nextPlayer.getID(), 2);
                players.add(next + 1, second);
            } else if (nextPlayer.getState() == 2) {
                nextPlayer.changeState();
                next += 1;
            } else {
                players.remove(nextPlayer);
            }
        }

        System.out.println(players.get(0).getID() + 1);



    }

    static class Player {
        private int id;
        private int state;

        Player(int id, int state) {
            this.id = id;
            this.state = state;
        }

        int getID() {
            return this.id;
        }

        int getState() {
            return this.state;
        }

        void changeState() {
            this.state += 1;
        }

        boolean isCoconut() {
            return this.state == 1;
        }

        boolean isHalfCoconut() {
            return this.state == 2;
        }

    }
}
