package dataobject;

public class WeatherData {

    private String lon;
    private String lat;
    private String description;

    public WeatherData(String lon,String lat,String description){
        this.lon = lon;
        this.lat = lat;
        this.description = description;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
