package com.example.datafiltering.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.datafiltering.jpa.AccessManager;

import jakarta.servlet.http.HttpSession;

@Controller
public class DataController {

	@Autowired
	private AccessManager accessManager;
	Logger logger = LoggerFactory.getLogger(DataController.class);

	public DataController(AccessManager accessManager) {
		this.accessManager = accessManager;
	}

	@RequestMapping(value = "/entries/all", method = RequestMethod.POST)
	public String showallData(@RequestParam(value = "page", defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "size", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "sort", defaultValue = "id") String sortProperty, HttpSession session,
			ModelMap model) {
		List<Data> dataList = accessManager.getEntries();

		model.addAttribute("dataList", dataList);
		if (dataList != null && !dataList.isEmpty()) {
			Set<String> secTypeSet = new HashSet<>();

			for (Data data : dataList) {
				secTypeSet.add(data.getsecType());
			}

			model.addAttribute("secTypeSet", secTypeSet);
		}

		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortProperty));
		int start = Math.min((int) pageable.getOffset(), dataList.size());
		int end = Math.min(start + pageable.getPageSize(), dataList.size());

		List<Data> pageContent = dataList.subList(start, end);
		Page<Data> dataPage = new PageImpl<>(pageContent, pageable, dataList.size());

		model.addAttribute("dataPage", dataPage);
		return "datafiltering";
	}

	@RequestMapping(value = "/entries/all", method = RequestMethod.GET)
	public String showData(@RequestParam(value = "page", defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "size", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "sort", defaultValue = "id") String sortProperty, HttpSession session,
			ModelMap model) {

		List<Data> dataList = accessManager.getEntries();

		model.addAttribute("dataList", dataList);

		if (dataList != null && !dataList.isEmpty()) {
			Set<String> secTypeSet = new HashSet<>();
			Set<String> fundTypeSet = new HashSet<>();

			for (Data data : dataList) {
				secTypeSet.add(data.getsecType());
				fundTypeSet.add(data.getFundType());
			}

			model.addAttribute("secTypeSet", secTypeSet);
			model.addAttribute("fundTypeSet", fundTypeSet);

		}

		List<Data> filteredData = (List<Data>) session.getAttribute("filteredData");

		if (filteredData == null) {
			filteredData = dataList;
		}

		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortProperty));
		int start = Math.min((int) pageable.getOffset(), filteredData.size());
		int end = Math.min(start + pageable.getPageSize(), filteredData.size());

		List<Data> pageContent = filteredData.subList(start, end);
		Page<Data> dataPage = new PageImpl<>(pageContent, pageable, filteredData.size());

		model.addAttribute("dataPage", dataPage);
		model.addAttribute("currentPage", pageNumber);

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
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date calendarMin,
			@RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date calendarMax,
			HttpSession session) {

		List<Data> allData = accessManager.getEntries();
		Set<String> secTypeSet = new HashSet<>();
		for (Data data : allData) {
			secTypeSet.add(data.getsecType());
		}

		// Apply filters
		List<Data> filteredData = new ArrayList<>(allData);
		if (secType != null && !secType.isEmpty()) {
			filteredData = filterBySecType(filteredData, secType);
		}
		if (fundType != null && !fundType.isEmpty()) {
			filteredData = filterByFundType(filteredData, fundType);
		}
		if (calendartext != null && !calendartext.isEmpty()) {
			filteredData = filterByCalendarText(filteredData, calendartext);
		}
		if (donembasıbakıyeMin != null || donembasıbakıyeMax != null) {
			filteredData = filterByDonembasıBakiye(filteredData, donembasıbakıyeMin, donembasıbakıyeMax);
		}
		if (donemsonubakıyeMin != null || donemsonubakıyeMax != null) {
			filteredData = filterByDonemsonuBakiye(filteredData, donemsonubakıyeMin, donemsonubakıyeMax);
		}
		if (calendarMin != null || calendarMax != null) {
			filteredData = filterByCalendar(filteredData, calendarMin, calendarMax);
		}

		session.setAttribute("filteredData", filteredData);
		session.setAttribute("secTypeSet", secTypeSet);

		return "redirect:/entries/all";
	}

}