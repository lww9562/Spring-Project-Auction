package org.koreait.models.file;

import lombok.RequiredArgsConstructor;
import org.koreait.commons.UserUtil;
import org.koreait.entities.FileInfo;
import org.koreait.repositories.FileInfoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class FileDeleteService {
	private final UserUtil userUtil;
	private final FileInfoService infoService;
	private final FileInfoRepository repository;


	//파일 삭제
	public void delete(Long fileNo){
		//1. 파일 정보 조회
		FileInfo fileInfo = infoService.get(fileNo);

		//2. 파일 소유자 체크(관리자는 무조건 가능, 회원인 경우 본인이 올린 파일만)
		String createdBy = fileInfo.getCreatedBy();
		if(createdBy != null && !userUtil.isAdmin() && !userUtil.isMine(createdBy)){
			throw new FileException("errors.file.notYours", HttpStatus.UNAUTHORIZED);
		}

		File filePath = new File(fileInfo.getFilePath());

		repository.delete(fileInfo);
		repository.flush();

		if(filePath.exists()){
			filePath.delete();
		}

	}
}
