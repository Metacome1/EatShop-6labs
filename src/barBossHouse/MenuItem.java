package barBossHouse;


public abstract class MenuItem implements java.lang.Comparable<MenuItem> {
    //todo поля приватные не? COMPLETE
    private final static int DEFAULT_COST = 0;
    private String name;
    private int cost;
    private String description;

    protected MenuItem(String dishName, String dishDescription) {
        this(dishName, dishDescription, DEFAULT_COST);
    }

    protected MenuItem(String dishName, String dishDescription, int dishCost) {
        if (dishCost < 0) throw new IllegalArgumentException("Вам не будут платить за еду");
        name = dishName;
        description = dishDescription;
        cost = dishCost;
    }


    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(MenuItem o) {
        //todo return o.cost - this.cost не? COMPLETE
        return o.cost - this.cost;
    }

    //todo Забыли тудушку поставить. Фиговый формат, переделывай COMPLETE
    @Override
    public String toString() {
        return String.format("{0}, {1, current} {2}",
                name,
                cost,
                description);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        MenuItem menuItem = (MenuItem) obj;

        return (name.equals(menuItem.getName()) && cost == menuItem.getCost());
    }

    @Override
    public int hashCode() {
        return name.hashCode()
                ^ cost;
    }
}