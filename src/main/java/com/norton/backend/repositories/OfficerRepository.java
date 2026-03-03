package com.norton.backend.repositories;

import com.norton.backend.models.OfficerModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficerRepository extends JpaRepository<OfficerModel, Long> {

  List<OfficerModel> findByPositionId(Integer positionId);
}
