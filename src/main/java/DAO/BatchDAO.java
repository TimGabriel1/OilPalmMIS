package DAO;

import Model.Batch;
import Model.Vendor;

import java.sql.SQLException;
import java.util.List;

public interface BatchDAO {
    List<Batch> get() throws SQLException;

    boolean  saveBatch(Batch batch);

    Batch get(int id);

    boolean updateBatch(Batch batch);

    boolean delete(int id);




}
