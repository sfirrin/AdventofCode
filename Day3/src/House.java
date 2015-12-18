public class House {
    private int x;
    private int y;

    public House(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof House) || obj == null) return false;
        House houseObj = (House) obj;
        if (this.getX() == houseObj.getX() && this.getY() == houseObj.getY()) {
            return true;
        } else return false;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
