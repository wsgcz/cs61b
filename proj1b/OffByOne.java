public class OffByOne implements CharacterComparator{

    @Override
    public boolean equalChars(char x, char y) {
        int store = x - y;
        if (store == -1 || store == 1){
            return true;
        } else{
            return false;
        }
    }
}
