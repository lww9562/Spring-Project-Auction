package org.koreait.models.file;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.FileInfo;
import org.koreait.models.file.FileInfoService;
import org.koreait.models.file.FileNotFoundException;
import org.koreait.repositories.FileInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileUpdateSuccessService {
	private final FileInfoRepository repository;
	private final FileInfoService infoService;

	public void process(String gid){
		List<FileInfo> files = repository.findByGidOrderByRegDtAsc(gid);

		if(files == null || files.size() == 0){
			throw new FileNotFoundException();
		}

		files.stream().forEach(f -> f.setSuccess(true));


		repository.flush();
	}
}
