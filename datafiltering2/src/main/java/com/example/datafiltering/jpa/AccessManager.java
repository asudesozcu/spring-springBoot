package com.example.datafiltering.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.datafiltering.data.Data;

@RestController

public class AccessManager {

	@Autowired
	private DataRepo repository;

	public List<Data> getEntries() {
		return repository.findAll();
	}

	public List<Data> getEntryById(@PathVariable long id) {
		return repository.findById(id);
	}

	public List<Data> getEntryBysecType(@PathVariable String secType) {
		return repository.findBysecTypeIgnoreCase(secType);
	}

	public List<Data> getEntryByFundType(@PathVariable String fundType) {
		return repository.findByFundTypeIgnoreCase(fundType);
	}

	public List<Data> getEntryByCalenderYearMonthText(@PathVariable String dateText) {
		return repository.findByCalenderYearMonthText(dateText);
	}

	public List<Data> getEntryByDonemSonuIsınBakıye(@PathVariable long sonBakıye) {
		return repository.findByDonemSonuIsinBakiye(sonBakıye);
	}

	public List<Data> getEntryBydonemSonuIsınBakıyeGreaterThan(@PathVariable long lowerbound) {
		return repository.findByDonemSonuIsinBakiyeGreaterThan(lowerbound);
	}

	public List<Data> getEntryBydonemSonuIsınBakıyeLessThan(@PathVariable long upperbound) {
		return repository.findByDonemSonuIsinBakiyeLessThan(upperbound);
	}

	public List<Data> getEntryBydonemSonuIsınBakıyeBetween(@PathVariable long lowerbound,
			@PathVariable long upperbound) {
		return repository.findByDonemSonuIsinBakiyeBetween(lowerbound, upperbound);
	}

	public List<Data> getEntryBydonemBasıIsınBakıyeGreaterThan(@PathVariable long lowerbound) {
		return repository.findByDonemBasiIsinBakiyeGreaterThan(lowerbound);
	}

	public List<Data> getEntryBydonemBasıIsınBakıyeLessThan(@PathVariable long upperbound) {
		return repository.findByDonemBasiIsinBakiyeLessThan(upperbound);
	}

	public List<Data> getEntryBydonemBasıIsınBakıyeBetween(@PathVariable long lowerbound,
			@PathVariable long upperbound) {
		return repository.findByDonemBasiIsinBakiyeBetween(lowerbound, upperbound);
	}

	public List<Data> getEntryByBetweencalenderYearMonth(@PathVariable Date start_date, @PathVariable Date end_date) {
		return repository.findByCalenderYearMonthBetween(start_date, end_date);
	}

	public List<Data> getEntryByAftercalendarYearMonth(@PathVariable Date date) {
		return repository.findByCalenderYearMonthAfter(date);
	}

	public List<Data> getEntryByBeforecalendarYearMonth(@PathVariable Date date) {
		return repository.findByCalenderYearMonthBefore(date);
	}

	public List<Data> getEntryByCalenderYearMonth(@PathVariable Date date) {
		return repository.findByCalenderYearMonth(date);
	}

	public Page<Data> getPagedData(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return repository.findAll(pageable);
	}

}
