package org.apps.quantitymeasurement.repository;

import org.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import org.apps.quantitymeasurement.exception.DatabaseException;
import org.apps.quantitymeasurement.util.ConnectionPool;

import java.sql.*;
import java.util.*;

public class QuantityMeasurementDatabaseRepository
        implements IQuantityMeasurementRepository {

    private ConnectionPool pool = new ConnectionPool();

    @Override
    public void save(QuantityMeasurementEntity entity) {
        String sql = "INSERT INTO measurement(type, operation, measurement_value, timestamp) VALUES (?, ?, ?, ?)";

        Connection con = pool.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, entity.getType());
            ps.setString(2, entity.getOperation());
            ps.setDouble(3, entity.getValue());
            ps.setTimestamp(4, Timestamp.valueOf(entity.getTime()));

            ps.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException("Save failed", e);
        } finally {
            pool.releaseConnection(con);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAll() {
        List<QuantityMeasurementEntity> list = new ArrayList<>();

        Connection con = pool.getConnection();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM measurement");

            while (rs.next()) {
                list.add(new QuantityMeasurementEntity(
                        rs.getString("type"),
                        rs.getString("operation"),
                        rs.getDouble("measurement_value")
                ));
            }

        } catch (Exception e) {
            throw new DatabaseException("Fetch failed", e);
        } finally {
            pool.releaseConnection(con);
        }

        return list;
    }

    @Override
    public int getCount() {

        Connection con = pool.getConnection();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM measurement");

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            throw new DatabaseException("Count failed", e);
        } finally {
            pool.releaseConnection(con);
        }

        return 0;
    }

    @Override
    public void deleteAll() {

        Connection con = pool.getConnection();

        try {
            Statement st = con.createStatement();
            st.executeUpdate("DELETE FROM measurement");

        } catch (Exception e) {
            throw new DatabaseException("Delete failed", e);
        } finally {
            pool.releaseConnection(con);
        }
    }
}