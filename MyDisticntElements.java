import java.util.*;
public class MyDisticntElements {
	public static void printDistinctElements(int[] arr) {
		for (int i=0;i<arr.length;i++) {
			boolean isDistinct = false;
			for (int j=0;j<i;j++) {
				if(arr[i] == arr[j]) {
					isDistinct = true;
					break;
				}
			}
			if(!isDistinct) {
				System.out.print(arr[i]+" ");
			}
		}
	}
	public static void main(String a[]) {
	    Scanner sc=new Scanner(System.in);
	    System.out.println("Enter size of array:");
	    int len=sc.nextInt();
		System.out.println("enter values");
	      int [] myArray = new int[len];
		    for(int i=0; i<myArray.length; i++ ) {
         myArray[i] = sc.nextInt();
      }
	  System.out.println("output:");
		MyDisticntElements.printDistinctElements(myArray);
	}
}