import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
   public  static void showmain(){
       System.out.println("╔════════════════════════════════╗");
       System.out.println("    🎮 欢迎来到文字格斗游戏 🎮   ");
       System.out.println("╚════════════════════════════════╝");
       System.out.println("请选择操作：1登录 2注册 3忘记密码 4退出");
   }
   public  static void showcreat(Users us){
       System.out.println("创建你的角色:");
       System.out.println("您的角色名为: "+us.getName());
       System.out.println("请分配属性点 (共20点):");
       System.out.println("1. 生命值 (每点+10 HP)");
       System.out.println("2. 攻击力 (每点+2 ATK)");
       System.out.println("3. 防御力 (每点+1 DEF)");
   }
   public static void battlestart(int i, String enemy){
       System.out.println("═══════════════════════════════════════");
       System.out.println("⚔\uFE0F第"+i+"场战斗开始！对手: "+enemy);
       System.out.println("---------------------------------------");
   }
   public static void heroinfo(Hero hero){
       System.out.println("角色创建成功！");
       System.out.println("初始属性: "+hero.getName()+ "[HP: "+hero.getMaxHP()+", ATK: "+hero.getATK()+", DEF: "+hero.getDEF()+"]");
       System.out.print("拥有技能:");
       for(int i=0;i<hero.getSkills().size();i++){
           if(i==hero.getSkills().size()-1) {
               System.out.println(hero.getSkills().get(i).getName());
           }
           else System.out.print(hero.getSkills().get(i).getName()+", ");
       }
   }
   public static void battleinfoplayer(ArrayList<Skill> sk){
       System.out.println("===== 你的回合 =====");
       for(int i=0;i<sk.size();i++){
           System.out.println(i+1+". "+sk.get(i).getName()+"("+sk.get(i).getEffect()+")");
           if(i==sk.size()-1) {
               System.out.println(i+2+". 使用道具");
           }
       }
   }
    public static void battleinfoenemy(enemy enemy,ArrayList<Skill> sk){
        System.out.println("===== "+enemy.getName()+"的回合 =====");
    }
    public static void Roundinfo(enemy enemy,Hero hero,int heroHP,int enemyHP,int maxHPh,int maxHPe,int round,int heroMP,int maxmp){
        System.out.println("第 "+round+" 回合开始");
        String temp="██".repeat((int)(10*((double)heroHP/maxHPh)))+" ".repeat(10-(int)(10*((double)heroHP/maxHPh)));
        System.out.println(hero.getName()+": ["+temp+"] "+heroHP+"/"+maxHPh+" HP"+" "+heroMP+"/"+maxmp+" MP");
        temp="██".repeat((int)(10*((double)enemyHP/maxHPe)))+" ".repeat(10-(int)(10*((double)enemyHP/maxHPe)));
        System.out.println(enemy.getName()+": ["+temp+"] "+enemyHP+"/"+maxHPe+" HP");
    }
    public static void next1(){
        System.out.println("═══════════════════════════════════════");
        System.out.println("继续下一场战斗？(y/n):");
    }
    public static void next2(){
        System.out.println("═══════════════════════════════════════");
    }
    public static void boost(){
        System.out.println("═══════════════════════════════════════");
        System.out.println("✨ 恭喜你！你获得了属性提升！" );
        System.out.println("生命值 +30，攻击力 +5，防御力 +3");
        System.out.print("当前属性：");
    }
    public static void boostinfo(Hero hero){
        System.out.println(hero.getName()+ "[最大HP: "+hero.getMaxHP()+", ATK: "+hero.getATK()+", DEF: "+hero.getDEF()+"]");
    }
    public static void baginfo(Hero hero){
        System.out.println("请选择你要使用的道具：");
        ArrayList<Item> temp=hero.getBag();
        for(int i=0;i<temp.size();i++){
            System.out.print((i+1)+temp.get(i).getName()+"("+temp.get(i).getInfo()+")， ");
        }
    }
}
