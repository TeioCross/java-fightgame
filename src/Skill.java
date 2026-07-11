public class Skill {
    private String name;
    private String effect;
    private double power;
    private int special;
    public Skill(String name, String effect,double power,int special) {
        this.name = name;
        this.effect = effect;
        this.power = power;
        this.special = special;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }


    public int getSpecial() {
        return special;
    }

    public void setSpecial(int special) {
        this.special = special;
    }
}



