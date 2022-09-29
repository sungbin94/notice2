package com.kh.notice2.domain.entity.notice;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Table
public class Notice {
  private Long noticeId;           // --공지사항게시글 번호
  private String title;            //--게시글 제목
  private String content;          //--게시글 내용
  private String write;           //--작성자(관리자)
  private String attachment;      //--첨부파일
  private Long count;             //조회수
  private LocalDateTime udate;   //게시글 작성일(수정일)
}
