package org.koreait.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class FileInfo extends BaseUserEntity {
    @Id @GeneratedValue
    private Long fileNo; // 파일 번호.확장자

    @Column(length=40, nullable = false)
    private String gid; // 그룹 ID

    @Column(length=60)
    private String location; // 파일 사용 위치

    @Column(length=100, nullable = false)
    private String originalFilename; //원래 파일명

    @Column(length=20)
    private String extension; // 파일 확장자

    @Column(length=65, nullable = false)
    private String contentType; // 파일 종류 MIME

    private boolean success; // 그룹작업 완료 여부

    @Transient
    private String fileURL; // 파일 접속 URL

    @Transient
    private String filePath; // 파일 업로드 경로

}
