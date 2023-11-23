import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] sizes = {10, 1000, 10000, 100000, 1000000};
        String[] algorithms =
            {"Insertion Sort", "Quick Sort", "Marge Sort", "Counting Sort", "Radix Sort", "Cocktail Sort"};

        System.out.println("Script 1: Random Large Range");
        runSortingComparison(sizes, algorithms, "Large");

        System.out.println("\nScript 2: Random Small Range");
        runSortingComparison(sizes, algorithms, "Small");

        System.out.println("\nScript 3: Descending Order, Random Large Range");
        runSortingComparison(sizes, algorithms, "Descending, Large");

        System.out.println("\nScript 4: Descending Order, Random Small Range");
        runSortingComparison(sizes, algorithms, "Descending, Small");
    }

    private static void runSortingComparison(int[] sizes, String[] algorithms, String script) {
        SortAlgorithm sortAlgorithm = new SortAlgorithm();
        int[] array = new int[0];
        boolean flag = true;

        System.out.printf("%-20s", "Algorithm");
        for(int size : sizes) {
            System.out.printf("%-15s", "Size " + size);
        }
        System.out.println();

        for(String algorithm : algorithms) {
            System.out.printf("%-20s", algorithm);

            for(int size : sizes) {

                if(script.contains("Small")) {
                    array = generateArray(size, 0, 1000);
                } else {
                    array = generateArray(size, 0, 1000000);
                }

                if(script.contains("Descending")) {
                    reverseArray(array);
                }

                long startTime = System.currentTimeMillis();

                switch (algorithm) {
                    case "Insertion Sort":
                        if(array.length > 100000) {
                            System.out.print("Too many elements, it can take quite a while");
                            flag = false;
                        } else {
                            sortAlgorithm.insertionSort(array);
                        }
                        break;
                    case "Marge Sort":
                        sortAlgorithm.mergeSort(array);
                        break;
                    case "Quick Sort":
                        if(array.length > 100000 && script.contains("Descending")) {
                            System.out.print("The worst case: O(n^2)");
                            flag = false;
                        } else {
                            sortAlgorithm.safeQuickSort(array, 0, array.length - 1);
                        }
                        break;
                    case "Counting Sort":
                        sortAlgorithm.countingSort(array);
                        break;
                    case "Cocktail Sort":
                        if(array.length > 100000) {
                            System.out.print("Too many elements, it can take quite a while");
                            flag = false;
                        } else {
                            sortAlgorithm.cocktailSort(array);
                        }
                        break;
                    case "Radix Sort":
                        sortAlgorithm.radixSort(array);
                    default:
                        break;
                }

                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - startTime;

                if(flag) {
                    System.out.printf("%-15d", elapsedTime);
                } else {
                    flag = true;
                }
            }
            System.out.println();
            Arrays.fill(array, 0);
        }
    }

    private static void reverseArray(int[] array) {
        Arrays.sort(array);

        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            left++;
            right--;
        }
    }

    private static int[] generateArray(int size, int lowerBound, int upperBound) {
        int[] array = new int[size];
        Random random = new Random();

        for(int i = 0; i < size; i++) {
            array[i] = random.nextInt(lowerBound, upperBound);
        }

        return array;
    }


}
