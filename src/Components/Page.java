package Components;

public class Page {
    private int value;

    private int uses;

    private boolean useBit; // usado pelo algoritmo Clock


    public Page(int value) {
        this.value = value;
        this.useBit = true;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getUses() {
        return uses;
    }

    public void setUses(int uses) {
        this.uses = uses;
    }
    public void addUse() {
        this.uses ++;
    }

    public boolean isUseBit() {
        return useBit;
    }

    public void setUseBit(boolean useBit) {
        this.useBit = useBit;
    }
}
