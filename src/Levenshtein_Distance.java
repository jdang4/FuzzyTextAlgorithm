public class Levenshtein_Distance {
    String input;
    String target;

    String s1;
    String s2;

    char[][] distanceArray;

    public Levenshtein_Distance(String input, String target) {
        this.input = input;
        this.target = target;
    }

    public void Initialize_Levenshtein_Distance() {

        s1 = addingSpaces(input, 1);
        s2 = addingSpaces(target, 2);

        int num_of_rows = s1.length();
        int num_of_columns = s2.length();

        distanceArray = new char[num_of_rows][num_of_columns];

        for (int i = 0; i < num_of_rows; i++) {

            for (int j = 0; j < num_of_columns; j++) {

                if (i == 0 && j > 0) {
                    distanceArray[i][j] = (char)(48 + j);
                }

                else if (i > 0 && j == 0) {
                    distanceArray[i][j] = (char)(48 + i);
                }

                else {
                    distanceArray[i][j] = '0';
                }
            }
        }

    }

    public void printArray() {

        // printing out the labels (horizontally first)
        for (int i = 0; i < s2.length(); i++) {
            System.out.print(s2.charAt(i) + " | ");
        }

        System.out.println(" ");

        for (int j = 0; j < s2.length(); j++) {
            System.out.print("----");
        }

        System.out.println(" ");

        // printing out the label for s1 along with the actual Levenshtein Distance Array
        for (int i = 0; i < s1.length(); i++) {


            for (int j = 0; j < s2.length(); j++) {

                // this prints out the label of string s1
                if (j == 0) {
                    System.out.print(s1.charAt(i) + " | ");
                }

                else {
                    int adjustJ = j - 1;
                    System.out.print(distanceArray[i][adjustJ] + " | ");
                }
            }

            System.out.println(" ");

            for (int j = 0; j < s2.length(); j++) {
                System.out.print("----");
            }

            System.out.println(" ");
        }
    }

    public String addingSpaces(String s, int numOfSpaces) {

        String result = "";
        for (int i = 0; i < numOfSpaces; i++) {
            result = result + " ";
        }

        result = result + s;  // adding spaces to front of strings

        return result;
    }

    public int getMinimumValue(int[] costs) {

        int minimumCost = costs[0];

        for (int i = 1; i < costs.length; i++) {
            if (costs[i] < minimumCost) {
                minimumCost = costs[i];
            }
        }

        return minimumCost;

    }

    public static int convertCharToInt(char c) {

        int integerValue = ((int)c) - 48;

        return integerValue;
    }


    public int LevenshteinDistance_Algorithm() {

        // need to call a function to remove spaces
        char[] s1Array = input.toCharArray();
        char[] s2Array = target.toCharArray();

        int stringIndex_s1 = 0;
        int stringIndex_s2 = 0;

        int deletion, insertion, substitution;
        int minimumCost = 0;

        for (int i = 1; i <= input.length() || i < stringIndex_s1; i++, stringIndex_s1++) {

            for (int j = 1; j <= target.length() || j < stringIndex_s2; j++, stringIndex_s2++) {

                int substitutionCost = 1;

                if (s1Array[stringIndex_s1] == s2Array[stringIndex_s2]) {
                    substitutionCost = 0;
                }

                else {
                    substitutionCost = 1;

                }

                deletion = convertCharToInt(distanceArray[i - 1][j]) + 1;
                insertion = convertCharToInt(distanceArray[i][j - 1]) + 1;
                substitution = convertCharToInt(distanceArray[i - 1][j - 1]) + substitutionCost;

                int[] costArray = {deletion, insertion, substitution};

                minimumCost = getMinimumValue(costArray);

                char updatedCost = (char) (48 + minimumCost);

                distanceArray[i][j] = updatedCost;

            }

            stringIndex_s2 = 0;
        }

        return minimumCost;
    }
}
