package data;

import entities.CarEntity;

import java.util.ArrayList;

public interface CarJDBC {
    ArrayList<CarEntity> getAllCarModels();

    boolean addCar(CarJDBCimpl carJDBCimpl);

    boolean deleteCar(CarJDBCimpl carJDBCimpl);

    boolean updateCar(CarJDBCimpl carJDBCimpl);

    ArrayList<CarEntity> getCarsByCondition(String condition);
}
