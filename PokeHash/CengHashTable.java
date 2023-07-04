import java.util.ArrayList;

public class CengHashTable {

    private CengHashRow[] directory;
    public Integer prefixBitCount; // global depth
    private Integer directorySize;
    private Integer hashModVal;
    private Integer noEmptyBucket;
    // hashing function

    public CengHashTable() {
        // TODO: Create a hash table with only 1 row.
        this.directory = new CengHashRow[1];
        this.directory[0] = new CengHashRow();
        this.prefixBitCount = 0;
        this.directorySize = 1;
        this.hashModVal = CengPokeKeeper.getHashMod();
        this.noEmptyBucket = 0;
//        System.out.println("CengHashTable Created");
    }

    public void deletePoke(Integer pokeKey) {
        // TODO: Empty Implementation

        Boolean isExists = false;
        Integer flag = 0;
//        System.out.println("Delete Entering");

        Integer hashValue = pokeKey % hashModVal;
        Integer shiftAmount = (int) ((Math.log(hashModVal) / Math.log(2)) - (this.prefixBitCount));
        Integer index = ((hashValue >> shiftAmount) % ((int) Math.pow(2, this.prefixBitCount)));

        for (int i = 0; i < this.directory[index].getBucket().getPokemonList().size(); i++) {
            if (pokeKey == this.directory[index].getBucket().pokeAtIndex(i).pokeKey()) {
                isExists = true;
                flag = i;
                break;
            }
        }
        if (isExists) {

            this.directory[index].getBucket().deletePoke(this.directory[index].getBucket().pokeAtIndex(flag));
            Integer sizeofBuckets = this.directory[index].getBucket().pokeCount();
            if (sizeofBuckets == 0)
                noEmptyBucket++;

            System.out.println("\"delete\": {");
            System.out.println("\t\"emptyBucketNum\": " + noEmptyBucket);
            System.out.println("{");

        }
    }

    public void addPoke(CengPoke poke) {

        Integer pokeKey = poke.pokeKey();
        Integer hashValue = pokeKey % hashModVal;
        Integer shiftAmount = (int) ((Math.log(hashModVal) / Math.log(2)) - (this.prefixBitCount));
        Integer index = ((hashValue >> shiftAmount) % ((int) Math.pow(2, this.prefixBitCount)));

//        System.out.println("PokeKey --> " + pokeKey);
//        System.out.println("hashValue --> " + hashValue);
//        System.out.println("shiftAmount --> " + shiftAmount);
//        System.out.println("index --> " + index);
//
//        System.out.println("Add Poke ---> will be in : " + index + "  " + prefixBitCount);
        if (this.directory[index].getBucket().isBucketFull()) {

            if ((this.prefixBitCount) == (directory[index].getBucket().getHashPrefix())) { // doubleTable
                doubleTable(poke, index);
            } else { //split buckets
                splitBuckets(poke, index, directory[index].getBucket().getHashPrefix());
            }
        } else {
            if (noEmptyBucket != 0 && this.directory[index].getBucket().pokeCount() == 0)
                noEmptyBucket--;

            this.directory[index].getBucket().insertPoke(poke);

        }
    }

    public void doubleTable(CengPoke data, int index) {
        ArrayList<CengPoke> pokemons = new ArrayList<CengPoke>();
        CengHashRow[] tempTable = new CengHashRow[directorySize * 2];
        for (int i = 0; i < directorySize; i++) {
            int tempVal = i * 2;
            if (i == index) {
                tempTable[tempVal] = new CengHashRow();
                tempTable[tempVal + 1] = new CengHashRow();
                tempTable[tempVal].setHashPrefix(tempVal);
                tempTable[tempVal + 1].setHashPrefix(tempVal + 1);
                tempTable[tempVal].getBucket().setHashPrefixLen(directory[index].getBucket().getHashPrefix() + 1);
                tempTable[tempVal + 1].getBucket().setHashPrefixLen(directory[index].getBucket().getHashPrefix() + 1);
                for (int j = 0; j < directory[index].getBucket().pokeCount(); j++) {
                    pokemons.add(directory[index].getBucket().pokeAtIndex(j));
                }

            } else {
                tempTable[tempVal] = new CengHashRow();
                tempTable[tempVal + 1] = new CengHashRow();
                tempTable[tempVal].setBucket(directory[i].getBucket());
                tempTable[tempVal + 1].setBucket(directory[i].getBucket());
                tempTable[tempVal].setHashPrefix(tempVal);
                tempTable[tempVal + 1].setHashPrefix(tempVal + 1);

            }
        }
        this.directory = new CengHashRow[directorySize * 2];
        this.directory = tempTable;
        this.directorySize *= 2;
        this.incPrefixBitCount();
        pokemons.add(data);
        for (int i = 0; i < pokemons.size(); i++) {
            this.addPoke(pokemons.get(i));
        }
    }

