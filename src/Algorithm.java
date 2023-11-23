public interface Algorithm {
    void insertionSort(int[] array);
    void mergeSort(int[] array);
    void safeQuickSort(int[] array, int low, int high);
    void countingSort(int[] array);
    void radixSort(int[] array);
    void cocktailSort(int[] array);
}
