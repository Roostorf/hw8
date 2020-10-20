package edu.ics211.h08;

import java.util.NoSuchElementException;

/**
 * Represents a CircularArrayQueue.
 *
 * @author Constantine Peros
 * @param <E> the data
 */
public class CircularArrayQueue<E> implements Queue211<E> {
  
  private static final int CAPACITY = 10000; 
  private int front;
  private int rear;
  private int size;
  private E[] data;
  
  /**
   * Construct a queue with the default initial capacity.
   *
   */
  public CircularArrayQueue() {
    
    front = 0;
    size = 0;
    rear = CAPACITY - 1;
    data = (E[]) new Object[CAPACITY];

  }
  
  @Override
  public boolean add(E e) {
    // adds e to the end of the queue. May throw an IllegalStateException if the queue is full.
    fullQ();
    rear = (rear + 1) % data.length; 
    data[rear] = e;
    System.out.println(data[rear]);
    size++;
    System.out.println(size);
    return true;
  }

  /**
   * Throws illegal state exception if queue is full.
   */
  private void fullQ() {
    if (size == data.length) {
      throw new IllegalStateException();
    } 
  }

  @Override
  public E element() {
    // Retrieves, but doesn't remove the head of the queue. Throws NoSuchElementException if queue is empty.
    sizeZero();
    return data[front];
  }

  @Override
  public boolean offer(E e) {
    // adds e to the end of the queue. Returns false if the queue is full.
    try {
      return add(e);
    } catch (IllegalStateException ise) {
      return false;
    }

  }


  

  @Override
  public E peek() {
    // Retrieves, but doesn't remove the head of the queue. Return null if queue is empty.
    try {
      return element(); //try to see the element at the front
    } catch (IllegalStateException ise) { //if queue is empty
      return null; //return null
    }

  }

  @Override
  public E poll() {
    // Retrieves and removes the head of the queue. Returns null if the queue is empty.
    try {
      return remove();
    } catch (NoSuchElementException nse) {
      return null;
    }


  }

  @Override
  public E remove() {
    // Retrieves and removes the head of the queue. Throws NoSuchElementException if queue is empty.
    sizeZero();
    E oldFront = data[front];
    front = (front + 1) % CAPACITY; // front is 0 unless greater than capacity
    size --; //decrement size
    return oldFront;
  }

  /**
   * Throws illegal state exception if size is zero.
   */
  private void sizeZero() {
    if (size <= 0) {
      throw new NoSuchElementException();
    } 
  }

  @Override
  public int size() {
    // Returns the size of the queue.
    return size;
  }

 

}
