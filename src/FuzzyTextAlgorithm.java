import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class FuzzyTextAlgorithm {

    public static void main(String[] args) {

        Map<String, Integer> costMapping = new HashMap<>();
        Map<Integer, String> findWord = new HashMap<>();

        String input = "c"; // source

        LinkedList<String> storage = new LinkedList<String>();
        storage.add("congrats");
        storage.add("hondar");
        storage.add("hyundai");
        storage.add("idk");
        storage.add("helper");
        storage.add("bye");
        storage.add("hi");
        storage.add("storage");
        storage.add("team");
        storage.add("honda");
        storage.add("john");
        storage.add("airplane");
        storage.add("software");
        storage.add("engineering");
        storage.add("alcohol");

        //LinkedList<Integer> costs = new LinkedList<Integer>();
        double maxPercentage = 0.0;
        int index = 0;

        for (int i = 0; i < storage.size(); i++) {

            int maxLength = getLongestWordLength(input, storage.get(i));

            myThread thread = new myThread(input, storage.get(i));
            thread.start();

            synchronized (thread) {
                try {
                    thread.wait();
                }

                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int cost = thread.getCalculatedCost();

            try {
                thread.join();
            }

            catch (InterruptedException e) {
                System.out.println("Not good");
            }

            //System.out.println(cost);

            double accuracyPercentage = (1.0 - ((double)cost / (double)maxLength)) * 100.0;

            costMapping.put(storage.get(i), cost);
            findWord.put(i, storage.get(i));

            if (accuracyPercentage > maxPercentage) {
                maxPercentage = accuracyPercentage;
                index = i;
            }

        }

        String formatPercentage = String.format("%.3f", maxPercentage);
        System.out.println("Input Word: " + input);
        System.out.println("Most Probable Word: " + findWord.get(index) + " (" + formatPercentage + "% Accuracy)");
        System.out.println(" ");
        System.out.println("Done");

    }

    public static int getLongestWordLength(String s1, String s2) {

        if (s1.length() >= s2.length()) {
            return s1.length();
        }

        else {
            return s2.length();
        }
    }



}
