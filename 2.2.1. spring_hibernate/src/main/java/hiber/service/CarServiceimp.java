package hiber.service;

import hiber.dao.CarDao;
import hiber.model.Car;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class CarServiceimp implements CarService {

    private final CarDao carDao;

    public CarServiceimp(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    @Transactional
    public void add(Car car) {
    carDao.add(car);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> listCars() {
        return carDao.listCars();
    }
}
