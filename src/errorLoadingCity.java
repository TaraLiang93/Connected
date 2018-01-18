/**
 * Created by tara on 1/17/18.
 */
public class errorLoadingCity extends Exception{
    public errorLoadingCity(String cities){
        super("Error Loading "+cities);
    }
}
