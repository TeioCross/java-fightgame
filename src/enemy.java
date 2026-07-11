import java.util.ArrayList;
import java.util.Arrays;


public enum enemy {
    ENEMY1("初级战士", 80, 15, 10, new ArrayList<Skill>(Arrays.asList(new Skill("普通攻击", "",1,0), new Skill("猛击", "",1.5,0)))),
    ENEMY2("敏捷刺客", 60, 20, 5, new ArrayList<Skill>(Arrays.asList(new Skill("普通攻击", "",1,0), new Skill("快速攻击", "",0.5,3)))),
    ENEMY3("重装坦克", 120, 10, 20, new ArrayList<Skill>(Arrays.asList(new Skill("普通攻击", "",1,0), new Skill("防御姿态", "",0,2)))),
    ENEMY4("神秘法师", 70, 25, 8, new ArrayList<Skill>(Arrays.asList(new Skill("普通攻击", "",1,0), new Skill("火球术", "",1.8,0))));
    private String name;
    private int MaxHP;
    private int ATK;
    private int DEF;
    private ArrayList<Skill> sk;
    private static ArrayList<String> bark=new ArrayList<>(Arrays.asList("就这点本事？回家喝奶去吧！", "哈哈，连我都打不过，你也配当冒险者？", "弱爆了！你的剑明明比你的嘴软多了。", "这就躺下了？我还没活动开呢！", "别急着注销账号啊，菜鸟。", "挣扎毫无意义，结局早已注定。", "你的恐惧……味道不错。", "安息吧，无人会记得你的失败。", "经验值，拿来吧。", "看，这就是反抗的下场。", "哎呀呀，姿势不错，可惜力道太弱了~", "别哭哦，姐姐下次会对你‘温柔’一点的。", "这身装备哪来的？地摊上捡的吗？", "再来一次？不，我可不想浪费体力欺负弱者。", "你的血，颜色倒是挺鲜艳的嘛。", "凡人，妄图挑战神明，便是这般结局吗？", "站起来，继续。让我看看你的极限……究竟有多低。", "世界不需要多余的英雄，尤其是像你这样无能的家伙。", "你的信念，比纸还薄。", "这场闹剧，该结束了。", "药都喝完了吧？背包里没红瓶了吧？", "刚才要是翻滚早0.5秒，你就赢了，可惜啊~", "这AI写得不错吧？谢谢夸奖！", "复活币还多吗？不多我送你点。", "网络连接正常，输了就是技不如我。", "废物。", "太慢！", "下一个。", "不堪一击。", "绝望了吗？"));

     enemy(String name, int maxHP, int ATK, int DEF, ArrayList<Skill> skills) {
        this.name = name;
        MaxHP = maxHP;
        this.ATK = ATK;
        this.DEF = DEF;
        this.sk = skills;
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

    public ArrayList<Skill> getSk() {
        return sk;
    }

    public void setSk(ArrayList<Skill> sk) {
        this.sk = sk;
    }

    public static String getBark() {
        return bark.get(Service.getrdint(0,bark.size()));
    }
}
