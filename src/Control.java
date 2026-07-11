import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;

public class Control {
    ArrayList<Users> member=new ArrayList<>();
    public Users MainStart() {
        while (true) {
            Ui.showmain();
            switch(Service.getinputnum()){
                case "1"-> {return login();}
                case "2"->member.add(register());
                case "3"->forget(member);
                case "4"->System.exit(0);
                default -> System.out.println("输入错误，请重新选择");
            }
        }
    }
    public Users register() {
        String tna,tpw,tphone;
        while (true) {
            System.out.println("请输入用户名：");
            String na = Service.getinput();
            if (!Service.Slength(na, 3, 16)) {
                System.out.println("用户名长度应在3-16个字符之间");
            } else {
                if (Service.haveletter(na)) {
                    System.out.println("用户名符合要求");
                    tna = na;
                    break;
                } else System.out.println("用户名不能是纯数字");
            }
        }
        while (true) {
            System.out.println("请输入密码：");
            String pw = Service.getinput();
            String temp=Service.pw(pw);
            if (!temp.equals("error")) {
                tpw=temp;
                break;
            }
        }
        while (true) {
            System.out.println("请输入绑定电话：");
            String phone = Service.getinputnum();
            if(Service.haveletter(phone)||phone.length()!=11){
                System.out.println("电话号码不合规，请重新输入");
            }else {
                System.out.println("电话号码绑定成功");
                tphone=phone;
                break;
            }
        }
        Users user=new Users(tna, tpw, tphone);
        System.out.println("注册成功"+tna);
        return user;
    }
    public Users login() {
        int fail=0;
        while (true) {
            System.out.println("请输入用户名：");
            String na = Service.getinput();
            System.out.println("请输入密码：");
            String pw = Service.getinput();
            while (true){
                String Yzm = YZM.createYZM();
                System.out.println("请输入验证码："+Yzm);
                if(YZM.verify(Yzm, Service.getinput())) break;
                else System.out.println("验证码错误，请重新输入一个新的验证码");
            }
            int che = Service.check(na, pw, member,fail);
            switch (che){
                case -1 -> {
                    System.out.println("密码错误");
                    fail++;
                    if (fail > 0&&fail < 3) {
                        System.out.println("您还有" + (3 - fail) + "次机会");
                    }
                    if((3 - fail)==0 ) System.out.println("您已输入错误3次，账号已锁定");
                }
                case -2 -> System.out.println("用户名未注册，请先注册");
                case -3 -> System.out.println("用户" + na + "已经锁定，请联系黑马程序员官方客服");
                default -> {
                    System.out.println("登录成功"+member.get(che).getName());
                    return member.get(che);
                }
            }
        }
    }
    public void Gamestart(Users us){
        int last=0;
        int battlenum=1;
        Hero hero = createhero(us);
        int HP=hero.getMaxHP();
        int MP=hero.getMaxMP();
        ArrayList<Item> drop=new ArrayList<>(Arrays.asList(Item.Item1,Item.Item2,Item.Item3,Item.Item4,Item.Item5));
        while (true) {
            last=Service.noreapeatrd(last);
            enemy e=Service.selectenemy(last);
            int[] arr=battle(battlenum,e,hero,HP,MP);
            int resthp=arr[0];
            if(resthp>0){
            MP=Math.min(arr[1]+30,hero.getMaxMP());
            HP=resthp;
            HP+=win(e,battlenum);
            if(Service.luck()){
                Service.getitem(hero,drop);
            }
                if(battlenum%3==0){
                    Ui.boost();
                    Service.playerboost(hero);
                    HP+=30;
                    Ui.boostinfo(hero);
                }
            HP=Math.min(HP,hero.getMaxHP());
            Ui.next1();
            if(!Service.con()){
                Ui.next2();
                break;
            }
            Service.enemyboost(enemy.ENEMY1);
            Service.enemyboost(enemy.ENEMY2);
            Service.enemyboost(enemy.ENEMY3);
            Service.enemyboost(enemy.ENEMY4);
            battlenum++;
            }else{
                lose(e);
                battlenum--;
                break;
            }
        }
        end(battlenum);
    }
    public static Hero createhero(Users us){
        int input=0;
        int temp=20;
        int[] stat=new int[3];
        String[] statname={"生命值","攻击力","防御力"};
        boolean t=true;
        while (t||!Service.pointcheck(temp)) {
            t=false;
            temp=20;
            Ui.showcreat(us);
            for(int i=0;i<3;i++){
                System.out.print("分配点数到 "+statname[i]+"(剩余点数: "+temp+" ):");
                input=Service.getinputInt();
                System.out.println();
                temp-=input;
                if(!Service.restcheck(temp))break;
                stat[i]=input;
            }
        }
        Hero player=new Hero(us.getName(),stat[0],stat[1],stat[2]);
        Ui.heroinfo(player);
        return player ;
    }
    public static int[] battle(int battlenum,enemy enemy,Hero hero,int HP,int MP){
        Ui.battlestart(battlenum,enemy.getName());
        int heroHP=HP;
        int heroMP=MP;
        int enemyHP=enemy.getMaxHP();
        int heroATK=hero.getATK();
        int enemyATK=enemy.getATK();
        int heroDEF=hero.getDEF();
        int enemyDEF=enemy.getDEF();
        int roundnum=1;
        boolean defending=false;
        ArrayList<Skill> heroSkills=hero.getSkills();
        ArrayList<Skill> enemySkills=enemy.getSk();
        int temp=0;
        while (true){
            Ui.Roundinfo(enemy,hero,heroHP,enemyHP,hero.getMaxHP(),enemy.getMaxHP(),roundnum,heroMP,hero.getMaxMP());
            int[] temparr=Service.playermove(heroSkills,heroHP,hero,heroATK,enemyDEF,defending,enemy,heroMP,heroHP);
            temp=temparr[0];
            enemyHP-=temp;
            temp=temparr[1];
            heroHP=Math.min(heroHP+=temp,hero.getMaxHP());
            temp=temparr[2];
            heroMP-=temp;
            if (enemyHP<=0){
                return new int[]{heroHP,heroMP};
            }
            defending=false;
            temp=Service.enemymove(enemySkills,enemyHP,enemy,enemyATK,heroDEF);
            if(temp==0)defending=true;
            else heroHP-=temp;
            if (heroHP<=0){
                return new int[]{0,0};
            }
            roundnum++;
        }
    }
  public static int win(enemy enemy,int win){
        System.out.println("═══════════════════════════════════════");
        System.out.println("\uD83C\uDF89 你击败了 "+enemy.getName()+"！");
        int heal=Service.getrdint(20,40);
        System.out.println("\uD83D\uDC9A 战斗结束！你恢复了 "+heal+" 点生命值与30点魔力值");
        System.out.println("\uD83C\uDFC6 当前胜场: "+win);
        return heal;
    }

