import java.util.HashSet;
import java.util.Set;

public class DramaDriver {


    public static void main(String[] args){
        Meanie Karen = new Meanie("Karen", 0);
        Meanie Gretchen = new Meanie("Gretchen", 1);
        Meanie Regina = new Meanie("Regina", 2);
        Meanie Katie = new Meanie("Katie", 3);
        NorthShore shore = new NorthShore();
        shore.addMeanie(Katie, null, false);
        shore.addMeanie(Karen, null, false);
        shore.addMeanie(Gretchen, new HashSet<>(Set.of(Karen)), false);
        shore.addMeanie(Regina, new HashSet<>(Set.of(Karen, Gretchen, Katie)), false);
        System.out.println(shore);
        shore.connect(Katie, Gretchen, true);
        System.out.println(shore);
        Meanie MrM = new Meanie("MrM", 4);
        shore.connect(Katie, MrM, false);
        System.out.println(shore);
        //Break up Regina and Katie
        shore.ghost(Regina, Katie, true);
    }
}
