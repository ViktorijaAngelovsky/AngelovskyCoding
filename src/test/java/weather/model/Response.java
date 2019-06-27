package weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
//отсутствует field weather (ili ljuboje drugoje pole) (test заваливается), чтобы игнорировал

public class Response {
    private Coord coord;
    @JsonProperty("weather") //4tobi raspoznaval field "weather" kak v json. identi4noje napisanije
    private List<Weather> weathers;
    private Base base;
    private Main main;
    private Visibility visibility;
    private Wind wind;
    @JsonProperty("all")
    private Clouds all_clouds;
    private Dt dt;
    @JsonProperty("sys")
    private Sys system;
    @JsonProperty("id")
    private OtherId other_id;
    private Name name;
    private Cod cod;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }


    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }


    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }


    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }


    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }


    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }


    public Clouds getAll_clouds() {
        return all_clouds;
    }

    public void setAll_clouds(Clouds all_clouds) {
        this.all_clouds = all_clouds;
    }


    public Dt getDt() {
        return dt;
    }

    public void setDt(Dt dt) {
        this.dt = dt;
    }


    public Sys getSystem() {
        return system;
    }

    public void setSystem(Sys system) {
        this.system = system;
    }


    public OtherId getOther_id() {
        return other_id;
    }

    public void setOther_id(OtherId other_id) {
        this.other_id = other_id;
    }


    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }


    public Cod getCod() {
        return cod;
    }

    public void setCod(Cod cod) {
        this.cod = cod;
    }
}
