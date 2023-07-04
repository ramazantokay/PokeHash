import java.util.ArrayList;
import java.util.List;

public class CengBucket {

    // GUI-Based Methods
    // These methods are required by GUI to work properly.

    private List<CengPoke> pokemonList; // CengPoke bucket
    private Integer defaultBucketSize; // default bucket size
    private Integer hashPrefixLen; // local depth
    private boolean isVisited;

    public int pokeCount() {
        // Return the pokemon count in the bucket.
        return this.pokemonList.size();
    }

    public CengPoke pokeAtIndex(int index) {
        // Return the corresponding pokemon at the index.

        if (index < this.pokeCount())
            return pokemonList.get(index);
        else {
            System.out.println("Hoop hemserim nereye!");
            return null;
        }
    }

    public int getHashPrefix() {
        //  Return hash prefix length.
        return hashPrefixLen;
    }

    public Boolean isVisited() {
        // Return whether the bucket is found while searching.
        return isVisited;
    }

    // Own Methods

    public CengBucket() {
        this.defaultBucketSize = CengPokeKeeper.getBucketSize();
        this.pokemonList = new ArrayList<CengPoke>(this.defaultBucketSize);
        this.hashPrefixLen = 0;
        this.isVisited = false;
    }

    public void incHashPrefLen() {
        this.hashPrefixLen++;
    }

    public void insertPoke(CengPoke data) {
        this.pokemonList.add(data);
    }

    public void deletePoke(CengPoke data) {
        this.pokemonList.remove(data);
    }

    public Boolean isBucketFull() {
        return (this.pokeCount() >= defaultBucketSize);
    }

    public Boolean getIsVisited() {
        return this.isVisited;
    }

    public void setIsVisited(Boolean bVisited) {
        this.isVisited = bVisited;
    }

    public List<CengPoke> getPokemonList() {
        return this.pokemonList;
    }

    public void setHashPrefixLen(Integer val) {
        this.hashPrefixLen = val;
    }

    public void printBucket() {
//        System.out.println("\t\t\"bucket\": {");
        System.out.println("\t\t\t\"hashLength\": " + this.hashPrefixLen + ",");
        System.out.println("\t\t\t\"pokes\": [");

        for (int i = 0; i < pokemonList.size(); i++) {
//            System.out.println("\t \t");
            pokeAtIndex(i).printCengPoke();
            if ((pokemonList.size() - 1) == i)
                System.out.println("\t\t\t\t}");
            else
                System.out.println("\t\t\t\t},");

            // cengpoke print
        }
        System.out.println("\t\t\t]");
        System.out.println("\t\t}");
    }
//    public Boolean insertPokemon(CengPoke data) {
//
//        // Check if the data is full and unique
//        if (sizePokemonList != bucketSize && !pokemonList.contains(data)) {
//            pokemonList.add(data);
//            sizePokemonList++;
//        } else {
//            return false;
//        }
//
//        return true;
//    }

}
