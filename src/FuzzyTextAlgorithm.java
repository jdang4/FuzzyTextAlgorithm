public class FuzzyTextAlgorithm {

    public static void main(String[] args) {

        String string1 = "hi";
        String string2 = "bye";

        String string1_new = addingSpaces(string1, 1);
        String string2_new = addingSpaces(string2, 2);

        System.out.println("Rows: " + string1_new.length() + " " + string1);
        System.out.println("Columns: " + string2_new.length() + " " + string2);

        char[][]LevenshteinDistanceArray = Initialize_Levenshtein_Distance(string1_new, string2_new);
        printArray(LevenshteinDistanceArray, string1_new, string2_new);

    }

    public static String addingSpaces(String s1, int numOfSpaces) {

        String result = "";
        for (int i = 0; i < numOfSpaces; i++) {
            result = result + " ";
        }

        result = result + s1;  // adding spaces to front of strings

        return result;
    }

    public static void printArray(char[][] array, String s1, String s2) {

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
                    System.out.print(array[i][adjustJ] + " | ");
                }
            }

            System.out.println(" ");

            for (int j = 0; j < s2.length(); j++) {
                System.out.print("----");
            }

            System.out.println(" ");
        }
    }

    public static char[][] Initialize_Levenshtein_Distance(String s1, String s2) {

        int num_of_rows = s1.length();
        int num_of_columns = s2.length();

        char[][] LevenshteinDistanceArray = new char[num_of_rows][num_of_columns];

        for (int i = 0; i < num_of_rows; i++) {

            for (int j = 0; j < num_of_columns; j++) {

                if (i == 0 && j > 0) {
                    LevenshteinDistanceArray[i][j] = (char)(48 + j);
                }

                else if (i > 0 && j == 0) {
                    LevenshteinDistanceArray[i][j] = (char)(48 + i);
                }

                else {
                    LevenshteinDistanceArray[i][j] = '0';
                }
            }
        }

        return LevenshteinDistanceArray;
    }

    public static int getMinimumValue(int[] costs) {

        int minimumCost = costs[0];

        for (int i = 1; i <= costs.length; i++) {
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


    public static int LevenshteinDistance_Algorithm(char[][] array, String s1, String s2) {

        // need to call a function to remove spaces
        char[] s1Array = s1.toCharArray();
        char[] s2Array = s2.toCharArray();

        int stringIndex_s1 = 0;
        int stringIndex_s2 = 0;

        int deletion, insertion, substitution, minimumCost;

        for (int i = 1; i <= s1.length() - 1; i++, stringIndex_s1++) {

            for (int j = 1; j <= s2.length() - 1; j++, stringIndex_s2++) {

                int substitutionCost = 1;

                if (s1Array[stringIndex_s1] == s2Array[stringIndex_s2]) {
                    substitutionCost = 0;
                }

                else {
                    substitutionCost = 1;

                }

                deletion = convertCharToInt(array[i - 1][j]) + 1;
                insertion = convertCharToInt(array[i][j - 1]) + 1;
                substitution = convertCharToInt(array[i - 1][j - 1]) + substitutionCost;

                int[] costArray = {deletion, insertion, substitution};

                minimumCost = getMinimumValue(costArray);


            }
        }

    }

}
