public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        int store = x - y;
        return store == -1 || store == 1;
    }
}
