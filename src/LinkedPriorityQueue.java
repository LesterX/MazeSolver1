/**
 * ??
 *
 * @author Yimin Xu 250876566 CS1027
 * @author 
 * @version
 */

public class LinkedPriorityQueue<T> implements PriorityQueueADT<T>
{
   private int count;
   private PriorityNode<T> front, rear;
   
   /**
    * Adds the specified element to the rear of this queue.
    *
    * @param element  the element to be added to the rear of this queue
    */
   public void enqueue (T element)
   {
	   PriorityNode<T> node = new PriorityNode<T>(element);

      if (isEmpty())
         front = node;
      else
         rear.setNext (node);

      rear = node;
      count++;
   }
   
   /**
    * Adds the element with priority into the queue
    * 
    * @param the element to be added and the priority
    */
   public void enqueue (T element, double p)
   {
	   PriorityNode<T> node = new PriorityNode<T>(element,p);
	   PriorityNode<T> current,next;
	   if (isEmpty())
	   {
		   front = node;
		   count ++;
	   }else if (front.getPriority() > p)
	   {
		   node.setNext(front);
		   front = node;
		   count ++;
	   }else if (front.getNext() == null)
	   {
		   front.setNext(node);
		   count ++; 
	   }else
	   {
		   current = front;
		   next = front.getNext();
		   while (current != null)
		   {
			   if (next == null)
			   {
				   current.setNext(node);
			   	   count ++;
			   	   break;
			   }
			   else if (next.getPriority() > p) 
			   {
				   node.setNext(next);
				   current.setNext(node);
				   count ++;
				   break;
			   }else
			   {
				   current = next;
				   next = current.getNext();
			   }
		   }
		   
		   
	   }
   }


      
	   
	/**
    * Removes the element at the front of this queue and returns a
    * reference to it. Throws an EmptyCollectionException if the
    * queue is empty.
    *
    * @return                           the element at the front of this queue
    * @throws EmptyCollectionException  if an empty collection exception occurs
    */
   public T dequeue() throws EmptyCollectionException
   {
      if (isEmpty())
         throw new EmptyCollectionException ("queue");

      T result = front.getElement();
      front = front.getNext();
      count--;

      if (isEmpty())
         rear = null;

      return result;
   }
   
   /**
    * Returns a reference to the element at the front of this queue.
    * The element is not removed from the queue.  Throws an
    * EmptyCollectionException if the queue is empty.  
    *
    * @return                            a reference to the first element in
    *                                    this queue
    * @throws EmptyCollectionsException  if an empty collection exception occurs
    */
   public T first() throws EmptyCollectionException
   {
	   return front.getElement();
   }

   /**
    * Returns true if this queue is empty and false otherwise. 
    *
    * @return  true if this queue is empty and false if otherwise
    */
   public boolean isEmpty()
   {
	   return count == 0;
   }
 
   /**
    * Returns the number of elements currently in this queue.
    *
    * @return  the integer representation of the size of this queue
    */
   public int size()
   {
	   return count;
   }

   /**
    * Returns a string representation of this queue. 
    *
    * @return  the string representation of this queue
    */
   public String toString()
   {
	   String result = "";
	   PriorityNode<T> current = front;
	   while (current != null)
	   {
		   result = result + current.getElement();
		   current = current.getNext();
	   }
	   return result;
   }
}

