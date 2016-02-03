package uk.ac.bath.classes;

/**
 * Created by Sam on 03/02/2016.
 */
public class Activity {

    private Integer id = null;
    private String source = null;
    private String category = null;
    private String type = null;
    private String units = null;

    public Activity() {

    }

    public Activity(String source, String category, String type, String units) {
        this.source = source;
        this.category = category;
        this.type = type;
        this.units = units;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public String getSource() {
        return source;
    }

    public String getType() {
        return type;
    }

    public String getUnits() {
        return units;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
