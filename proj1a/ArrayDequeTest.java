class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque ad = new ArrayDeque<Integer>();
        ad.addFirst(0);
        System.out.println(ad.removeFirst());
        ad.addFirst(2);
        ad.addFirst(3);
        System.out.println(ad.removeFirst());
        System.out.println(ad.removeFirst());
    }
}