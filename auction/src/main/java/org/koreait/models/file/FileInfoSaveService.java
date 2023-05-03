package org.koreait.models.file;

import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.koreait.entities.FileInfo;
import org.koreait.repositories.FileInfoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileInfoSaveService {
    private final FileInfoRepository repository;
    private final EntityManager em;

    private final HttpServletRequest request;


    @Value("${fileupload.path}")
    private String fileUploadPath;

    public FileInfo save(MultipartFile file) {
        return save(file, null, null);
    }

    public FileInfo save(MultipartFile file, String gid, String location) {
        gid = gid == null ? UUID.randomUUID().toString() : gid;

        String fileName = file.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        FileInfo fileInfo = FileInfo.builder()
                .contentType(file.getContentType())
                .originalFilename(fileName)
                .extension(extension)
                .gid(gid)
                .location(location)
                .build();

        fileInfo = repository.saveAndFlush(fileInfo);
        em.detach(fileInfo);

        return fileInfo;
    }

    /**
     * 웹브라우저에서 파일을 조회할 수 있는 경로
     *
     * @param fileNo
     * @return
     */
    public String getFileURL(Long fileNo) {
        String URL = request.getContextPath() + "/uploads/" + getFileFolder(fileNo) + "/" + getFileName(fileNo);

        return URL;
    }

    /**
     * 파일이 저장된 물리적 위치
     *
     * - 파일 삭제시에 활용
     * @param fileNo
     * @return
     */
    public String getFilePath(Long fileNo) {
        String dirPath = fileUploadPath + "/" + getFileFolder(fileNo);
        File dir = new File(dirPath);
        if (!dir.exists()) { // 분산 폴더가 없는 경우는 생성
            dir.mkdir();
        }

        return dirPath += "/" + getFileName(fileNo);
    }

    private String getFileFolder(Long fileNo) {
        return "" + fileNo % 10;
    }

    private String getFileName(Long fileNo) {
        String fileName = "" + fileNo;
        FileInfo fileInfo = repository.findByFileNo(fileNo);
        if (fileInfo != null) {
            String extension = fileInfo.getExtension();
            if (extension != null && !extension.isBlank()) {
                fileName += "." + extension;
            }
        }

        return fileName;
    }

}
