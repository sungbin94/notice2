package com.kh.notice2.domain.dao.notice;

import com.kh.notice2.domain.entity.notice.Notice;

import java.util.List;
import java.util.Optional;

public interface NoticeDAO {
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
   * @param notice  게시글 작성
   * @return        게시글
   */
  int save(Notice notice);

  /**
   * 수정
   * @param noticeId 게시글 번호
   * @param notice   수정 내용
   * @return
   */
  int update(Long noticeId, Notice notice);

  /**
   * 삭제
   * @param noticeId
   * @return
   */
  int delete(Long noticeId);

  /**
   * 게시물 번호 생성
   * @return
   */
  Long generatedNoticeId();

  /**
   * 조회수 증가
   * @param noticeId 게시글 번호
   * @return 수정건수
   */
  int increaseViewCount(Long noticeId);




}