    public static void lose(enemy Enemy){
        System.out.println("很遗憾，你被敌人击败了");
        System.out.println(Enemy.getName()+"对你说: "+enemy.getBark());
    }
    public static void end(int a){
        System.out.println("═══════════════════════════════════════");
        System.out.println("游戏结束，本次你击败了 "+a+" 个敌人，感谢游玩");
        System.out.println("═══════════════════════════════════════");
    }
    public static void forget(ArrayList<Users> c){
        System.out.println("请输入用户名");
        String name=Service.getinput();
        int ex=Service.exist(name,c);
        if (ex==-1) System.out.println("用户不存在");
        else {
            while (true) {
                System.out.println("请输入绑定手机");
                String phone=Service.getinput();
                if(phone.equals(c.get(ex).getPhone())){
                    while (true) {
                        System.out.println("请输入新密码");
                        String npw=Service.getinput();
                        String temp=Service.pw(npw);
                        if (!temp.equals("error")) {
                            c.get(ex).setPw(temp);
                            System.out.println("密码修改成功");
                            if(c.get(ex).getState()==false){
                                c.get(ex).setState(true);
                                System.out.println("账号已恢复可登录状态");
                            }
                            break;
                        }
                    }
                    break;
                }else {
                    System.out.println("手机不正确");
                }
            }
        }
    }
}