    public void splitBuckets(CengPoke data, int index, int prefLen) {

        ArrayList<CengPoke> pokemons = new ArrayList<CengPoke>();

        Integer modVal = (int) Math.pow(2, ((this.prefixBitCount) - (prefLen)));
        Integer sIndex = index - (index % modVal);
        Integer eIndex = sIndex + modVal;

        CengBucket tempBucket = new CengBucket();
        CengBucket tempBucket2 = new CengBucket();
        tempBucket.setHashPrefixLen(prefLen + 1);
        tempBucket2.setHashPrefixLen(prefLen + 1);

        for (int j = 0; j < directory[index].getBucket().pokeCount(); j++) {
            pokemons.add(directory[index].getBucket().pokeAtIndex(j));
        }
        for (int i = 0; i < directorySize; i++) {
            if (i >= sIndex && i < (eIndex + sIndex) / 2) {
                directory[i].setBucket(tempBucket);
            } else if (i < eIndex && i >= (eIndex + sIndex) / 2) {
                directory[i].setBucket(tempBucket2);
            } else {
                continue;
            }
        }
        pokemons.add(data);
        for (int i = 0; i < pokemons.size(); i++) {
            this.addPoke(pokemons.get(i));
        }

    }

    public void searchPoke(Integer pokeKey) {
        // TODO: Empty Implementation
        Boolean isExists = false;
//        System.out.println("Seach Enter");

        Integer hashValue = pokeKey % hashModVal;
        Integer shiftAmount = (int) ((Math.log(hashModVal) / Math.log(2)) - (this.prefixBitCount));
        Integer index = ((hashValue >> shiftAmount) % ((int) Math.pow(2, this.prefixBitCount)));

        for (int i = 0; i < this.directory[index].getBucket().getPokemonList().size(); i++) {
            if (pokeKey == this.directory[index].getBucket().pokeAtIndex(i).pokeKey()) {
                isExists = true;
                break;
            }
        }
        if (isExists) {
            System.out.println("\"search\": {");
            for (int i = 0; i < this.directory[index].getBucket().pokeCount(); i++) {
                this.directory[index].printHashRows();
                if (this.directory[index].getBucket().pokeCount() - 1 == i)
                    System.out.println("\t}");
                else
                    System.out.println("\t},");
            }

            System.out.println("}");

        } else {
            System.out.println("\"search\": {");
            System.out.println("}");

        }

    }

    public void print() {
        // TODO: Empty Implementation

        System.out.println("\"table\": {");
        for (int i = 0; i < directorySize; i++) {
            directory[i].printHashRows();

            if ((directorySize - 1) == i)
                System.out.println("\t}");
            else
                System.out.println("\t},");

        }
        System.out.println("}");

    }

    // GUI-Based Methods
    // These methods are required by GUI to work properly.

    public int prefixBitCount() {
        // TODO: Return table's hash prefix length.
        return prefixBitCount;
    }

    public int rowCount() {
        // TODO: Return the count of HashRows in table.
        return directorySize;
    }

    public CengHashRow rowAtIndex(int index) {
        // TODO: Return corresponding hashRow at index.
        return directory[index];
    }

    // Own Methods

    public void incPrefixBitCount() {
        this.prefixBitCount++;
    }

}
