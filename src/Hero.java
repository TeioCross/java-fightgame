import java.util.ArrayList;
import java.util.Arrays;

public class Hero {
    private String name;
    private int MaxHP;
    private int ATK;
    private int DEF;
    private int MaxMP=100;
    private ArrayList<Skill> skills=new ArrayList<>(Arrays.asList(new Skill("普通攻击","无消耗",1.0,0),new Skill("强力一击","消耗10HP和10MP",1.8,4),new Skill("生命汲取","消耗10HP和10MP,恢复生命",0,1)));
    private ArrayList<Item> bag=new ArrayList<>();
    public Hero(String name, int MaxHP, int ATK, int DEF) {
        this.name = name;
        this.MaxHP = 100+10*MaxHP;
        this.ATK = 10+2*ATK;
        this.DEF = DEF;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHP() {
        return MaxHP;
    }

    public void setMaxHP(int maxHP) {
        MaxHP = maxHP;
    }

    public int getATK() {
        return ATK;
    }

    public void setATK(int ATK) {
        this.ATK = ATK;
    }

    public int getDEF() {
        return DEF;
    }

    public void setDEF(int DEF) {
        this.DEF = DEF;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public int getMaxMP() {
        return MaxMP;
    }

    public void setMaxMP(int maxMP) {
        MaxMP = maxMP;
    }

    public ArrayList<Item> getBag() {
        return bag;
    }

    public void setBag(ArrayList<Item> bag) {
        this.bag = bag;
    }
    public void putin(Item item) {
        this.bag.add(item);
    }
    public void putout(int a) {
        this.bag.remove(a);
    }
}

