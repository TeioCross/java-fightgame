import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public  class Service {
    private static Scanner sc = new Scanner(System.in);
    private static Random rd = new Random();
    public static boolean haveletter(String e){
        for(int i = 0; i < e.length(); i++){
            if ((e.charAt(i) >= 'a'&& e.charAt(i) <='z')||(e.charAt(i) >= 'A'&& e.charAt(i) <='Z')) {
                return true;
            }
        }
        return false;
    }
    public static boolean havenum(String e){
        for(int i = 0; i < e.length(); i++){
            if (e.charAt(i) >= '0'&& e.charAt(i) <='9') {
                return true;
            }
        }
        return false;
    }
    public static boolean Slength(String e,int a,int b){
        if (e.length() < a||e.length() > b) {
            return false;
        }
        return true;
    }
    public static int check(String a, String b, ArrayList<Users> c, int fail) {
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).getName().equals(a)) {
                if (fail>=3)c.get(i).setState(false);
                if (c.get(i).getState()) {
                    if (c.get(i).getPw().equals(b)) {
                        return i;
                    }else return -1;
                }else return -3;
            }
        }
        return -2;
    }
    public static int exist(String a,ArrayList<Users> c){
        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).getName().equals(a)) {
                return i;
            }
        }
        return -1;
    }
    public static boolean pointcheck(int a){
        if (a>0){
            System.out.println("未用完点数，请重新分配");
            return false;
        } else if(a<0){
            System.out.println("点数不足，请重新分配");
            return false;
        } else return true;
    }
    public static boolean restcheck(int a){
        if (a<0){
            return false;
        } else return true;
    }

    public static String getinput(){
        String temp=sc.nextLine();;
        while (true) {
            if(temp.matches("\\w"))return sc.nextLine();
        }
    }
    public static String getinputnum(){
        String temp=sc.nextLine();;
        while (true) {
            if(temp.matches("\\d+"))return sc.nextLine();
        }
    }
    public static int getinputInt(){
        return sc.nextInt();
    }

    public static int movementplayer(int a){
        switch (a) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            default:
                return -1;
        }
    }
    public static int damage(int atk,int def,double skill,boolean defending){
        if (skill!=1.0) {
            if (defending){
                return Math.max(1,((int)(atk*skill-def))/2);
            }
            else return Math.max(1,(int)(atk*skill-def));
        } else {
            if (defending){
                return Math.max(1,((int)((atk-def)/2)));
            }
            else return Math.max(1,(int)(atk-def));
        }
    }
    public static int special(int a){
        if(a>0&&a<4) return a;
        else return -1;
    }
    public static int heal(){
        return getrdint(0,20);
    }

    public static int[] playermove(ArrayList<Skill> heroSkills,int heroHP,Hero hero,int heroATK,int enemyDEF,boolean defending,enemy enemy,int rMP,int rHP){
        int dam=0;
        int heal=0;
        int MP=0;
        Ui.battleinfoplayer(heroSkills);
        int move= 0;
        while (true) {
            move = Service.movementplayer(Service.getinputInt());
            if (move==2||move==3) {
              if (rMP<10) {
                  System.out.println("MP不足，请重新选择");
              } else if(rHP<10){
                  System.out.println("HP不足，请重新选择");
              }else break;
              }else if(move==4){
                if(hero.getBag().isEmpty()) {
                    System.out.println("你的背包是空的");
                }else {
                    break;
                }
                }else break;
            }
        if (move!=4) {
            int spe=heroSkills.get(move-1).getSpecial();
            String skill=heroSkills.get(move-1).getName();
            switch (spe){
                case 1->{
                    MP=10;
                    heroHP-=10;
                    int temp=heroHP;
                    heroHP=Math.min(hero.getMaxHP(),heroHP+=Service.heal());
                    System.out.println("消耗10HP和10MP后，你使用 "+skill+"恢复了"+(heroHP-temp)+"点生命值");
                    heal=(heroHP-temp)-10;
                }
                case 4->{
                    MP=10;
                    heal=-10;
                    dam=Service.damage(heroATK,enemyDEF,heroSkills.get(move-1).getPower(),defending);
                    System.out.println("消耗10HP和10MP后，你对"+enemy.getName()+"使用 "+skill+" 造成了"+dam+"点伤害！");
                }
                default -> {
                    MP=0;
                    dam=Service.damage(heroATK,enemyDEF,heroSkills.get(move-1).getPower(),defending);
                    System.out.println("你对"+enemy.getName()+"使用 "+skill+" 造成了"+dam+"点伤害！");
                }
            }
            return new int[]{dam,heal,MP};
        } else {
            return useitem(hero);
        }
    }
    public static int enemymove(ArrayList<Skill> enemySkills,int enemyHP,enemy enemy,int enemyATK,int heroDEF){
        int dam=0;
        Ui.battleinfoenemy(enemy,enemySkills);
        int move= getrdint(0,1);
        int spe=enemySkills.get(move).getSpecial();;
        String skill=enemySkills.get(move).getName();
        switch (spe){
            case 2->{
                dam=0;
                System.out.println("敌人使用了 "+skill+"，下回合受伤减半。");
            }
            case 3->{
                dam=Service.damage(enemyATK,heroDEF,enemySkills.get(move).getPower(),false);
                System.out.println("敌人对你使用了 "+skill+" ，造成了两次"+dam+"点伤害！");
                dam+=dam;
            }
            default -> {
                dam=Service.damage(enemyATK,heroDEF,enemySkills.get(move).getPower(),false);
                System.out.println("敌人对你使用了 "+skill+" ，造成了"+dam+"点伤害！");
            }
        }
        return dam;
    }
    public static enemy selectenemy(int ed){
        switch (ed){
            case 1 -> {
                return enemy.ENEMY1;
            }
            case 2 -> {
                return enemy.ENEMY2;
            }
            case 3 -> {
                return enemy.ENEMY3;
            }
            case 4 -> {
                return enemy.ENEMY4;
            }
            default -> throw new IllegalStateException("Unexpected value: " + ed);
            }
    }
    public static int noreapeatrd(int last){
        int temp=getrdint(1,4);
        while (true) {
            if (temp!=last) break;
            else temp=getrdint(1,4);
        }
        return temp;
    }
    public static boolean con(){
        String choose=Service.getinput();
        while (!(choose.equalsIgnoreCase("y")||choose.equalsIgnoreCase("n"))){
            System.out.println("请输入y,Y,n,N之一");
            choose=Service.getinput();
        }
        return choose.equalsIgnoreCase("y");
    }
    public static void playerboost(Hero hero){
        hero.setMaxHP(hero.getMaxHP()+30);
        hero.setATK(hero.getATK()+5);
        hero.setDEF(hero.getDEF()+3);
    }
    public static void enemyboost(enemy enemy){
        enemy.setMaxHP(enemy.getMaxHP()+10);
        enemy.setATK(enemy.getATK()+3);
        enemy.setDEF(enemy.getDEF()+2);
    }
    public static int getrdint(int a,int b){
        return rd.nextInt(a,b+1);
    }
    public static String pw(String pw) {
        if (!Service.Slength(pw, 3, 8)) {System.out.println("密码长度应在3-8个字符之间");
        return "error";
        } else {
            if (Service.havenum(pw)) {
                if (Service.haveletter(pw)) {
                    System.out.println("密码符合要求");
                    while (true) {
                        System.out.println("请确认密码：");
                        String cpw =getinput();
                        if (cpw.equals(pw)) {
                            System.out.println("密码确认成功");
                            return pw;
                        } else System.out.println("密码不一致，请重新输入");
                    }
                } else {System.out.println("密码不能是纯数字");
                return "error";
                }
            } else {System.out.println("密码不能是纯字母");
            return "error";
            }
        }
    }
    public static int[] useitem(Hero hero){
        Ui.baginfo(hero);
        ArrayList<Item> temp=hero.getBag();
        int used= 0;
        while (true) {
            used = Service.getinputInt()-1;
            if(used>=0&&used<temp.size())break;
        }
        int effect=temp.get(used).getEffect();
        System.out.println("你使用 "+temp.get(used).getName()+"恢复了"+effect+"点生命值");
        hero.putout(used);
        return new int[]{0,effect,0};
    }

    public static void getitem(Hero hero,ArrayList<Item> drop){
         int num=Service.getrdint(0,drop.size()-1);
         hero.putin(drop.get(num));
        System.out.println("幸运奖励！你获得了一个"+drop.get(num).getName());
    }
    public static boolean luck(){
        return getrdint(1,100)<=90;
    }
}

