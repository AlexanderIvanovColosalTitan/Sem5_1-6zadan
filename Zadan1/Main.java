import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

class Composition {
    private final List<Instrument> instruments = new ArrayList<>();
    private String name;

    public Composition(String name) {
        this.name = name;
    }

    public void addInstrument(Instrument instrument) { instruments.add(instrument); }
    public void removeInstrument(Instrument instrument) { instruments.remove(instrument); }
    public List<Instrument> getInstruments() { return instruments; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

abstract class Instrument {
    protected String name;
    protected int volume, tempo;

    public Instrument(String name, int volume, int tempo) {
        this.name = name;
        this.volume = volume;
        this.tempo = tempo;
    }

    public int getVolume() { return volume; }
    public void setVolume(int volume) { this.volume = volume; }
    public int getTempo() { return tempo; }
    public void setTempo(int tempo) { this.tempo = tempo; }
}

class Guitar extends Instrument { public Guitar() { super("Гитара", 50, 120); } }
class Fortepiano extends Instrument { public Fortepiano() { super("Фортепиано", 50, 120); } }
class Skripka extends Instrument { public Skripka() { super("Скрипка", 50, 120); } }


