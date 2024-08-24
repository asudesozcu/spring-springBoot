package com.example.datafiltering.jpa;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.datafiltering.data.Data;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class DataLoader implements CommandLineRunner {

	@Autowired
	private DataRepo repository;

	@Override
	public void run(String... args) throws Exception {

		Data data1 = new Data("F54", "GAYRIMENKUL YATIRIM FONU", Date.valueOf(LocalDate.of(2023, 06, 01)),
				"2023 HAZIRAN", 7454709218L, 7781542579L);

		Data data2 = new Data("F54", "GAYRIMENKUL YATIRIM FONU", Date.valueOf(LocalDate.of(2023, 07, 01)),
				"2024 TEMMUZ", 7728362589L, 7806990656L);

		Data data3 = new Data("F54", "GAYRIMENKUL YATIRIM FONU", Date.valueOf(LocalDate.of(2025, 8, 01)),
				"2025 AGUSTOS", 77283756489L, 36224234L);

		Data data4 = new Data("F54", "GAYRIMENKUL YATIRIM FONU", Date.valueOf(LocalDate.of(2024, 9, 01)), "2024 EYLUL",
				4L, 32224234L);

		Data data5 = new Data("F53", "GAYRIMENKUL YATIRIM FONU", Date.valueOf(LocalDate.of(2024, 9, 01)), "2024 EYLUL",
				4L, 32224234L);

		Data data6 = new Data("F52", "GAYRIMENKUL YATIRIM FONU", Date.valueOf(LocalDate.of(2024, 9, 01)), "2024 EYLUL",
				4L, 32224234L);

		repository.save(data1);

		repository.save(data2);
		repository.save(data3);
		repository.save(data4);
		repository.save(data5);
		repository.save(data6);

	}
}
