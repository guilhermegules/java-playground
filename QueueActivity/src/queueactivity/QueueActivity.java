package queueactivity;

/**
 *
 * @author Guilherme Gules
 */
public class QueueActivity {

    public static void main(String[] args) {
        Queue queue = new Queue();
        
        queue.enqueue('a');
        System.out.println(queue.toString());
        queue.enqueue('b');
        System.out.println(queue.toString());
        queue.enqueue('c');
        System.out.println(queue.toString());
        queue.enqueue('d');
        System.out.println(queue.toString());
        queue.dequeue();
        System.out.println(queue.toString());
        queue.dequeue();
        System.out.println(queue.toString());
        queue.enqueue('f');
        System.out.println(queue.toString());
        queue.dequeue();
        System.out.println(queue.toString());
        queue.enqueue('g');
        System.out.println(queue.toString());
        queue.enqueue(queue.dequeue());
        System.out.println(queue.toString());
        queue.enqueue('h'); // após solução do professor esse comando funciona
        System.out.println(queue.toString());
    }
}
