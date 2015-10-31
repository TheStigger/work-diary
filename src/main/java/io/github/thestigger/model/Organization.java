package io.github.thestigger.model;

/**
 * Created by maxim on 10/14/15.
 */
public class Organization {
    private String id;
    private String name;
    private Contact director;
    private String description;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Contact getDirector() {
        return director;
    }

    public void setDirector(Contact director) {
        this.director = director;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
