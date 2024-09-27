class Solution {
    public static void main(String[] args) {
        // Sample array (rotated sorted array) to test the pivot search.
        int[] arr = {1,2,3,4,5,6};
        System.out.println();
        
        // Call the searchPivot function and store the result.
        int result = searchPivot(arr);
        
        // Output the result (index of pivot).
        System.out.println(result);
    }

    // Main function to search for the target element in a rotated sorted array.
    public int search(int[] nums, int target) {
        // Find the pivot index (the point where the array is rotated).
        int pivot = searchPivot(nums);
        
        // If no pivot is found, the array is not rotated, perform normal binary search.
        if (pivot == -1) {
            return binarySearch(nums, target, 0, nums.length - 1);
        }

        // If the target is found at the pivot, return the pivot index.
        if (nums[pivot] == target) {
            return pivot;
        }

        // If the target is greater than or equal to the first element, search in the left half (from start to pivot-1).
        if (target >= nums[0]) {
            return binarySearch(nums, target, 0, pivot - 1);
        }

        // Otherwise, search in the right half (from pivot+1 to end).
        return binarySearch(nums, target, pivot + 1, nums.length - 1);
    }

    // Helper function to perform binary search in a sorted array.
    static int binarySearch(int[] arr, int target, int start, int end) {
        while (start <= end) {
            // Calculate mid index to split the array.
            int mid = start + (end - start) / 2;

            // If target is smaller than mid, narrow search to left half.
            if (target < arr[mid]) {
                end = mid - 1;
            }
            // If target is greater than mid, narrow search to right half.
            else if (target > arr[mid]) {
                start = mid + 1;
            } 
            // If target is found, return its index.
            else {
                return mid;
            }
        }
        // Return -1 if target is not found.
        return -1;
    }

    // Function to find the pivot index in a rotated sorted array.
    static int searchPivot(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        // Standard binary search loop.
        while (start <= end) {
            int mid = start + (end - start) / 2;

            // Check if mid is greater than next element (this is the pivot point).
            if (mid < end && arr[mid] > arr[mid + 1]) {
                return mid;
            }

            // Check if mid is smaller than the previous element (this is the pivot point).
            if (mid > start && arr[mid] < arr[mid - 1]) {
                return mid - 1;
            }

            // If the mid element is less than or equal to the start element, 
            // move to the left side (this means the pivot is in the left half).
            if (arr[mid] <= arr[start]) {
                end = mid - 1;
            } 
            // Otherwise, move to the right side (pivot is in the right half).
            else {
                start = mid + 1;
            }
        }

        // Return -1 if no pivot is found (array is not rotated).
        return -1;
    }
}
