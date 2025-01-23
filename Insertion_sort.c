#include<stdio.h>

int main() 

{
    int arra[10], i, j, n, array_key;

    // Input the number of values in the array
    printf("Input no. of values in the array: \n");
    scanf("%d", &n);

    // Input array values
    printf("Input %d array value(s): \n", n);
    for (i = 0; i < n; i++)
        scanf("%d", &arra[i]);

    /* Insertion Sort */
    for (i = 1; i < n; i++) {
        array_key = arra[i];
        j = i - 1;

        // Move elements greater than array_key to one position ahead of their current position
        while (j >= 0 && arra[j] > array_key) {
            arra[j + 1] = arra[j];
            j = j - 1;
        }

        // Insert array_key at its correct position
        arra[j + 1] = array_key;
    }

    // Print the sorted array
    printf("Sorted Array: \n");
    for (i = 0; i < n; i++)
        printf("%d  \n", arra[i]);

    return 0;
}
