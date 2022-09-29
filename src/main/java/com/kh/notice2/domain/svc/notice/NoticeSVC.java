package com.kh.notice2.domain.svc.notice;

import com.kh.notice2.domain.entity.notice.Notice;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface NoticeSVC {

  /**
   *
   * @return 공지사항 리스트
   */
  List<Notice> findAll();


  /**
   * 조회
   * @param noticeId  게시글 번호
   * @return          게시글
   */
  Optional<Notice> read(Long noticeId);

  /**
   * 작성
   *
   * @param notice 게시글 작성
   * @return 게시글
   */
  Notice save(Notice notice);

  Notice save(Notice notice, List<MultipartFile> files);

  /**
   * 수정
   *
   * @param noticeId 게시글 번호
   * @param notice   수정 내용
   * @return
   */
  Notice update(Long noticeId, Notice notice);

  Notice update(Long noticeId, Notice notice, List<MultipartFile> files);

  /**
   * 삭제
   * @param noticeId
   * @return
   */
  void delete(Long noticeId);


}
