package org.koreait.schedulers;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.koreait.entities.Products;
import org.koreait.repositories.ProductRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Log
@RequiredArgsConstructor
public class ExpireDateCheckScheduler {
	private final ProductRepository repository;

	@Async
	//@Scheduled(fixedRate = 1000)
	public void check(){
		List<Products> list = repository.findAll();

		for(Products product : list){
			LocalDateTime expire = product.getRegDt().plusDays(3L);
			LocalDate expire_date = expire.toLocalDate();
			LocalTime expire_time = expire.toLocalTime();

			//서버가 365일 24시간 내내 돌아간다는 가정하에,
			if(LocalDate.now().isEqual(expire_date)){
				if(LocalTime.now().isAfter(expire_time)){
					//만약 현재 시간이 expire_time 이후의 시간이라면(expire_time 초과라면)
					product.setStats(false);

				}
			}
		}

	}
}
