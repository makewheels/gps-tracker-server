package com.example.gpstrackerserver.bean;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadDataDao extends CrudRepository<UploadData, Integer> {
}
