public class ArrayDeque<T> implements Deque<T>{
    private int size;
    private int first;
    private int last;
    private T[] array;

    public ArrayDeque() {
        size = 0;
        array = (T []) new Object[8];
        last = 0;
        first = 0;
    }
    @Override
    public void addFirst(T item) {
        if (size == 0) {
            array[0] = item;
            first = 0;
            last = 0;
        } else {
            if (array.length == size) {
                resize(size * 2);
            }
            if (first == 0) {
                first = array.length - 1;
            } else {
                first = first - 1;
            }
            array[first] = item;
        }
        size += 1;
    }
    private void resize(int length) {
        T[] a = (T []) new Object[length];
        if (first <= last) {
            System.arraycopy(array, first, a, 0, size);
        } else {
            System.arraycopy(array, first, a, 0, array.length - first);
            System.arraycopy(array, 0, a, array.length - first, last + 1);
        }
        first = 0;
        last = size - 1;
        array = a;
    }
    @Override
    public  void addLast(T item) {
        if (size == 0) {
            array[0] = item;
            first = 0;
            last = 0;
        } else {
            if (array.length == size) {
                resize(2 * size);
            }
            if (last == array.length - 1) {
                last = 0;
            } else {
                last = last + 1;
            }
            array[last] = item;
        }
        size += 1;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        if (first <= last) {
            for (int i = first; i < size + first; i++) {
                System.out.print(array[i]);
                System.out.print(" ");
            }
        } else {
            for (int i = first; i < array.length; i++) {
                System.out.print(array[i]);
                System.out.print(" ");
            }
            for (int i = 0; i < last; i++) {
                System.out.print(array[i]);
                System.out.print(" ");
            }
        }
    }
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size - 1 <= (array.length / 4) && array.length >= 16) {
            resize(array.length / 2);
        }
        T store;
        store = array[first];
        array[first] = null;
        if (first != last) {
            if (first == array.length - 1) {
                first = 0;
            } else {
                first = first + 1;
            }
        }
        size -= 1;
        return store;
    }
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (size - 1 <= (array.length / 4) && array.length >= 16) {
            resize(array.length / 2);
        }
        T store;
        store = array[last];
        array[last] = null;
        if (first != last) {
            if (last == 0) {
                last = array.length - 1;
            } else {
                last = last - 1;
            }
        }
        size -= 1;
        return store;
    }
    @Override
    public T get(int index) {
        if (first <= last) {
            return array[index + first];
        } else {
            if (index < array.length - first) {
                return array[first + index];
            } else {
                index = index - (array.length - first);
                return array[index];
            }
        }
    }
}
