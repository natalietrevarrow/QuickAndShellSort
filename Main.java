import java.util.Random;

public class Main {
    
    public static void main(String[] args) {
        int size = 10000;
        int gapindex = 2;
        int[] randomArray = RandomizedArray(size, 10000, 0);
        int[] bubbleClone = CloneArray(randomArray);
        long bstartTime = System.currentTimeMillis();
        BubbleSort(bubbleClone);
        long bendTime = System.currentTimeMillis();
        long bduration = bendTime - bstartTime;
        System.out.println("Sorting a random array with size " + size + " took bubble sort " + bduration + "ms to complete");
        int[] selectionClone = CloneArray(randomArray);
        long sstartTime = System.currentTimeMillis();
        SelectionSort(selectionClone);
        long sendTime = System.currentTimeMillis();
        long sduration = sendTime - sstartTime;
        System.out.println("Sorting a random array with size " + size + " took selection sort " + sduration + "ms to complete");
        int[] insertionClone = CloneArray(randomArray);
        long istartTime = System.currentTimeMillis();
        InsertionSortAlone(insertionClone);
        long iendTime = System.currentTimeMillis();
        long iduration = iendTime - istartTime;
        System.out.println("Sorting a random array with size " + size + " took selection sort " + iduration + "ms to complete");
        int[] shellClone = CloneArray(randomArray);
        long hstartTime = System.currentTimeMillis();
        ShellSort(shellClone, size, gapindex);
        long hendTime = System.currentTimeMillis();
        long hduration = hendTime - hstartTime;
        System.out.println("Sorting a random array with size " + size + " took shell sort " + hduration + "ms to complete");
        int[] QuickClone = CloneArray(randomArray);
        long qstartTime = System.currentTimeMillis();
        QuickSort(QuickClone, 0, size-1);
        long qendTime = System.currentTimeMillis();
        long qduration = qendTime - qstartTime;
        System.out.println("Sorting a random array with size " + size + " took quick sort " + qduration + "ms to complete");
    }

    public static int[] RandomizedArray(int size, int max, int min) {
        if (max-min + 1 >= size) {
            int[] sourceArray = new int[max - min+1];
            for(int i = 0; i < (max-min + 1); i++) {
                sourceArray[i] = min + i;
            }
            shuffle(sourceArray);
            int[] result = new int[size];
            for(int i = 0; i < size; i++) {
                result[i] = sourceArray[i];
            }
            return result;
        } else {
            return null;
        }
    }

    public static void shuffle(int[] array) {
        for(int i = 0; i < array.length / 2; i++) {
            Random r = new Random();
            int pos1 = r.nextInt(array.length - 1);
            int pos2 = r.nextInt(array.length - 1);
            int temp = array[pos1];
            array[pos1] = array[pos2];
            array[pos2] = temp;
        }
    }

    public static int[] BubbleSort(int[] arr) {
        int temp;
        for(int j = 0; j < arr.length; j++) {
            for(int i = 0; i < arr.length-j-1; i++) {
                if(arr[i] > arr[i+1]) {
                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
            }
        }

        /*
        System.out.println("BubbleSort array:");
        for(int i = 0; i < arr.length; i++) {
            if(i == arr.length-1) {
                System.out.println(arr[i]);
            }
            else {
                System.out.print(arr[i] + ", ");
            }
        }
        */

        return arr;
    }

    public static int[] SelectionSort(int[] arr) {
        int temp = 0;
        for(int i = 0; i < arr.length-1; ++i) {
            int smallest = i;
            for(int j = i+1; j < arr.length; ++j) {
                if(arr[j] < arr[smallest]) {
                    smallest = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;
        }

        /*
        System.out.println("SelectionSort array:");
        for(int i = 0; i < arr.length; i++) {
            if(i == arr.length-1) {
                System.out.println(arr[i]);
            }
            else {
                System.out.print(arr[i] + ", ");
            }
        }
        */
        return arr;
    }

    public static int[] CloneArray(int[] arr) {
        int[] result = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    public static int[] InsertionSortAlone(int[] arr) {
        int i = 0;
        int j = 0;
        int temp = 0;
        for(i = 1; i < arr.length; i++) {
            j = i;
            while(j > 0 && arr[j] < arr[j-1]) {
                temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
                j--;
            }
        }
        
        /* 
        System.out.println("InsertionSort array:");
        for(int k = 0; k < arr.length; k++) {
            if(k == arr.length-1) {
                System.out.println(arr[k]);
            }
            else {
                System.out.print(arr[k] + ", ");
            }
        }
        */
        return arr;
    }

    public static int[] InsertionSort(int[] nums, int numsSize, int startIndex, int gap) {
        int i = 0;
        int j = 0;
        int temp = 0;
        for(i = startIndex + gap; i < numsSize; i = i + gap) {
            j = i;
            while(j - gap >= startIndex && nums[j] < nums[j - gap]) {
                temp = nums[j];
                nums[j] = nums[j - gap];
                nums[j - gap] = temp;
                j = j - gap;
            }
        }
        return nums;
    }

    public static int[] ShellSort(int[] nums, int numsSize, double gapIndex) {
        //convert gap index into 2^n-1 format
        gapIndex = Math.pow(2, gapIndex - 1);

        //find size of array of gap values dividing by 2 until gap index is 1
        int j = 0;
        for(double i = gapIndex; i > 0; i = i/2) {
            j = j + 1;
        }

        //fill array of gap values
        double gapValues[] = new double[j];
        for(int i = 0; i > 0; i++) {
            gapValues[i] = gapIndex;
            gapIndex = gapIndex/2;
        }
        
        /*
        System.out.println("ShellSort array:");
        
        for(int i = 0; i > 0; i++) {
            if(i == nums.length-1) {
                System.out.println(nums[i]);
            }
            else {
                System.out.print(nums[i] + ", ");
            }
        }
            */
        return nums;
    }

    public static int Partition(int[] nums, int startIndex, int endIndex) {
        int midpoint = startIndex + (endIndex - startIndex)/2;
        int pivot = nums[midpoint];

        int low = startIndex;
        int high = endIndex;

        boolean done = false;
        while(!done) {
            while(nums[low] < pivot) {
                low = low + 1;
            }

            while(pivot < nums[high]) {
                high = high - 1;
            }

            if(low >= high) {
                done = true;
            }
            else {
                int temp = nums[low];
                nums[low] = nums[high];
                nums[high] = temp;
                low++;
                high--;
            }
        }
        return high;
    }

    public static void QuickSort(int[] nums, int startIndex, int endIndex) {
        if(endIndex <= startIndex) {
            return;
        }
        int high = Partition(nums, startIndex, endIndex);

        QuickSort(nums, startIndex, high);

        QuickSort(nums, high + 1, endIndex);
    }

        
}
    

 
