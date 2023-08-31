class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque ad = new ArrayDeque<Integer>();
        ad.addLast(0);
        ad.addFirst(1);
        System.out.println(ad.removeLast());
        System.out.println(ad.removeLast());
        ad.addLast(4);
        ad.isEmpty();
        System.out.println(ad.removeLast());
    }
}