
public class Card {
    private String name;
    private String type;

    public Card(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Card() {
        this.name = "";
        this.type = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
@Override
    public String toString() {
        return this.name + " | " + this.type;
    }
}
    

