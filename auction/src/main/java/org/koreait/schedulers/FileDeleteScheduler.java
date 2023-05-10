package org.koreait.schedulers;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.koreait.entities.FileInfo;
import org.koreait.repositories.FileInfoRepository;
import org.koreait.repositories.ProductRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
@RequiredArgsConstructor
public class FileDeleteScheduler {
	private final FileInfoRepository repository;

	//@Scheduled(cron="0 0 0 * * *")
	public void autoDelete(){
		repository.deleteBySuccess(false);
	}
}
