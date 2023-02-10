package com.beneti.limitsvc;

import com.beneti.limitsvc.entities.DiaryLimit;
import com.beneti.limitsvc.repositories.DiaryLimitRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = DiaryLimitRepository.class)
@EntityScan(basePackageClasses = DiaryLimit.class)
public class LimitSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimitSvcApplication.class, args);
	}

}
