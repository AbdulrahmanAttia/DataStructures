import java.util.EmptyStackException;


public class ArrayStack<T>{
	
	T []stack;
	int N = 0;
	public ArrayStack(int size){
		stack = (T[])new Object[size];
	}
	
	public void push(T item){
		if(N >= stack.length)resize(2 * stack.length);
		stack[N++] = item;
	}
	
	public void resize(int newSize){
		T []newStack = (T[])new Object[newSize];
		for(int i = 0; i < N; i++)
			newStack[i] = stack[i];
		stack = newStack;
	}
	
	public T pop()throws EmptyStackException{
		if(N == 0)throw new EmptyStackException();
		T ret = stack[--N];
		if(N != 0 && N <= stack.length / 4)resize(stack.length / 2);
		return ret;
	}
	
	public static void main(String []args){
		ArrayStack<Integer> s = new ArrayStack<>(5);
		
		int k = 1000;
		
		while(k > 0){
			s.push(k);
			k--;
		}
		
		try{
		while(true){
			System.out.println(s.pop());
		}
		}catch(EmptyStackException E){
			System.out.println("Stack is Empty");
		}
	}
}
