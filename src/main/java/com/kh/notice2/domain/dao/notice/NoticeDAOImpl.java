package com.kh.notice2.domain.dao.notice;

import com.kh.notice2.domain.entity.notice.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class NoticeDAOImpl implements NoticeDAO {

  private final JdbcTemplate jt;


  /**
   * @return 공지사항 리스트
   */
  @Override
  public List<Notice> findAll() {
    StringBuffer sql = new StringBuffer();
    sql.append("select notice_id, title, content, write, Attachments, count, udate ");
    sql.append("  from notice ");
    sql.append("order by notice_id desc ");

    List<Notice> list = jt.query(sql.toString(), new BeanPropertyRowMapper<>(Notice.class));

    return list;
  }

  /**
   * 조회
   *
   * @param noticeId 게시글 번호
   * @return 게시글
   */
  @Override
  public Optional<Notice> read(Long noticeId) {
    StringBuffer sql = new StringBuffer();
    sql.append("select notice_id, title, content, write,Attachments, count, udate ");
    sql.append("  from notice ");
    sql.append("order by notice_id desc ");

    List<Notice> list = jt.query(
        sql.toString(), new BeanPropertyRowMapper<>(Notice.class));

    return Optional.empty();

  }

  /**
   * 작성
   *
   * @param notice 게시글 작성
   * @return 게시글
   */
  @Override
  public int save(Notice notice) {
    StringBuffer sql = new StringBuffer();
    sql.append("insert into notice(notice_id, title, content, write, Attachments, count, udate) ");
    sql.append("values(notice_notice_id_seq.nextval,?,?,관리자,1,1,sysdate) ");

    int result = jt.update(sql.toString(),
              notice.getNoticeId(),
    notice.getTitle(),
    notice.getContent(),
    notice.getWrite(),
    notice.getAttachment());
    return result;
  }

  /**
   * 수정
   *
   * @param noticeId 게시글 번호
   * @param notice   수정 내용
   * @return
   */
  @Override
  public int update(Long noticeId, Notice notice) {
    StringBuffer sql = new StringBuffer();
    sql.append("update notice ");
    sql.append("set title = ? , ");
    sql.append("    content = ? , ");
    sql.append("    attachment   = ?, udate = sysdate ");
    sql.append("    udate   = ? ");
    sql.append("where notice_id = ? ");

    int affectedRow = jt.update(sql.toString(), notice.getTitle(), notice.getContent(), notice.getAttachment(), noticeId);
    return affectedRow;
  }

  /**
   * 삭제
   *
   * @param noticeId
   * @return
   */
  @Override
  public int delete(Long noticeId) {

    String sql = "delete from notice where notice_id = ? ";
    int affectRow = jt.update(sql, noticeId);
    return affectRow;
  }

  /**
   * 게시물 번호 생성
   *
   * @return
   */
  @Override
  public Long generatedNoticeId() {
    String sql = "select notice_notice_id_seq.nextval form dual ";
    Long noticeId = jt.queryForObject(sql, Long.class);
    return noticeId;
  }

  /**
   * 조회수 증가
   *
   * @param noticeId 게시글 번호
   * @return 수정건수
   */
  @Override
  public int increaseViewCount(Long noticeId) {
    String sql = " select count(*) form notice ";
    Integer cnt = jt.queryForObject(sql, Integer.class);

    return cnt;
  }
}
