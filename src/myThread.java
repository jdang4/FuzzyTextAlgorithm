import java.util.LinkedList;

public class myThread extends Thread {

    String input;
    String target;
    LinkedList<Integer> output;

    public myThread(String input, String target) {
        this.input = input;
        this.target = target;
        output = new LinkedList<Integer>();
    }

    public void run() {
        try {

            synchronized (this) {
                Levenshtein_Distance obj = new Levenshtein_Distance(input, target);
                obj.Initialize_Levenshtein_Distance();
                int calculatedCost = obj.LevenshteinDistance_Algorithm();
                output.add(calculatedCost);
                notify();
            }

        }

        catch (Exception e) {
            System.out.println("ERROR");
        }
    }

    public int getCalculatedCost() {

        return output.get(0);
    }
}
