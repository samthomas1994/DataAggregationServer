package uk.ac.bath.classes;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sam on 03/02/2016.
 */
@Entity
@Table(name = "ACTIVITY")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="source")
    private String source;

    @Column(name="category")
    private String category;

    @Column(name="type")
    private String type;

    @Column(name="units")
    private String units;

    public Activity() {

    }

    public Activity(String source, String category, String type, String units) {
        this.source = source;
        this.category = category;
        this.type = type;
        this.units = units;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return String.format(
                "UserDetails[id=%d, source='%s', category='%s', type='%s', units='%s']",
                id, source, category, type, units);
    }
}
