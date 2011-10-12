import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author claus
 */
public class Example1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Jeg er født i Rødovre, æøåÆØÅ.");
        Set<String> s = new HashSet<String>();
        
        for (int i = 0; i < args.length; i++) {
            if (!s.add(args[i]))
                System.out.println("Duplicate: " + args[i]);
        }
		System.out.print(s.size() + " disctinct words: ");
		
		Iterator it = s.iterator();
		
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		
		System.out.println(".");
    }
}
