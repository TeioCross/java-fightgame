import java.util.ArrayList;
import java.util.Arrays;

public enum Item {
    Item1("桃子", "生命 + 10", 10),
    Item2("煎蛋", "生命 + 20", 20),
    Item3("花酿鸡", "生命 + 30", 30),
    Item4("黑背鲈鱼", "生命 + 40", 40),
    Item5("白玉汤", "生命 + 50", 50);
    private String name;
    private String info;
    private int effect;
    Item(String name, String info, int effect) {
        this.name = name;
        this.info = info;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }
}
