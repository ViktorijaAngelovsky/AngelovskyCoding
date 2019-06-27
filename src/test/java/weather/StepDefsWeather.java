package weather;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.eo.Do;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import weather.model.Response;
import weather.model.Weather;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StepDefsWeather {
    private String county;
    private String city;
    private Response response;

    @Given("country: ([a-z]*)")
    public void add_country(String country) {
        this.county = country;
    }

    @Given("city: (.*)")
    public void add_city(String city) {
        this.city = city;
    }

    @When("we are request weather data")
    public void request_data() throws IOException {
        WeatherRequester weatherRequester = new WeatherRequester();
        response = weatherRequester.request(county, city);
    }

    @Then("lon is (.*)")
    public void check_lon(Double lon) {
        Assertions.assertEquals(lon, response.getCoord().getLon(), "Lon correct!");
    }

    @Then("lat is (.*)")
    public void check_lat(Double lat) {
        Assertions.assertEquals(lat, response.getCoord().getLat(), "Lat correct!");
    }

    @Then("weather data:")
    public void check_weather(Map<String, String> data) {
        Assertions.assertEquals(data.get("id"), response.getWeathers().get(0).getId());
        Assertions.assertEquals(data.get("main"), response.getWeathers().get(0).getMain());
        Assertions.assertEquals(data.get("description"), response.getWeathers().get(0).getDescription());
        Assertions.assertEquals(data.get("icon"), response.getWeathers().get(0).getIcon());
    }

//         VARIANT 2
//        public void check_weather(HashMap<String, String> data) {
//            Weather weather = response.getWeathers().get(0);
//            Assertions.assertEquals(data.get("id"), weather.getId());
//            Assertions.assertEquals(data.get("main"), weather.getMain());
//            Assertions.assertEquals(data.get("description"), weather.getDescription());
//            Assertions.assertEquals(data.get("icon"), weather.getIcon());
//
//    }


    //    VARIANT 3
//        @Then("id is (.*)")
//        public void check_id(Double id) {
//            Assertions.assertEquals(id, response.getWeathers().get(0), "Id correct!");
//        }
//
//        @Then("main is (.*)")
//        public void check_main (String main) {
//            Assertions.assertEquals(main, response.getWeathers().get(0), "Main correct!");
//        }
//
//        @Then("description is (.*)")
//        public void check_description (String description) {
//            Assertions.assertEquals(description, response.getWeathers().get(0), "Description correct!");
//        }
//
//        @Then("icon is (.*)")
//        public void check_icon (String icon){
//            Assertions.assertEquals(icon, response.getWeathers().get(0), "Icon correct!");
//
//        }

    @Then("base is (.*)")
    public void check_base(String base) {
        Assertions.assertEquals(base, response.getBase().getStations());
    }

    @Then("temp is (.*)")
    public void check_temp(Double temp) {
        Assertions.assertEquals(temp, response.getMain().getTemp());
    }

    @Then("pressure is (.*)")
    public void check_pressure(Double pressure) {
        Assertions.assertEquals(pressure, response.getMain().getPressure());
    }

    @Then("humidity is (.*)")
    public void check_humidity(Double humidity) {
        Assertions.assertEquals(humidity, response.getMain().getHumidity());
    }

    @Then("temp_min is (.*)")
    public void check_temp_min(Double temp_min) {
        Assertions.assertEquals(temp_min, response.getMain().getTemp_min());
    }

    @Then("temp_max is (.*)")
    public void check_temp_max(Double temp_max) {
        Assertions.assertEquals(temp_max, response.getMain().getTemp_max());
    }

    @Then("visibility is (.*)")
    public void check_visibility(Double visibility) {
        Assertions.assertEquals(visibility, response.getVisibility().getVisibility());
    }

    @Then("speed is (.*)")
    public void check_wind_speed(Double speed) {
        Assertions.assertEquals(speed, response.getWind().getSpeed());
    }

    @Then("deg is (.*)")
    public void check_wind_deg(Double deg) {
        Assertions.assertEquals(deg, response.getWind().getDeg());
    }

    @Then("all_clouds (.*)")
    public void check_clouds(Double clouds) {
        Assertions.assertEquals(clouds, response.getAll_clouds().getAll_clouds());
    }

    @Then("dt is (.*)")
    public void check_dt(Double dt) {
        Assertions.assertEquals(dt, response.getDt().getDt());
    }

    @Then("type is (.*)")
    public void check_type(Integer type) {
        Assertions.assertEquals(type, response.getSystem().getType());
    }

    @Then("id is (.*)")
    public void check_sys_id(Double sysId) {
        Assertions.assertEquals(sysId, response.getSystem().getId());
    }

    @Then("message is (.*)")
    public void check_message(Double message) {
        Assertions.assertEquals(message, response.getSystem().getMessage());
    }

    @Then("country is (.*)")
    public void check_country(String country) {
        Assertions.assertEquals(country, response.getSystem().getCountry());
    }

    @Then("sunrise is (.*)")
    public void check_sunrise(Double sunrise) {
        Assertions.assertEquals(sunrise, response.getSystem().getSunrise());
    }

    @Then("sunset is (.*)")
    public void check_sunset(Double sunset) {
        Assertions.assertEquals(sunset, response.getSystem().getSunset());
    }

    @Then("other_id is (.*)")
    public void check_other_id(Double other) {
        Assertions.assertEquals(other, response.getOther_id().getOther_id());
    }

    @Then("name is (.*)")
    public void check_name(String cityname) {
        Assertions.assertEquals(cityname, response.getName().getName());
    }

    @Then("cod is (.*)")
    public void check_cod(Double code) {
        Assertions.assertEquals(code, response.getCod().getCod());
    }
}


