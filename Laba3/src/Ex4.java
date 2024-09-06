public class Ex4 {
    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        T max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Integer[] intArray = {1,5,2,8,3};
        System.out.println("Max number(int): " + findMax(intArray));

        Double[] doubleArray = {1.5,3.14,2.7,0.8,1.1};
        System.out.println("Max number(double): " + findMax(doubleArray));

        Character[] charArray ={'a','z','m','b','g'};
        System.out.println("Max character(char): " + findMax(charArray));

        String[] strArray = {"apple","pear","banana","orange"};
        System.out.println("Max string(String): " + findMax(strArray));
    }
}

