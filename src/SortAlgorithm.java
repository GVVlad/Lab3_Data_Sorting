import java.util.Arrays;
import java.util.Stack;

public class SortAlgorithm implements Algorithm {

    public void insertionSort(int[] array) {

        int n = array.length;

        for(int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }

            array[j + 1] = key;
        }

    }

    public void safeQuickSort(int[] array, int low, int high) {
        if(false) {
            iterativeQuickSort(array, low, high);
        } else {
            try {
                quickSort(array, low, high);
            } catch (StackOverflowError ignore) {

            }
        }
    }

    private void iterativeQuickSort(int[] array, int low, int high) {
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);

        while (!stack.isEmpty()) {
            high = stack.pop();
            low = stack.pop();

            if(low < high) {
                int partitionIndex = partition(array, low, high);

                stack.push(low);
                stack.push(partitionIndex - 1);

                stack.push(partitionIndex + 1);
                stack.push(high);
            }
        }
    }

    private void quickSort(int[] array, int low, int high) {

        if(low < high) {
            int partitionIndex = partition(array, low, high);

            quickSort(array, low, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);

        for(int j = low; j < high; j++) {
            if(array[j] <= pivot) {
                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    public void countingSort(int[] array) {
        int max = findMax(array);
        int min = findMin(array);

        int range = max - min + 1;
        int[] count = new int[range];
        int[] output = new int[array.length];

        for(int value : array) {
            count[value - min]++;
        }

        for(int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        for(int i = array.length - 1; i >= 0; i--) {
            output[count[array[i] - min] - 1] = array[i];
            count[array[i] - min]--;
        }

        System.arraycopy(output, 0, array, 0, array.length);
    }


    private int findMax(int[] array) {
        int max = array[0];
        for(int value : array) {
            if(value > max) {
                max = value;
            }
        }
        return max;
    }

    private int findMin(int[] array) {
        int min = array[0];
        for(int value : array) {
            if(value < min) {
                min = value;
            }
        }
        return min;
    }

    public void radixSort(int[] array) {
        int max = findMax(array);

        for(int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(array, exp);
        }
    }

    public void countingSort(int[] array, int exp) {
        int n = array.length;
        int[] output = new int[n];

        int maxDigit = Arrays.stream(array).max().orElse(0);
        int[] count = new int[maxDigit + 1];
        Arrays.fill(count, 0);

        for(int value : array) {
            count[(value / exp) % 10]++;
        }

        for(int i = 1; i <= maxDigit; i++) {
            count[i] += count[i - 1];
        }

        for(int i = n - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, array, 0, n);
    }

    public void cocktailSort(int[] array) {
            boolean swapped;
            do {
                swapped = false;
                for(int i = 0; i <= array.length - 2; i++) {
                    if(array[i] > array[i + 1]) {
                        int temp = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = temp;
                        swapped = true;
                    }
                }

                if(!swapped) {
                    break;
                }

                swapped = false;
                for(int i = array.length - 2; i >= 0; i--) {
                    if(array[i] > array[i + 1]) {
                        int temp = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = temp;
                        swapped = true;
                    }
                }
            } while (swapped);

    }

    public void mergeSort(int[] array) {
        int n = array.length;
        if(n > 1) {
            int mid = n / 2;

            int[] leftArray = Arrays.copyOfRange(array, 0, mid);
            int[] rightArray = Arrays.copyOfRange(array, mid, n);

            mergeSort(leftArray);
            mergeSort(rightArray);

            merge(array, leftArray, rightArray);
        }
    }

    private void merge(int[] result, int[] leftArray, int[] rightArray) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < leftArray.length && j < rightArray.length) {
            if(leftArray[i] <= rightArray[j]) {
                result[k] = leftArray[i];
                i++;
            } else {
                result[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < leftArray.length) {
            result[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightArray.length) {
            result[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
