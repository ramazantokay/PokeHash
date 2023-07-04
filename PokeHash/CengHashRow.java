public class CengHashRow {

    private CengBucket bucket;
    private Integer hashPrefix;
    private Boolean isVisited;

    // GUI-Based Methods
    // These methods are required by GUI to work properly.

    public String hashPrefix() {
        // Return row's hash prefix (such as 0, 01, 010, ...)
        return Integer.toBinaryString(this.hashPrefix);
    }

    public CengBucket getBucket() {
        // Return the bucket that the row points at.
        return this.bucket;
    }

    public boolean isVisited() {
        //  Return whether the row is used while searching.
        return isVisited;
    }

    // Own Methods

    public CengHashRow() {
        bucket = new CengBucket();
        hashPrefix = 0;
        isVisited = false;
    }

    public Boolean getIsVisited() {
        return this.isVisited;
    }

    public void setIsVisited(Boolean bVisited) {
        this.isVisited = bVisited;
    }

    public void printHashRows() {
        String i;
        System.out.println("\t\"row\": {");
//        if (this.hashPrefix == 0)
//            i = "00";
//        else if (this.hashPrefix == 1)
//            i = "01";
//        else
        i = Integer.toBinaryString(this.hashPrefix);
        System.out.println("\t\t\"hashPref\": " + i + ",");
        System.out.println("\t\t\"bucket\": {");

//        for (i = 0; i < bucket.pokeCount(); i++) {

        bucket.printBucket();
//        }
//        if  (bucket.)
//        if (i >= bucket.pokeCount())
//            System.out.println("}");
//        {
//
//        }
    }

    public void setHashPrefix(Integer val) {
        this.hashPrefix = val;
    }

    public void setBucket(CengBucket bucket) {
        this.bucket = bucket;
    }
}
