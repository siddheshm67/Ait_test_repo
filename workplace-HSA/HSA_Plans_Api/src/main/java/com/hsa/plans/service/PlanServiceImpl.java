package com.hsa.plans.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsa.plans.Dto.PlanDTO;
import com.hsa.plans.Repo.PlanMasterRepo;
import com.hsa.plans.entity.PlanMaster;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanMasterRepo planMasterRepo;

	@Override
	public boolean savePlan(PlanDTO planDTO) {

		PlanMaster planMaster = new PlanMaster();

		BeanUtils.copyProperties(planDTO, planMaster);
		planMaster.setActiveSwitch("Y");

		PlanMaster savedEntityMaster = planMasterRepo.save(planMaster);

		return savedEntityMaster.getPlanId() != null;
	}

	@Override
	public List<PlanDTO> getPlans() {

		List<PlanMaster> allPlansList = planMasterRepo.findAll();

		List<PlanDTO> planDTOs = new ArrayList<>();

		allPlansList.forEach(e -> {
			PlanDTO planDTO = new PlanDTO();
			BeanUtils.copyProperties(e, planDTO);
			planDTOs.add(planDTO);
		});

		return planDTOs;
	}

	@Override
	public PlanDTO getPlan(Integer planID) {
		PlanMaster planMaster = planMasterRepo.findById(planID).orElseThrow();
		PlanDTO planDTO = new PlanDTO();
		BeanUtils.copyProperties(planMaster, planDTO);
		return planDTO;
	}

	@Override
	public boolean updatePlan(Integer planID, String status) {
		PlanMaster planMaster = planMasterRepo.findById(planID).orElseThrow();
		planMaster.setActiveSwitch(status);
		planMasterRepo.save(planMaster);
		return true;
	}

}
