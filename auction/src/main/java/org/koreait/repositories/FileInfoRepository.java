package org.koreait.repositories;

import org.koreait.entities.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long>, QuerydslPredicateExecutor<FileInfo> {
    FileInfo findByFileNo(Long fileNo);

    List<FileInfo> findByGidOrderByRegDtAsc(String gid);
}
