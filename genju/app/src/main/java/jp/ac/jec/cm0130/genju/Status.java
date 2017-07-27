package jp.ac.jec.cm0130.genju;

/**
 * Created by minayu on 17/03/06.
 */
public class Status {
    private int Id;
    private int Level;
    private int Stamina;
    private int Attack;
    private int Defence;
    private int Exp;

    public Status(int id, int level, int stamina, int attack, int defence, int exp) {
        Id = id;
        Level = level;
        Stamina = stamina;
        Attack = attack;
        Defence = defence;
        Exp = exp;
    }

    public int getId() {
        return Id;
    }

    public int getLevel() {
    return Level;
    }

    public int getStamina() {
    return Stamina;
    }

    public int getAttack() {
    return Attack;
    }

    public int getDefence() {
    return Defence;
    }

    public int getExp() {
    return Exp;
    }

}
