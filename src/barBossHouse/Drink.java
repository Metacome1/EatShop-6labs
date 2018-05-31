package barBossHouse;

final public class Drink extends MenuItem implements Alcoholable {
     private double alcoholVol;
     private DrinkTypeEnum type;

    public Drink(String dishName, String dishDescription) {
        super(dishName, dishDescription);
    }

    public Drink(String dishName, String dishDescription, int dishCost) {
        super(dishName, dishDescription, dishCost);
    }

    public Drink(String dishName, DrinkTypeEnum type){
        super(dishName, "", 0);
        this.type = type;
    }

    public Drink(String dishName, String dishDescription, int dishCost, double alcoholVole, DrinkTypeEnum type) {
        super(dishName, dishDescription, dishCost);
        if(alcoholVole < 0 && alcoholVole> 100) throw new IllegalArgumentException("Слишком много или мало градусов у алкоголя");
        this.type = type;
        this.alcoholVol = alcoholVole;
    }

    @Override
    public double getAlcoholVol() {
        return alcoholVol;
    }

    @Override
    public boolean isAlcoholicDrink() {
        if (alcoholVol> 0) return true;
        else return false;
    }

    public Drink(String dishName, String dishDescription, int dishCost, DrinkTypeEnum type, double alcoholVol) {
        super(dishName, dishDescription, dishCost);
        this.type = type;
        this.alcoholVol = alcoholVol;
    }

    public DrinkTypeEnum getType(){
        return type;
    }

    @Override
    public String toString() {
        /* todo это не форматный вывод, это какая-то фигня. COMPLETE
        Здесь нафиг не нужно заморачиваться на значениях полей,
        а все запятые, приписки рублей и проценты - в строке формата надо поместить.
        И не забудь правильные спецификаторы форматов использовать */
        return String.format("%s %s %s, %dр. %s: %f.2%% %s",
                (getClass().getSimpleName()),
                type,
                getName(),
                getCost(),
                Alcoholable.class.getSimpleName(),
                getAlcoholVol(),
                getDescription());
    }

    @Override
    public boolean equals(Object obj){
        return (super.equals(obj) && getDescription().equals(((MenuItem) obj).getDescription()));
    }

    @Override
    public int hashCode(){
        return  super.hashCode()
                ^getType().hashCode()
                ^Double.valueOf(getAlcoholVol()).hashCode();
    }



}
