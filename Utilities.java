public class Utilities {
    /**
     * Returns all the permutations of a given string
     * @param initial String which contains all the elements for the permutation. Don't include multi-digit or repeating values.
     * @return An array of strings with all possible permutations
     */
    public static String[] permutationsWithRecursion(String initial){
        int length = initial.length();

        if(length == 2){
            return new String[]{
                    initial,
                    "" + initial.charAt(1)+initial.charAt(0)
            };
        }

        String[] res = new String[factoriel(length)];
        int lastFilledIndex = -1;

        for(int i = 0; i < length; i++){
            char firstChar = initial.charAt(i);
            String subInitial = initial.substring(0, i) + initial.substring(i+1, length);

            String[] permutations = permutationsWithRecursion(subInitial);
            for (String permutation : permutations) {
                res[++lastFilledIndex] = firstChar + permutation;
            }
        }
        return res;
    }


    /**
     * Finds the index of the given permutation, having 012345678 as the first one
     * @param newString String : the permutation whose index is required
     * @return int : required index
     */
    public static int permutationIndex(String newString){
        String initialString = "012345678";
        int index = 1;
        int length = initialString.length();
        for(int i = 0; i < length -1; i++){
            char currentChar = newString.charAt(i);
            int m = initialString.indexOf(currentChar);
            index += m*factoriel(length-i-1);
            initialString = initialString.substring(0 ,m)+initialString.substring(m+1);
        }
        return index-1;
    }

    /**
     * Factoriel
     * @param n
     * @return n!
     */
    public static int factoriel(int n){
        int product = 1;
        for(int i = 1; i <= n; i++)
            product *= i;
        return product;
    }
}
