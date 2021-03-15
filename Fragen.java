package ubb;

import java.util.List;
public class Fragen {
    int ID;
    String frage;
    String antwort1;
    String antwort2;
    String antwort3;
    String richtigeAntw;


    public Fragen(int ID, String frage, String antwort1, String antwort2, String antwort3, String richtige_antw) {
        this.ID = ID;
        this.frage = frage;
        this.antwort1 = antwort1;
        this.antwort2 = antwort2;
        this.antwort3 = antwort3;
        this.richtigeAntw = richtige_antw;
    }

    public int getID() { return ID; }

    public void setID(int ID) { this.ID = ID; }

    public String getFrage() { return frage; }

    public void setFrage(String frage) { this.frage = frage; }

    public String getAntwort1() { return antwort1; }

    public void setAntwort1(String antwort1) { this.antwort1 = antwort1; }

    public String getAntwort2() { return antwort2; }

    public void setAntwort2(String antwort2) { this.antwort2 = antwort2; }

    public String getAntwort3() { return antwort3; }

    public void setAntwort3(String antwort3) { this.antwort3 = antwort3; }

    public String getRichtigeAntw() { return richtigeAntw; }

    public void setRichtigeAntw(String richtigeAntw) { this.richtigeAntw = richtigeAntw; }

    @Override
    public String toString() {
        return "Fragen{" +
                "ID=" + ID +
                ", frage='" + frage + '\'' +
                ", antwort1='" + antwort1 + '\'' +
                ", antwort2='" + antwort2 + '\'' +
                ", antwort3='" + antwort3 + '\'' +
                ", richtigeAntw=" + richtigeAntw +
                '}';
    }
}
