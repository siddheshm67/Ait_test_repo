package com.management.app.service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.app.Bindings.LoginForm;
import com.management.app.Bindings.RegisterForm;
import com.management.app.Bindings.ResetPwdForm;
import com.management.app.Entity.City;
import com.management.app.Entity.Country;
import com.management.app.Entity.State;
import com.management.app.Entity.User;
import com.management.app.Repo.CityRepo;
import com.management.app.Repo.CountryRepo;
import com.management.app.Repo.StateRepo;
import com.management.app.Repo.UserRepo;
import com.management.app.utils.EmailUtil;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	CountryRepo countryRepo;
	
	@Autowired
	StateRepo stateRepo;
	
	@Autowired
	CityRepo cityRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	EmailUtil emailUtil;

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public Map<Integer, String> getCountries() {
		HashMap<Integer, String> countryMap = new HashMap<>();
		List<Country> cuntList = countryRepo.findAll();
	    cuntList.forEach(c -> countryMap.put(c.getCountryID(), c.getCountryName()));
		logger.info("added new line in getCountries");
	    return countryMap;
	}
	
	@Override
	public Map<Integer, String> getStates() {
		HashMap<Integer, String> stateMap = new HashMap<>();
		List<State> stateList = stateRepo.findAll();
		logger.info("added new line in getStates");
	    stateList.forEach(c -> stateMap.put(c.getStateID(), c.getStateName()));
		return stateMap;
	}



	@Override
	public Map<Integer, String> getCities() {
		HashMap<Integer, String> cityateMapityMap = new HashMap<>();
		List<City> cityList = cityRepo.findAll();
	    cityList.forEach(c -> cityateMapityMap.put(c.getCityID(), c.getCityName()));
		return cityateMapityMap;
	}
	
	

	/*
	 * @Override public Map<Integer, String> getStates(Integer countryID) {
	 * HashMap<Integer, String> stateMap = new HashMap<>(); List<State> stateList =
	 * stateRepo.findByCountryID(countryID); stateList.forEach(s ->
	 * stateMap.put(s.getStateID(), s.getStateName())); return stateMap; }
	 * 
	 * @Override public Map<Integer, String> getCity(Integer stateID) {
	 * HashMap<Integer, String> cityMap = new HashMap<>(); List<City> stateList =
	 * cityRepo.findByStateID(stateID); stateList.forEach(c ->
	 * cityMap.put(c.getCityID(), c.getCityName())); return cityMap; }
	 */

	@Override
	public User getUser(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public boolean saveUser(RegisterForm registerForm) {
		
		registerForm.setPassword(generateRandonPwd());
		registerForm.setPwdUpdated("NO");
		
		User user = new User();
		BeanUtils.copyProperties(registerForm, user);
		userRepo.save(user);
		
		String subject = "Your account created";
		String body = "your password : "+ registerForm.getPassword();
		return emailUtil.sendMail(subject,body,registerForm.getEmail());
	}

	private String generateRandonPwd() {
		char[] possibleCharacters = (new String("abcdefghijklmnopqrstuvwxyz0123456789@")).toCharArray();
		return RandomStringUtils.random( 6, 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom() );
		
	}

	@Override
	public User loginUSer(LoginForm loginForm) {
		User user = userRepo.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
		return user;
	}

	@Override
	public boolean resetPwd(ResetPwdForm resetPwdForm) {
		Optional<User> optional = userRepo.findById(resetPwdForm.getUserID());
		
		if (optional.isPresent()) {
			User user = optional.get();
			user.setPassword(resetPwdForm.getPwd());
			user.setPwdUpdated("YES");
			userRepo.save(user);
			return true;
		}
		return false;
	}

}
