

public class Wire {
    private int signal;
    private String name;

    public Wire(String nameInst, int signalInst) {
        this.name = nameInst;
        this.signal = signalInst;
    }

    public Wire(String nameInst) {
        this.name = nameInst;
        this.signal = (short)-1;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Wire)) return false;
        Wire otherWire = (Wire) obj;
        if (otherWire.getName().equals(this.getName())) return true;
        else return false;
    }

    public int getSignal() {
        return signal;
    }

    public void setSignal(int signal) {
        this.signal = signal;
    }

    public String getName() {
        return name;
    }
}
