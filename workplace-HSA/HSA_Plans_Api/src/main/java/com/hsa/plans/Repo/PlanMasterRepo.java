package com.hsa.plans.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hsa.plans.entity.PlanMaster;

public interface PlanMasterRepo extends JpaRepository<PlanMaster, Integer> {

}
