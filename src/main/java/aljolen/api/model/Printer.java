package aljolen.api.model;

public class Printer {

    private final String name;
    private final String url;
    private final String description;
    private final String location;

    public Printer(String name, String url, String description, String location) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Printer{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
