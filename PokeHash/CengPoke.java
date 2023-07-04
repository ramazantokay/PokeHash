public class CengPoke {

    private Integer pokeKey;

    private String pokeName;
    private String pokePower;
    private String pokeType;

    public CengPoke(Integer pokeKey, String pokeName, String pokePower, String pokeType) {
        this.pokeKey = pokeKey;
        this.pokeName = pokeName;
        this.pokePower = pokePower;
        this.pokeType = pokeType;
    }

    // Getters

    public Integer pokeKey() {
        return pokeKey;
    }

    public String pokeName() {
        return pokeName;
    }

    public String pokePower() {
        return pokePower;
    }

    public String pokeType() {
        return pokeType;
    }

    // GUI method - do not modify
    public String fullName() {
        return "" + pokeKey() + "\t" + pokeName() + "\t" + pokePower() + "\t" + pokeType;
    }

    // Own Methods

    public void printCengPoke() {
        String temp;
        int hash = this.pokeKey % CengPokeKeeper.getHashMod();

//        if (hash == 0)
//            temp = "000";
//        else if (hash == 1)
//            temp = "001";
//        else if (hash == 2)
//            temp = "010";
//        else
            temp = Integer.toBinaryString(hash);


        System.out.println("\t\t\t\t\"poke\": {");
        System.out.println("\t\t\t\t\t\"hash\": " + temp + ",");
        System.out.println("\t\t\t\t\t\"pokeKey\": " + this.pokeKey + ",");
        System.out.println("\t\t\t\t\t\"pokeName\": " + this.pokeName + ",");
        System.out.println("\t\t\t\t\t\"pokePower\": " + this.pokePower + ",");
        System.out.println("\t\t\t\t\t\"pokeType\": " + this.pokeType);
    }
}
