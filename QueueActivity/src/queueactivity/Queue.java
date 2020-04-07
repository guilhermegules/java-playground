package queueactivity;

/**
 *
 * @author Guilherme Gules
 */
public class Queue {
    private char[] queue;
    private int start;
    private int end;
    private final int MAX = 7;

    public Queue() {
        this.queue = new char[MAX*10];
        this.start = 0;
        this.end = 0;
    }
    
    public boolean isFull() {
        return (this.end-this.start) == MAX;
    }
    
    public boolean isEmpty() {
        return this.end == 0;
    }
    
    public char enqueue(char letter) {
        if(isFull()) return ' ';
        this.queue[end++] = letter;
        return letter;
    }
    
    public char dequeue() {
        if(isEmpty()) return ' ';
        try {
            return this.queue[start];
        } finally { 
            this.start++;   
        }
    }

    @Override
    public String toString() {
        String queueText = "";
        for (int i = start; i < end; i++) {
            queueText += this.queue[i];
        }
        return queueText;
    }
}
