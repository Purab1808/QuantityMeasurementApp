package Com.apps.QuantityMeasurementApp.UseCase15;


import Com.apps.QuantityMeasurementApp.UseCase15.controller.Controller;
import Com.apps.QuantityMeasurementApp.UseCase15.dto.QuantityDTO;
import Com.apps.QuantityMeasurementApp.UseCase15.repo.Repository;
import Com.apps.QuantityMeasurementApp.UseCase15.service.Service;

public class Main {
    public static void main(String[] args) {

        Repository repo = Repository.getInstance();
        Service service = new Service(repo);
        Controller controller = new Controller(service);

        controller.compare(
                new QuantityDTO(1, "FEET"),
                new QuantityDTO(12, "INCHES")
        );

        controller.convert(
                new QuantityDTO(1, "KILOGRAM"),
                "GRAM"
        );

        controller.add(
                new QuantityDTO(1, "LITRE"),
                new QuantityDTO(1000, "MILLILITRE")
        );
    }
}
