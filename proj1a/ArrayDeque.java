public class ArrayDeque<T> {
    private int size;
    int first;
    private int last;
    private T[] Array;

    public ArrayDeque() {
        size = 0;
        Array = (T []) new Object[8];
        last = 0;
        first = 0;
    }
    public void addFirst(T item) {
        if (size == 0) {
            Array[0] = item;
        }
        else {
            if (Array.length == size) {
                resize(size * 2);
            }
            if (first == 0) {
                first = Array.length - 1;
            }
            else {
                first = first - 1;
            }
            Array[first] = item;
        }
        size += 1;
    }
    private void resize(int length) {
        T[] a = (T []) new Object[length];
        if (first <= last) {
            System.arraycopy(Array, first, a, 0, size);
        }
        else {
            System.arraycopy(Array, first, a, 0, Array.length - first);
            System.arraycopy(Array, 0, a, Array.length - first, last + 1);
        }
        first = 0;
        last = size - 1;
        Array = a;
    }
    public  void addLast(T item) {
        if (size == 0) {
            Array[0] = item;
        }
        else {
            if (Array.length == size) {
                resize(2 * size);
            }
            if (last == Array.length - 1) {
                last = 0;
            }
            else {
                last = last + 1;
            }
            Array[last] = item;
        }
        size += 1;
    }
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        else {
            return false;
        }
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        if (first <= last) {
            for (int i = first; i < size + first; i++) {
                System.out.print(Array[i]);
                System.out.print(" ");
            }
        }
        else {
            for (int i = first; i<Array.length; i++) {
                System.out.print(Array[i]);
                System.out.print(" ");
            }
            for (int i = 0; i < last; i++) {
                System.out.print(Array[i]);
                System.out.print(" ");
            }
        }
    }
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size-1 <= Array.length && Array.length >= 16){
            resize(Array.length / 2);
        }
        T store;
        store = Array[first];
        Array[first] = null;
        if (first == Array.length - 1) {
            first = 0;
        }
        else {
            first = first + 1;
        }
        size -= 1;
        return store;
    }
    public T removeLast(){
        if (size == 0) {
            return null;
        }
        if (size-1 <= Array.length && Array.length >= 16) {
            resize(Array.length/2);
        }
        T store;
        store = Array[last];
        Array[last] = null;
        if (last == 0) {
            last = Array.length - 1;
        }
        else {
            last = last - 1;
        }
        size -= 1;
        return store;
    }
    public T get(int index) {
        if (first <= last) {
            return Array[index + first];
        }
        else {
            if (index < Array.length - first)
            {
                return Array[first + index];
            }
            else {
                index = index - (Array.length - first);
                return Array[index];
            }
        }
    }
}
