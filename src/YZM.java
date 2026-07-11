import java.util.Random;

public class YZM {
    static Random rd=new Random();
    private static final char[] CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static boolean verify(String yzm, String yzmin){
        return yzmin.equalsIgnoreCase(yzm);
    }
    public static String createYZM(){
        String yzm="";
        for (int i = 0; i < 4; i++) {
            yzm+=CHARS[rd.nextInt(CHARS.length)];
        }
        yzm+=rd.nextInt(0, 10);
        char[] array = yzm.toCharArray();
        char temp=array[4];
        int t=rd.nextInt(0,4);
        array[4]=array[t];
        array[t]=temp;
        yzm=new String(array);
        return yzm;
    }
}
