package hiber.dao;

import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoimp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    public CarDaoimp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);

    }

    @Override
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car", Car.class);
        return query.getResultList();
    }

}
