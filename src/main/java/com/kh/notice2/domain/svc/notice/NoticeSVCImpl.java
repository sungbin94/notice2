package com.kh.notice2.domain.svc.notice;

import com.kh.notice2.domain.dao.notice.NoticeDAO;
import com.kh.notice2.domain.entity.notice.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeSVCImpl implements NoticeSVC {

  private final NoticeDAO noticeDAO;

  /**
   * @return 공지사항 리스트
   */
  @Override
  public List<Notice> findAll() {
    return noticeDAO.findAll();
  }

  /**
   * 조회
   *
   * @param noticeId 게시글 번호
   * @return 게시글
   */
  @Override
  public Optional<Notice> read(Long noticeId) {
    noticeDAO.increaseViewCount(noticeId);
    return noticeDAO.read(noticeId);
  }

  /**
   * 작성
   *
   * @param notice 게시글 작성
   * @return 게시글
   */
  @Override
  public Notice save(Notice notice) {
    Long generatedNoticeId = noticeDAO.generatedNoticeId();
    notice.setNoticeId(generatedNoticeId);
    noticeDAO.save(notice);

    return noticeDAO.read(generatedNoticeId).get();
  }

  @Override
  public Notice save(Notice notice, List<MultipartFile> files) {
    Long generatedNoticeId = noticeDAO.generatedNoticeId();
    notice.setNoticeId(generatedNoticeId);
    noticeDAO.save(notice);

    return noticeDAO.read(generatedNoticeId).get();
  }

  /**
   * 수정
   *
   * @param noticeId 게시글 번호
   * @param notice   수정 내용
   * @return
   */
  @Override
  public Notice update(Long noticeId, Notice notice) {
    noticeDAO.update(noticeId, notice);
    return noticeDAO.read(noticeId).get();
  }

  @Override
  public Notice update(Long noticeId, Notice notice, List<MultipartFile> files) {
    noticeDAO.update(noticeId, notice);

    return noticeDAO.read(noticeId).get();
  }

  /**
   * 삭제
   *
   * @param noticeId
   * @return
   */
  @Override
  public void delete(Long noticeId) {
    noticeDAO.delete(noticeId);

  }
}
