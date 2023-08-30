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
        stand = new List(stand, null, stand);
        size = 0;
    }
    private T Recursionhelp(List L, int n) {
        if (n == 0) {
            return L.content;
        }
        else {
            return Recursionhelp(L.next, n - 1);
        }
    }
    public T getRecursive(int index) {
        return Recursionhelp(this.stand.next, index);
    }
    public int size() {
        return size;
    }
    public void addFirst(T item) {
        if (size == 0) {
            stand.pre = new List(stand, item, stand);
            stand.next = stand.pre;
        }
        else {
            stand.next = new List(stand, item, stand.next);
        }
        size += 1;
    }
    public void addLast(T item) {
        if (size == 0)
        {
            stand.pre = new List(stand, item, stand);
            stand.next = stand.pre;
        }
        else {
            stand.pre = new List(stand.pre, item, stand);
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
        }
        else {
            store = stand.next.content;
            stand.next = stand.next.next;
            if (size == 1) {
                stand.pre = stand;
            }
            size -= 1;
        }
        return store;
    }
    public T removeLast() {
        T store;
        if (size == 0) {
            return null;
        }
        else {
            store = stand.pre.content;
            stand.pre = stand.pre.pre;
            if (size == 1) {
                stand.next = stand;
            }
            size -= 1;
        }
        return store;
    }
    public T get(int index) {
        List p = stand;
        while (p.next != stand) {
            p = p.next;
        }
        return p.content;
    }
}
