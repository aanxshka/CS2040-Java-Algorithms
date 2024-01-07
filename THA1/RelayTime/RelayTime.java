
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class RelayTime {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        List<Runner> runners = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String ln = sc.nextLine();
            String[] parts = ln.split(" ");
            String name = parts[0];
            double time1 = Double.parseDouble(parts[1]);
            double time2 = Double.parseDouble(parts[2]);
            Runner r = new Runner(name, time1, time2);
            runners.add(r);
        }

        Collections.sort(runners, (r1,r2) -> Double.compare(r1.leg2, r2.leg2));
        
        List<Runner> top4 = runners.subList(0,4);
        
        // Case 1: top4 leg 2 makeup form the team  
        double case1Time = 100000.0;
        Runner first1 = top4.get(0);
        double tempcase1Time = 0.0;

        for (Runner r : top4) {
            tempcase1Time = r.leg1;

            for (Runner a : top4) {
                if (a.name != r.name) {
                    tempcase1Time += a.leg2;
                }
            }

            if (tempcase1Time < case1Time) {
                case1Time = tempcase1Time;
                first1 = r;
            }

        }

        //Case 2 top3 leg2 and another runner make up team: 
        List<Runner> top3 = top4.subList(0,3);
        double case2Time = 100000.0;
        Runner first2 = first1;
        if (n > 4) {
            List<Runner> rem = runners.subList(4,n);
            Collections.sort(rem, (r1,r2) -> Double.compare(r1.leg1, r2.leg1));
            first2 = rem.get(0);
            case2Time = first2.leg1;
            for (Runner r : top3) {
                case2Time += r.leg2;
            }
        }

        double finalTime = 0.0;
        Runner first = first1;
        List<Runner> finalTeam = new ArrayList<>();
        if (case1Time < case2Time) { //if case1 is better 
            finalTime = case1Time;
            for (Runner r : top4) {
                if (r != first) {
                    finalTeam.add(r);
                }
            }
        } else { //if case2 is better 
            finalTime = case2Time;
            first = first2;
            finalTeam = top3;
        }
    

        System.out.println(finalTime);
        System.out.println(first.name);
        for (Runner r : finalTeam) {
            System.out.println(r.name);
        }

        sc.close();
    }

    static class Runner {
        String name;
        double leg1;
        double leg2;

        Runner(String name, double leg1, double leg2) {
            this.name = name;
            this.leg1 = leg1;
            this.leg2 = leg2;
        }

        public String toString() {
            return this.name;
        }

    }

    
}
