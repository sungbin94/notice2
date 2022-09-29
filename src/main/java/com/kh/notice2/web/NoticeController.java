package com.kh.notice2.web;

import com.kh.notice2.domain.entity.notice.Notice;
import com.kh.notice2.domain.svc.notice.NoticeSVC;
import com.kh.notice2.web.form.notice.DetailForm;
import com.kh.notice2.web.form.notice.ListForm;
import com.kh.notice2.web.form.notice.WriteForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
  @ResponseBody
  @PostMapping("/write")
  public String write(@ModelAttribute WriteForm writeForm, RedirectAttributes redirectAttributes) {
    log.info("writeForm : {}", writeForm);

    Notice notice = new Notice();
    BeanUtils.copyProperties(writeForm, notice);
    log.info("notice : {}", notice);

    Notice save = noticeSVC.save(notice);

    List<Notice> list = noticeSVC.findAll();

    return "redirect:/notice/{id}";
  }

  //글작성 보기화면
  @GetMapping("/{id}")
  public String article(@PathVariable("id") Long noticeId, DetailForm detailForm, Model model) {
    Notice readNotice = noticeSVC.f
  }


  //글수정 화면

  //글수정 처리

  //게시글 조회

  //게시글 삭제

}
