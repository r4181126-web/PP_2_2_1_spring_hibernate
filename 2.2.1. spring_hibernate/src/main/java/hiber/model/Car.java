package hiber.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "car")
    private User user;

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private int series;

    public Car() {}
    public Car(String model, int series) {
        this.model = model;
        this.series = series; }

    public Long getId() { return id; }
    public void setId(Long id) {this.id = id; }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public int getSeries() { return series; }
    public void setSeries(int series) { this.series = series;}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return series == car.series && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, series);
    }
}
