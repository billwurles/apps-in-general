package wburles.uk.sqlite;

/**
 * Created by Will on 07/03/2016.
 */
public class Bird {
    String binomialName;
    String commonName;

    public Bird(String binomialName, String commonName){
        this.binomialName = binomialName;
        this.commonName = commonName;
    }

    public String getBinomialName() {
        return binomialName;
    }

    public String getCommonName() {
        return commonName;
    }

    @Override
    public String toString(){
        return commonName + " (" + binomialName + ")";
    }
}
