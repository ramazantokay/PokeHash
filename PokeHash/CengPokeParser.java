import java.io.*;
import java.util.ArrayList;

public class CengPokeParser {

    public static ArrayList<CengPoke> parsePokeFile(String filename) {
        ArrayList<CengPoke> pokeList = new ArrayList<CengPoke>();

        String inputLine;
        String[] parsed;

        try {
            BufferedReader brr = new BufferedReader(new FileReader(filename));
            while ((inputLine = brr.readLine()) != null) {
                parsed = inputLine.split("\\t");
                System.out.println(inputLine);
                pokeList.add(new CengPoke(Integer.parseInt(parsed[1]), parsed[2], parsed[3], parsed[4]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pokeList;
    }

    public static void startParsingCommandLine() throws IOException {

        BufferedReader brr = null;
        try {
            while (true) {

                brr = new BufferedReader(new InputStreamReader(System.in));
                String inputLine = brr.readLine();
                String[] parsed = inputLine.split("\\t");
                String command = parsed[0];

                if (command.equalsIgnoreCase("add")) {
                    CengPokeKeeper.addPoke(new CengPoke(Integer.parseInt(parsed[1]), parsed[2], parsed[3], parsed[4]));
                } else if (command.equalsIgnoreCase("quit")) {
                    System.exit(0);
                } else if (command.equalsIgnoreCase("search")) {
                    CengPokeKeeper.searchPoke(Integer.parseInt(parsed[1]));
                } else if (command.equalsIgnoreCase("delete")) {
                    CengPokeKeeper.deletePoke(Integer.parseInt(parsed[1]));
                } else if (command.equalsIgnoreCase("print")) {
                    CengPokeKeeper.printEverything();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // There are 5 commands:
        // 1) quit : End the app. Print nothing, call nothing.
        // 2) add : Parse and create the poke, and call CengPokeKeeper.addPoke(newlyCreatedPoke).
        // 3) search : Parse the pokeKey, and call CengPokeKeeper.searchPoke(parsedKey).
        // 4) delete: Parse the pokeKey, and call CengPokeKeeper.removePoke(parsedKey).
        // 5) print : Print the whole hash table with the corresponding buckets, call CengPokeKeeper.printEverything().

        // Commands (quit, add, search, print) are case-insensitive.
    }
}
