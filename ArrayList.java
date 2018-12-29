/**
 * Your implementation of an ArrayList.
 *
 * @author Brian Zhu
 * @version 1.0
 * @userid bzhu75
 * @GTID 903408566
 */
public class ArrayList<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * The initial capacity of the array list.
     */
    public static final int INITIAL_CAPACITY = 13;

    /**
     * Constructs a new ArrayList.
     * <p>
     * You may add statements to this method.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Adds the element to the index specified.
     * <p>
     * Remember that this add may require elements to be shifted.
     * <p>
     * Adding to index {@code size} should be amortized O(1),
     * all other adds are O(n).
     *
     * @param index The index where you want the new element.
     * @param data  The data to add to the list.
     * @throws java.lang.IndexOutOfBoundsException if index is negative
     *                                             or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Cannot add to an invalid"
                + " index (index<0 or index>size)");
        }
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null"
                + " data into the data structure");
        }
        if (index == backingArray.length) {
            resize();
        }
        if (index == size) {
            backingArray[index] = data;
        } else {
            for (int i = size; i > index; i--) {
                backingArray[i] = backingArray[i - 1];
            }
            backingArray[index] = data;
        }
        size++;
    }

    /**
     * Add the given data to the front of your array list.
     * <p>
     * Remember that this add may require elements to be shifted.
     * <p>
     * Must be O(n).
     *
     * @param data The data to add to the list.
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data into"
                + " the data structure");
        }
        if (size == backingArray.length) {
            resize();
        }
        for (int i = size; i > 0; i--) {
            backingArray[i] = backingArray[i - 1];
        }
        backingArray[0] = data;
        size++;
    }

    /**
     * Add the given data to the back of your array list.
     * <p>
     * Must be amortized O(1).
     *
     * @param data The data to add to the list.
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data "
                + "into the data structure");
        }
        if (size == backingArray.length) {
            resize();
        }
        backingArray[size] = data;
        size++;
    }

    /**
     * Removes and returns the element at index.
     * <p>
     * Remember that this remove may require elements to be shifted.
     * <p>
     * This method should be O(1) for index {@code size - 1} and O(n) in
     * all other cases.
     *
     * @param index The index of the element
     * @return The object that was formerly at that index.
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     *                                             index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Cannot remove at"
                + "an invaild index, index<0 or index>=size");
        }
        if (index == size - 1) {
            return removeFromBack();
        } else if (size > 0) {
            T temp = backingArray[index];
            for (int i = index; i < size - 1; i++) {
                backingArray[i] = backingArray[i + 1];
            }
            size--;
            backingArray[size] = null;
            return temp;
        } else {
            return null;
        }
    }

    /**
     * Remove the first element in the list and return it.
     * <p>
     * Remember that this remove may require elements to be shifted.
     * <p>
     * Must be O(n).
     *
     * @return The data from the front of the list or null if the list is empty
     */
    public T removeFromFront() {
        if (size > 0) {
            T temp = backingArray[0];
            for (int i = 0; i < size - 1; i++) {
                backingArray[i] = backingArray[i + 1];
            }
            size--;
            backingArray[size] = null;
            return temp;
        } else {
            return null;
        }
    }

    /**
     * Remove the last element in the list and return it.
     * <p>
     * Must be O(1).
     *
     * @return The data from the back of the list or null if the list is empty
     */
    public T removeFromBack() {
        if (size > 0) {
            T temp = backingArray[size - 1];
            backingArray[size - 1] = null;
            size--;
            return temp;
        } else {
            return null;
        }
    }

    /**
     * Returns the element at the given index.
     * <p>
     * Must be O(1).
     *
     * @param index The index of the element
     * @return The data stored at that index.
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     *                                             index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Cannot remove at"
                + "an invaild index, index<0 or index>=size");
        }
        return backingArray[index];
    }

    /**
     * Return a boolean value representing whether or not the list is empty.
     * <p>
     * Must be O(1).
     *
     * @return true if empty; false otherwise
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Clear the list. Reset the backing array to a new array of the initial
     * capacity.
     * <p>
     * Must be O(1).
     */
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Return the size of the list as an integer.
     * <p>
     * For grading purposes only. DO NOT USE THIS METHOD IN YOUR CODE!
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Return the backing array for this list.
     * <p>
     * For grading purposes only. DO NOT USE THIS METHOD IN YOUR CODE!
     *
     * @return the backing array for this list
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Does not return anything
     *
     * Enlarges the backing array to double the current size,
     * per the homework instructions
     */
    private void resize() {
        int oldCapacity = backingArray.length;
        T[] tempBackingArr = (T[]) new Object[oldCapacity * 2];
        for (int i = 0; i < oldCapacity; i++) {
            tempBackingArr[i] = backingArray[i];
        }
        backingArray = tempBackingArr;
    }
    /**
    public String toString(){
        String answer = "";
        for (int i = 0; i < backingArray.length; i++){
            answer += backingArray[i]+" ";
        }
        return answer;
    }
     */
}
