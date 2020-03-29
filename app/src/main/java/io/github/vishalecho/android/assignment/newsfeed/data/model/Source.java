package io.github.vishalecho.android.assignment.newsfeed.data.model;

public class Source {

    private String id;
    private String name;

    /**
     * No args constructor for use in serialization
     *
     */
    public Source() {
    }

    /**
     * @param id
     * @param name
     */
    public Source(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
