package core;

import java.util.Objects;

public class Champion {
    String name;
    ChampionClass champClass;

    ChampionState state;

    public Champion(String name, ChampionClass champClass, ChampionState st) {
        this.name = name;
        this.champClass = champClass;
        this.state = st;
    }

    public Champion(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Champion champion = (Champion) o;
        return Objects.equals(name, champion.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChampionClass getChampClass() {
        return champClass;
    }

    public void setChampClass(ChampionClass champClass) {
        this.champClass = champClass;
    }

    public ChampionState getState() {
        return state;
    }

    public void setState(ChampionState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Champion{" +
                "name='" + name + '\'' +
                ", champClass=" + champClass +
                ", state=" + state +
                '}';
    }
}
