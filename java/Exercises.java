import java.util.*;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.toList;
import java.lang.Math;
import java.util.List;
import java.util.function.IntConsumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.Optional;

public class Exercises {
    private Exercises() {
        //Intentionally empty
    }

    public static List<Integer> change(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount must not be negative!");
        }
        int quarter = amount / 25;
        amount -= quarter * 25;
        int dime = amount / 10;
        amount -= dime * 10;
        int nickle = amount / 5;
        amount -= nickle * 5;
        int penny = amount;

        return List.of(quarter, dime, nickle, penny);
    }

    public static String stretched(String input) {
    // https://stackoverflow.com/a/11049132 - to remove whitespace using regex
    input = input.replaceAll("[\t\n\r\s]", "");
    
    String result = "";
    //Used classnotes and Java Documentation to learn about streams.
    IntStream stream = input.codePoints();
    int[] items = stream.toArray();
    for(int i = 0; i < items.length; i++){
      for(int j = 0; j <= i; j++){
        result += Character.toString(items[i]);
      }
    }
    return result;
    }

    public static void powers(int base, int limit, IntConsumer consume) {
        for (int power = 1; power <= limit; power *= base) {
            consume.accept(power);
        }
    }

    public static IntStream powers(int base) {
      return IntStream.iterate(0, x -> x + 1).map(x -> (int)Math.pow(base, x));
    }

    public class Chainable {
      private String word;

      public Chainable(String theWord) {
        this.word = theWord;
      }

      public Chainable and(String input) {
        this.word = this.word + " " + input;
        return this;
      }

      public String ok() {
        return this.word;
      }

    }

    public static Exercises.Chainable say(String word) {
      Exercises ex = new Exercises();
      return ex.new Chainable(word);
    } 

    public static String say() {
      return "";
    }

    public static Optional<String> findFirstThenLower(Predicate<String> property, List<String> words) {
      return words.stream()
          .filter(property)
          .findFirst()
          .map(String::toLowerCase);
    }
    
    public static List<String> topTenScorers(Map<String, List<String>> data) {
      record Player(String name, String team, int games, int points) { 
        double pointAverage() {return (double) points / games; } 
      }
      return data.entrySet().stream() 
        .flatMap(e -> e.getValue().stream().map(player -> player + "," + e.getKey())) 
        .map(line -> line.split(","))
        .map(p -> new Player(p[0], p[3], Integer.parseInt(p[1]), Integer.parseInt(p[2])))
        .filter(x -> x.games >= 15) 
        .sorted((p1, p2) -> Double.compare(p2.pointAverage(), p1.pointAverage())) 
        .limit(10) 
        .map(p -> p.name() + "|" + String.format("%.2f", p.pointAverage()) + "|" + p.team())
        .collect(toList()); 







    }

    
}
