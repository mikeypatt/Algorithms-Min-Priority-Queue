package utils;

import java.util.ArrayList;

public class MinPriorityQueue<T extends Comparable<T>> {

  // creates an empty queue.
  private ArrayList<T> queue;

  public MinPriorityQueue() {
    queue = new ArrayList<T>();
  }

  /** Returns the number of elements currently in the queue. */
  public int size() {
    return queue.size();
  }

  // gets the number at the given index
  public T get(int index) {
    return queue.get(index);
  }

  public void add(T elem) {

    // Check that the root is free
    if (queue.isEmpty()) {
      queue.add(elem);
    } else {
      queue.add(elem);
      int queueRear = queue.size() - 1;
      recursiveShuffleUp(elem, queueRear);
    }
  }

  public void recursiveShuffleUp(T elem, int index) {

    // base case
    if (index == 0) {
      return;
    }

    int parentIndex = (index + 1) / 2 - 1;

    // if the value is smaller than its parent index
    if (elem.compareTo(queue.get(parentIndex)) <= 0) {
      // swap the two values
      T tempValue = queue.get(parentIndex);
      // set the parent value to the new value
      queue.set(parentIndex, elem);
      queue.set(index, tempValue);
      // call the function again
      recursiveShuffleUp(queue.get(parentIndex), parentIndex);
    }
  }

  /** Removes, and returns, the element at the front of the queue. */
  public T remove() {

    // gets the root of the tree
    T root = queue.get(0);
    if (queue.size() == 1) {
      queue.remove(queue.get(0));
      return root;
    }

    // gets the tail of the tree
    T tail = queue.get(queue.size() - 1);
    // set the tail number as the root
    queue.set(0, tail);
    // delete the tail
    queue.remove(queue.size() - 1);
    // shuffle down
    recursiveShuffleDown(queue.get(0), 0);

    return root;
  }

  public void recursiveShuffleDown(T elem, int index) {

    // base case if it reaches the new tail
    int leftChildIndex = 2 * (index + 1) - 1;
    int rightChildIndex = 2 * (index + 1);

    if(leftChildIndex > queue.size() - 1 || rightChildIndex > queue.size() - 1){
      return;
    }

    if (elem.compareTo(queue.get(leftChildIndex)) < 0
        && elem.compareTo(queue.get(rightChildIndex)) < 0) return;

    // If the left child is smaller than the right its going to go left
    if (queue.get(leftChildIndex).compareTo(queue.get(rightChildIndex)) <= 0) {

      T tempValue = queue.get(leftChildIndex);
      queue.set(leftChildIndex, elem);
      queue.set(index, tempValue);
      recursiveShuffleDown(queue.get(leftChildIndex), leftChildIndex);

    }

    // else it is going to go right
    else {

      T tempValue = queue.get(rightChildIndex);
      queue.set(rightChildIndex, elem);
      queue.set(index, tempValue);
      recursiveShuffleDown(queue.get(rightChildIndex), rightChildIndex);
    }
  }

  /** Returns true if the queue is empty, false otherwise. */
  public boolean isEmpty() {
    return queue.isEmpty();
  }
}
