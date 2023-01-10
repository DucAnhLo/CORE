package core;

public class ChampionClass {
    String name;
    int skillLevel;
    Boolean nacromancer;
    Integer entryFee;
    String spell;
    String weapon;
    Boolean talk;


    public ChampionClass(String name, int skillLevel, Boolean nacromancer, Integer entryFee, String spell, String weapon, Boolean talk){
        this.name = name;
        this.skillLevel = skillLevel;
        this.nacromancer = nacromancer;
        if(this.nacromancer){
            this.entryFee = 400;
        }else {
            this.entryFee = entryFee;
        }
        this.spell = spell;
        this.weapon = weapon;
        if(talk == null){
            this.talk = false;

        }else {
            this.talk = talk;
            if(this.talk){
                this.entryFee = 500;
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public Boolean getNacromancer() {
        return nacromancer;
    }

    public int getEntryFee() {
        return entryFee;
    }

    public String getSpell() {
        return spell;
    }

    public String getWeapon() {
        return weapon;
    }

    public Boolean getTalk() {
        return talk;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public void setNacromancer(Boolean nacromancer) {
        this.nacromancer = nacromancer;
    }

    public void setEntryFee(int entryFee) {
        this.entryFee = entryFee;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public void setTalk(Boolean talk) {
        this.talk = talk;
    }


    @Override
    public String toString() {
        return "ChampionClass{" +
                "name='" + name + '\'' +
                ", skillLevel=" + skillLevel +
                ", nacromancer=" + nacromancer +
                ", entryFee=" + entryFee +
                ", spell='" + spell + '\'' +
                ", weapon='" + weapon + '\'' +
                ", talk=" + talk +
                '}' + "\n";
    }
}
