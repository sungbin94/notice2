package com.kh.notice2.web;

import com.kh.notice2.domain.entity.notice.Notice;
import com.kh.notice2.domain.svc.notice.NoticeSVC;
import com.kh.notice2.web.form.notice.EditForm;
import com.kh.notice2.web.form.notice.ListForm;
import com.kh.notice2.web.form.notice.WriteForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

  private final NoticeSVC noticeSVC;

  //공지사항 메인화면
  @GetMapping
  public String list(ListForm listForm, Model model) {
    List<Notice> list = noticeSVC.findAll();

    BeanUtils.copyProperties(list, listForm);
    model.addAttribute("listForm", listForm);
    return "notice/noticeMainForm";
  }


  //글쓰기 화면
  @GetMapping("/write")
  public String writePage(Model model) {
    model.addAttribute("writeForm", new WriteForm());
    return "/notice/noticeWriteForm";
  }

  //글쓰기 처리
  @PostMapping("/write")
  public String write(@Valid @ModelAttribute WriteForm writeForm, BindingResult bindingResult) {
    log.info("writeForm : {}", writeForm);

    Notice notice = new Notice();
    BeanUtils.copyProperties(writeForm, notice);
    log.info("notice : {}", notice);
    Notice savedNotice = new Notice();

    return "redirect:/notice/{id}";
  }


  //글수정 화면
  @GetMapping("edit/{id}")
  public String edit(@PathVariable("id") Long noticeId,
                     Model model) {
    Optional<Notice> foundNotice = noticeSVC.read(noticeId);
    EditForm editForm = new EditForm();
    if (!foundNotice.isEmpty()) {
      BeanUtils.copyProperties(foundNotice.get(), noticeId);
    }
    model.addAttribute("EditForm", editForm);

    return "/notice/noticeModifyForm";
  }

  //글수정 처리
  @PostMapping("edit/{id}")
  public String update(@PathVariable("id") Long noticeId, @Valid EditForm editForm, BindingResult bindingResult) {
    log.info("EditForm : {}", editForm);

    Notice notice = new Notice();
    BeanUtils.copyProperties(editForm, notice);
    log.info("notice = {}", notice);

    return "redirect:/notice/{id}";
  }

  //게시글 조회
  @GetMapping("/notice/{id}")
  public String read(@PathVariable("id") Long noticeId, Model model) {
    Optional<Notice> foundNotice = noticeSVC.read(noticeId);
    ListForm listForm = new ListForm();
    if (!foundNotice.isEmpty()) {
      BeanUtils.copyProperties(foundNotice.get(), listForm);
    }
    model.addAttribute("listForm", listForm);

    return "/notice/noticeMainForm";
  }

  //게시글 삭제
  @GetMapping("/notice/{id}/del")
  public String del(@PathVariable("id") Long noticeId) {
    log.info("게시글번호 : {}", noticeId);
    noticeSVC.delete(noticeId);

    return "/notice";
  }
}
