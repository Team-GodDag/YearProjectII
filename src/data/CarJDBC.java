package data;

import entities.CarEntity;

import java.util.ArrayList;

public interface CarJDBC {
    ArrayList<CarEntity> getAllCarModels();

    boolean addNewCarModel(CarJDBCimpl carJDBCimpl);

    boolean deleteCarModel(CarJDBCimpl carJDBCimpl);

    boolean updateCarModel(CarJDBCimpl carJDBCimpl);

    ArrayList<CarEntity> getCarModelsByCondition(String condition);
}
