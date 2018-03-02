import java.util.NoSuchElementException;

public class QueueArray<T>{

    private T[] arr;

    private int total, first, next;

    public QueueArray()
    {
        arr = (T[]) new Object[3];
    }

    private void resize(int capacity)
    {
        T[] tmp = (T[]) new Object[capacity];
        
        for(int i = 0; i < total; i++){
        	tmp[i] = arr[(first + i) % arr.length];//Adjusts the elements so the first elemnt is @ index 0 and so on.
        }
        	first = 0;// first is now @ index 0
        	next = total;// next is after the length of the new array
        	arr = tmp;
    }

    public void enqueue(T ele)
    {
        if(total >= arr.length)resize(arr.length * 2);
        arr[next++] = ele;
        if(next == arr.length)next = 0;
        total++;
    }

    public T dequeue()
    {
    	if(total == 0) throw new NoSuchElementException();
    	T ret = arr[first++];
    	total--;
    	if(first == arr.length)first = 0;
    	if(total > 0 && total <= arr.length/4)resize(arr.length / 2);
    	return ret;
    	
      
    }

    @Override
    public String toString()
    {
        return java.util.Arrays.toString(arr);
    }
    
    public static void main(String args[]){
    	QueueArray<Integer> q = new QueueArray<>();
    	int k = 5;
    	
    	while(k > 0){
    		q.enqueue(k);
    		if(k == 3)System.out.println(q.dequeue());
    		k--;
    	}
    	
    	try{
    		while(true){
    			System.out.println(q.dequeue());
    		}
    	}catch(Exception e){
    		
    	}
    }
}