package org.koreait.models.file;

import lombok.RequiredArgsConstructor;
import org.koreait.entities.FileInfo;
import org.koreait.repositories.FileInfoRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileInfoService {
	private final FileInfoRepository repository;
	private final FileInfoSaveService saveService;

	public FileInfo get(Long fileNo){
		FileInfo fileInfo = repository.findById(fileNo).orElseThrow(FileNotFoundException::new);
		fileInfo.setFileURL(saveService.getFileURL(fileInfo.getFileNo()));
		fileInfo.setFilePath(saveService.getFilePath(fileInfo.getFileNo()));

		return fileInfo;
	}
}
