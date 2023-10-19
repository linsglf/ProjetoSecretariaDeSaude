package DAO;

import DTO.DTO;

public interface DAO {

    public void insert(DTO obj);
    public void update(DTO obj);
    public void delete(DTO obj);
}
