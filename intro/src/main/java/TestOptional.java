import java.util.Optional;

/**
 * Created by Freemind on 2016-11-04.
 */
public class TestOptional {

    public static void main(String[] args) {
        String s="sss";

        Optional<String> sq=Optional.of(s);

        Optional flatMapped=sq.flatMap(val->Optional.of(val+" flat"));

        System.out.println(flatMapped.get());

        Optional mapped=sq.map(val->val+"map");

        System.out.println(mapped.get());






    }
}
