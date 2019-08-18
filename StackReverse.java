import java.util.*;
class Stack {
    private int top;
    private int capacity;
    private char[] array;
	private String output;

    public Stack(int capacity) {
        this.capacity = capacity;
        this.array = new char[capacity];
        this.top = -1;
    }

    public boolean isFull() {
        return this.top == this.capacity - 1;
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    public void push(char value) {
        if(!this.isFull()) {
            this.array[++this.top] = value;
        }
    }

    public char pop() {
        return this.isEmpty()?'\u0000':this.array[this.top--];
    }

    public static String reverse(String str) {
		String output;
		int stackSize = str.length();
		Stack theStack = new Stack(stackSize);
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			theStack.push(ch);
		}
		output = "";
		while (!theStack.isEmpty()) {
			char ch = theStack.pop();
			output = output + ch;
		}
		System.out.println("Reverse of stack:");
		return output;
    }


}
public class StackReverse {
  public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      System.out.println("enter stack elements:");
      String str = sc.nextLine();
      System.out.println(Stack.reverse(str));
  }
}