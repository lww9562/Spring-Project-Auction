package org.koreait.repositories;

import org.koreait.entities.FileInfo;
import org.koreait.entities.QFileInfo;
import org.koreait.entities.QProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long>, QuerydslPredicateExecutor<FileInfo> {
    default boolean exists(Long fileNo){
        QFileInfo fileInfo = QFileInfo.fileInfo;
        return exists(fileInfo.fileNo.eq(fileNo));

    }

    FileInfo findByFileNo(Long fileNo);

    List<FileInfo> findByGidOrderByRegDtAsc(String gid);
    List<FileInfo> findBySuccess(Boolean success);
}
