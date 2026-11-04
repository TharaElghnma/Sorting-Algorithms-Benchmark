/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sorting;



import java.util.Random;
import java.util.Arrays;

public class Sorting {

    static Random random = new Random();

    // =========================================
    // 1. ARRAY GENERATION
    // =========================================

    // Generate random array
    static int[] Randomarr(int n) {

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(999999) + 1;
        }

        return arr;
    }

    // Generate reverse sorted array
    static int[] ReverseSortedarr(int[] sorted) {

        int n = sorted.length;
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sorted[n - 1 - i];
        }

        return arr;
    }

    // Generate partially sorted array
    static int[] PartiallySortedarr(int[] sorted) {

        int[] arr = sorted.clone();

        // Approximately 20% random swaps
        int swaps = arr.length / 5;

        for (int i = 0; i < swaps; i++) {

            int a = random.nextInt(arr.length);
            int b = random.nextInt(arr.length);

            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }

        return arr;
    }

    // =========================================
    // 2. SELECTION SORT
    // =========================================

    static void selectionSort(int[] arr) {

        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < n; j++) {

                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    // =========================================
    // 3. INSERTION SORT
    // =========================================

    static void insertionSort(int[] arr) {

        int n = arr.length;

        for (int i = 1; i < n; i++) {

            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {

                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    // =========================================
    // 4. BUBBLE SORT
    // =========================================

    static void bubbleSort(int[] arr) {

        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - 1 - i; j++) {

                if (arr[j] > arr[j + 1]) {

                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // =========================================
    // 5. MERGE SORT
    // =========================================

    static void mergeSort(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }

        int middle = left + (right - left) / 2;

        mergeSort(arr, left, middle);
        mergeSort(arr, middle + 1, right);

        merge(arr, left, middle, right);
    }

    static void merge(int[] arr, int left, int middle, int right) {

        int[] temp = new int[right - left + 1];

        int i = left;
        int j = middle + 1;
        int k = 0;

        while (i <= middle && j <= right) {

            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= middle) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    // =========================================
    // 6. QUICK SORT
    // =========================================

    static void quickSort(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }

        int pivotIndex = partition(arr, left, right);

        quickSort(arr, left, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, right);
    }

    static int partition(int[] arr, int left, int right) {

        // Select a random pivot
        int randomIndex = left + random.nextInt(right - left + 1);

        int temp = arr[randomIndex];
        arr[randomIndex] = arr[right];
        arr[right] = temp;

        int pivot = arr[right];

        int i = left - 1;

        for (int j = left; j < right; j++) {

            if (arr[j] <= pivot) {

                i++;

                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;

        return i + 1;
    }

    // =========================================
    // 7. RADIX SORT
    // =========================================

    static void radixSort(int[] arr) {

        if (arr.length == 0) {
            return;
        }

        int max = arr[0];

        // Find maximum value
        for (int i = 1; i < arr.length; i++) {

            if (arr[i] < 0) {
                throw new IllegalArgumentException(
                    "Radix Sort requires non-negative integers."
                );
            }

            if (arr[i] > max) {
                max = arr[i];
            }
        }

        if (max < 0) {
            throw new IllegalArgumentException(
                "Radix Sort requires non-negative integers."
            );
        }

        // Process each digit
        for (long place = 1; max / place > 0; place *= 10) {

            int[] output = new int[arr.length];
            int[] count = new int[10];

            // Count digit occurrences
            for (int i = 0; i < arr.length; i++) {

                int digit = (int) ((arr[i] / place) % 10);

                count[digit]++;
            }

            // Calculate cumulative count
            for (int i = 1; i < 10; i++) {

                count[i] += count[i - 1];
            }

            // Build output array (stable sorting)
            for (int i = arr.length - 1; i >= 0; i--) {

                int digit = (int) ((arr[i] / place) % 10);

                output[count[digit] - 1] = arr[i];

                count[digit]--;
            }

            // Copy sorted values back
            System.arraycopy(output, 0, arr, 0, arr.length);
        }
    }

    // =========================================
    // 8. SORTING VALIDATION
    // =========================================

    static boolean isSorted(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {

            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }

        return true;
    }

    // =========================================
    // 9. ALGORITHM SELECTION
    // =========================================

    static void sortArray(int[] arr, String algorithm) {

        switch (algorithm) {

            case "Selection":
                selectionSort(arr);
                break;

            case "Insertion":
                insertionSort(arr);
                break;

            case "Bubble":
                bubbleSort(arr);
                break;

            case "Merge":
                mergeSort(arr, 0, arr.length - 1);
                break;

            case "Quick":
                quickSort(arr, 0, arr.length - 1);
                break;

            case "Radix":
                radixSort(arr);
                break;

            default:
                throw new IllegalArgumentException(
                    "Unknown sorting algorithm: " + algorithm
                );
        }
    }

    // =========================================
    // 10. PERFORMANCE MEASUREMENT
    // =========================================

    static long measureTime(int[] original, String algorithm) {

        int warmupRuns = 3;
        int repetitions = 7;

        // Warm-up runs
        for (int i = 0; i < warmupRuns; i++) {

            int[] arr = original.clone();

            sortArray(arr, algorithm);
        }

        long[] times = new long[repetitions];

        // Repeated measurements
        for (int i = 0; i < repetitions; i++) {

            int[] arr = original.clone();

            long start = System.nanoTime();

            sortArray(arr, algorithm);

            long end = System.nanoTime();

            // Verify sorting result
            if (!isSorted(arr)) {
                throw new IllegalStateException(
                    algorithm + " failed to sort the array."
                );
            }

            times[i] = end - start;
        }

        // Sort measurement times
        Arrays.sort(times);

        // Return median execution time
        return times[repetitions / 2];
    }

    // =========================================
    // 11. MAIN METHOD
    // =========================================

    public static void main(String[] args) {

        int n = 1000;

        // Generate one random dataset
        int[] randomArray = Randomarr(n);

        // Create a sorted version of the same values
        int[] sortedArray = randomArray.clone();

        Arrays.sort(sortedArray);

        // Generate other test cases from the same values
        int[] reverseArray = ReverseSortedarr(sortedArray);

        int[] partialArray = PartiallySortedarr(sortedArray);

        // Sorting algorithms
        String[] algorithms = {
            "Selection",
            "Insertion",
            "Bubble",
            "Merge",
            "Quick",
            "Radix"
        };

        // Test cases
        int[][] cases = {
            randomArray,
            reverseArray,
            partialArray,
            sortedArray
        };

        String[] caseNames = {
            "Random",
            "Reverse",
            "Partial",
            "Sorted"
        };

        // Print table header
        System.out.println("\nSorting Algorithms Performance");
        System.out.println("Array Size: " + n);
        System.out.println("Execution Time: Nanoseconds\n");

        System.out.printf("%-12s", "Algorithm");

        for (String name : caseNames) {
            System.out.printf("%18s", name);
        }

        System.out.println();

        // Print separator
        for (int i = 0; i < 84; i++) {
            System.out.print("-");
        }

        System.out.println();

        // Measure each algorithm
        for (String algorithm : algorithms) {

            System.out.printf("%-12s", algorithm);

            for (int[] data : cases) {

                long time = measureTime(data, algorithm);

                System.out.printf("%18d", time);
            }

            System.out.println();
        }

        System.out.println("\nAll algorithms completed successfully.");
    }
}
