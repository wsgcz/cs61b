public class OffByN implements CharacterComparator {
    private int num;
    public OffByN(int N) {
        num = N;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int store = x - y;
        return store == num || store == -1 * num;
    }
}
