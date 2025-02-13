package com.MarvelMan.JournalApplication.api.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class WeatherResponse {
    public Current current;

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public class Current{
        public int temperature;
        @JsonProperty("weather_descriptions")
        public ArrayList<String> weatherDescriptions;
        public String is_day;

        public int getTemperature() {
            return temperature;
        }

        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }

        public ArrayList<String> getWeatherDescriptions() {
            return weatherDescriptions;
        }

        public void setWeatherDescriptions(ArrayList<String> weatherDescriptions) {
            this.weatherDescriptions = weatherDescriptions;
        }

        public String getIs_day() {
            return is_day;
        }

        public void setIs_day(String is_day) {
            this.is_day = is_day;
        }
    }

}



