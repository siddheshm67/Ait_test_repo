package com.hsa.main.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hsa.main.entity.PlanEntity;

public interface PlanRepo extends JpaRepository<PlanEntity, Integer> {

}
