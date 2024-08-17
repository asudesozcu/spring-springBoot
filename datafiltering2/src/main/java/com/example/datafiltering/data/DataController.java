package com.example.datafiltering.data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.datafiltering.jpa.AccessManager;

@Controller
public class DataController {

	Logger log = LoggerFactory.getLogger(DataController.class);
	@Autowired
	private AccessManager accessManager;

	public DataController(AccessManager accessManager) {
		this.accessManager = accessManager;
	}

	@RequestMapping(value = "/entries/all", method = RequestMethod.GET)
	public String showData(ModelMap model) {
		model.addAttribute("dataList", accessManager.getEntries());
		return "datafiltering";
	}

	@RequestMapping(value = "/entries/all", method = RequestMethod.POST)
	public String showallData(ModelMap model) {
		model.addAttribute("dataList", accessManager.getEntries());
		return "datafiltering";
	}

	private List<Data> filterBySecType(List<Data> dataList, String secType) {
		return dataList.stream().filter(data -> secType.equals(data.getsecType())).collect(Collectors.toList());
	}

	private List<Data> filterByFundType(List<Data> dataList, String fundType) {
		return dataList.stream().filter(data -> fundType.equals(data.getFundType())).collect(Collectors.toList());
	}

	private List<Data> filterByCalendarText(List<Data> dataList, String calendartext) {
		return dataList.stream().filter(data -> calendartext.equals(data.getCalenderYearMonthText()))
				.collect(Collectors.toList());
	}

	private List<Data> filterByDonembasıBakiye(List<Data> dataList, Long givenbakiyeMin, Long givenbakiyeMax) {
		if (givenbakiyeMax == null && givenbakiyeMin != null) {
			return dataList.stream().filter(data -> data.getDonemBasiIsinBakiye() >= givenbakiyeMin)
					.collect(Collectors.toList());
		} else if (givenbakiyeMin == null && givenbakiyeMax != null) {
			return dataList.stream().filter(data -> data.getDonemBasiIsinBakiye() <= givenbakiyeMax)
					.collect(Collectors.toList());
		} else {
			return dataList.stream()
					.filter(data -> (givenbakiyeMin == null || data.getDonemBasiIsinBakiye() >= givenbakiyeMin)
							&& (givenbakiyeMax == null || data.getDonemBasiIsinBakiye() <= givenbakiyeMax))
					.collect(Collectors.toList());
		}
	}

	private List<Data> filterByDonemsonuBakiye(List<Data> dataList, Long givenbakiyeMin, Long givenbakiyeMax) {
		if (givenbakiyeMax == null && givenbakiyeMin != null) {
			return dataList.stream().filter(data -> data.getDonemSonuIsinBakiye() >= givenbakiyeMin)
					.collect(Collectors.toList());
		} else if (givenbakiyeMin == null && givenbakiyeMax != null) {
			return dataList.stream().filter(data -> data.getDonemSonuIsinBakiye() <= givenbakiyeMax)
					.collect(Collectors.toList());
		} else {
			return dataList.stream()
					.filter(data -> (givenbakiyeMin == null || data.getDonemBasiIsinBakiye() >= givenbakiyeMin)
							&& (givenbakiyeMax == null || data.getDonemSonuIsinBakiye() <= givenbakiyeMax))
					.collect(Collectors.toList());
		}
	}

	private List<Data> filterByCalendar(List<Data> dataList, Date givenDatemin, Date givenDatemax) {
		if (givenDatemax == null && givenDatemin != null) {
			return dataList.stream().filter(data -> data.getCalenderYearMonth().compareTo(givenDatemin) >= 0)
					.collect(Collectors.toList());
		} else if (givenDatemin == null && givenDatemax != null) {
			return dataList.stream().filter(data -> data.getCalenderYearMonth().compareTo(givenDatemax) <= 0)
					.collect(Collectors.toList());
		} else {
			return dataList.stream().filter(data -> data.getCalenderYearMonth().compareTo(givenDatemin) >= 0
					&& data.getCalenderYearMonth().compareTo(givenDatemax) <= 0).collect(Collectors.toList());
		}
	}

	@RequestMapping(value = "/filterData", method = RequestMethod.POST)
	public String filterData(@RequestParam(value = "secType", required = false) String secType,
			@RequestParam(value = "fundType", required = false) String fundType,
			@RequestParam(value = "calendartext", required = false) String calendartext,
			@RequestParam(value = "donembasıbakıyeMin", required = false) Long donembasıbakıyeMin,
			@RequestParam(value = "donembasıbakıyeMax", required = false) Long donembasıbakıyeMax,
			@RequestParam(value = "donemsonubakıyeMin", required = false) Long donemsonubakıyeMin,
			@RequestParam(value = "donemsonubakıyeMax", required = false) Long donemsonubakıyeMax,
			@RequestParam(value = "calendarMin", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date calendarMin,
			@RequestParam(value = "calendarMax", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date calendarMax,
			ModelMap model) {

		List<Data> dataList = accessManager.getEntries();

		if (secType != null && !secType.isEmpty()) {
			dataList = filterBySecType(dataList, secType);
		}

		if (fundType != null && !fundType.isEmpty()) {
			dataList = filterByFundType(dataList, fundType);
		}

		if (calendartext != null && !calendartext.isEmpty()) {
			dataList = filterByCalendarText(dataList, calendartext);
		}

		if (donembasıbakıyeMin != null || donembasıbakıyeMax != null) {
			dataList = filterByDonembasıBakiye(dataList, donembasıbakıyeMin, donembasıbakıyeMax);
		}

		if (donemsonubakıyeMin != null || donemsonubakıyeMax != null) {
			dataList = filterByDonemsonuBakiye(dataList, donemsonubakıyeMin, donemsonubakıyeMax);
		}

		if (calendarMin != null || calendarMax != null) {
			dataList = filterByCalendar(dataList, calendarMin, calendarMax);
		}

		model.addAttribute("dataList", dataList);

		return "datafiltering";
	}

}
