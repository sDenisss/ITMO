public class Positions {
    // int next;
    int[] pos;

    Positions(int capacity) {
        this.pos = new int[capacity];
        for (int i = 0; i < pos.length; i++) {
            this.pos[i] = i + 1;
        }
        this.pos[capacity - 1] = -1; // Указывает на конец списка
    }

    public int getNext(int index) {
        return pos[index];
    }

    public void setNext(int index, int nextIndex) {
        this.pos[index] = nextIndex;
    }

}
