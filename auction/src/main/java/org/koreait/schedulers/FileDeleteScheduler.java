package org.koreait.schedulers;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.koreait.entities.FileInfo;
import org.koreait.models.file.FileInfoSaveService;
import org.koreait.repositories.FileInfoRepository;
import org.koreait.repositories.ProductRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@Log
@RequiredArgsConstructor
public class FileDeleteScheduler {
	private final FileInfoRepository repository;
	private final FileInfoSaveService saveService;

	//@Scheduled(cron="0 0 0 * * *")
	//@Scheduled(fixedRate = 1000)
	public void autoDelete(){
		List<FileInfo> fileInfos = repository.findBySuccess(false);
		if(!fileInfos.isEmpty()){
			for(FileInfo fileInfo : fileInfos){
				if(repository.exists(fileInfo.getFileNo())){
					String filePath = saveService.getFilePath(fileInfo.getFileNo());
					System.out.println(filePath);
					File file = new File(filePath);
					if(file.exists()){
						file.delete();
						repository.deleteById(fileInfo.getFileNo());
					}
				}
			}
		}
	}
}
