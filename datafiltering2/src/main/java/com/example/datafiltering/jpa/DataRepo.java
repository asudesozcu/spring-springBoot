package com.example.datafiltering.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.datafiltering.data.Data;

@Repository
public interface DataRepo extends JpaRepository<Data, Long> {

	List<Data> findAll();

	List<Data> findById(long id);

	List<Data> findBysecTypeIgnoreCase(String secType);

	List<Data> findByCalenderYearMonth(Date calenderYearMonth);

	List<Data> findByCalenderYearMonthText(String calenderYearMonthText);

	List<Data> findByFundTypeIgnoreCase(String fundType);

	List<Data> findByDonemBasiIsinBakiye(long donemBasiIsinBakiye);

	List<Data> findByDonemSonuIsinBakiye(long donemSonuIsinBakiye);

	List<Data> findByCalenderYearMonthBetween(Date startDate, Date endDate);

	List<Data> findByCalenderYearMonthAfter(Date date);

	List<Data> findByCalenderYearMonthBefore(Date date);

	List<Data> findByDonemSonuIsinBakiyeGreaterThan(long lowerBound);

	List<Data> findByDonemSonuIsinBakiyeLessThan(long upperBound);

	List<Data> findByDonemSonuIsinBakiyeBetween(long lowerbound, long upperbound);

	List<Data> findByDonemBasiIsinBakiyeGreaterThan(long lowerBound);

	List<Data> findByDonemBasiIsinBakiyeLessThan(long upperBound);

	List<Data> findByDonemBasiIsinBakiyeBetween(long lowerbound, long upperbound);

}
