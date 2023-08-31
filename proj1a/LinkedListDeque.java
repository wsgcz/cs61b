public class LinkedListDeque<T> {
    private class List {
        List pre;
        List next;
        T content;
        public List(List pre1, T cot, List nex) {
            pre = pre1;
            content = cot;
            next = nex;
        }
    }
    private List stand;
    private int size;
    public LinkedListDeque() {
        stand = new List(null, null, null);
        stand.pre = stand;
        stand.next = stand;
        size = 0;
    }
    private T recursion(List L, int n) {
        if (n == 0) {
            return L.content;
        } else {
            return recursion(L.next, n - 1);
        }
    }
    public T getRecursive(int index) {
        return recursion(this.stand.next, index);
    }
    public int size() {
        return size;
    }
    public void addFirst(T item) {
        stand.next = new List(stand, item, stand.next);
        stand.next.next.pre = stand.next;
        size += 1;
    }
    public void addLast(T item) {
        stand.pre = new List(stand.pre, item, stand);
        stand.pre.pre.next = stand.pre;
        size += 1;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public void printDeque() {
        List p = stand.next;
        while (p.next != stand) {
            System.out.print(p.content);
            System.out.print(" ");
            p = p.next;
        }
    }
    public  T removeFirst() {
        T store;
        if (size == 0) {
            return null;
        } else {
            store = stand.next.content;
            stand.next.next.pre = stand;
            stand.next = stand.next.next;
            size -= 1;
        }
        return store;
    }
    public T removeLast() {
        T store;
        if (size == 0) {
            return null;
        } else {
            store = stand.pre.content;
            stand.pre.pre.next = stand;
            stand.pre = stand.pre.pre;
            size -= 1;
        }
        return store;
    }
    public T get(int index) {
        List p = stand.next;
        while (index != 0) {
            p = p.next;
            index = index - 1;
        }
        return p.content;
    }
}
