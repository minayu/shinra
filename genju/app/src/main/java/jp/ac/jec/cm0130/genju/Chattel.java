package jp.ac.jec.cm0130.genju;

/**
 * Created by minayu on 17/03/06.
 */
public class Chattel {
    private int id;
    private String name;
    private int count;
    private int use;

    public Chattel(int id, String name, int count, int use) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.use = use;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public int getUse() {
        return use;
    }
}
