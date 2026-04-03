package Com.apps.QuantityMeasurementApp.UseCase15.repo;
import Com.apps.QuantityMeasurementApp.UseCase15.entity.QuantityEntity;
import java.util.*;

public class Repository {

    private static final Repository instance = new Repository();
    private final List<QuantityEntity> data = new ArrayList<>();

    private Repository() {}

    public static Repository getInstance() {
        return instance;
    }

    public void save(QuantityEntity e) {
        data.add(e);
    }

    public List<QuantityEntity> getAll() {
        return data;
    }
}