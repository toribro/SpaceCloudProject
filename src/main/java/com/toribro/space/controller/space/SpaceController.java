package com.toribro.space.controller.space;

import com.toribro.space.common.PageInfo;
import com.toribro.space.common.Pagination;
import com.toribro.space.domain.dto.space.SpaceDto;
import com.toribro.space.domain.entity.common.Attachment;
import com.toribro.space.domain.entity.member.Member;
import com.toribro.space.domain.entity.vo.space.SpaceDetail;
import com.toribro.space.service.space.SpaceService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
@RequestMapping("/space")
@RequiredArgsConstructor
@Slf4j
public class SpaceController {

    private final SpaceService spaceService;

    @Value("${file.dir}")
    private String uploadDir;

    //CRUD

    //공간등록
    @PostMapping
    public String hostEnroll(@ModelAttribute SpaceDto.EnrollDto space,
                             List<MultipartFile> files, HttpSession session){

        Member member = (Member) session.getAttribute("loginUser");
        space.setUserNo(member.getUserNo());

        log.info("files: {}",files);
        log.info("{}",space);
        log.info("{}",uploadDir);
        List<SpaceDto.SpaceAttachmentDto> fileInfo= new CopyOnWriteArrayList<>();

        int level=1;
        if(files!=null) {

            for(MultipartFile f :files) {
                log.info("changeName:{}",f.getOriginalFilename());
                if(!Objects.equals(f.getOriginalFilename(), "")) {
                    SpaceDto.SpaceAttachmentDto file=new SpaceDto.SpaceAttachmentDto();
                    String changeName=saveFile(f,session,uploadDir);
                    file.setFileLevel(level++);
                    file.setOriginName(f.getOriginalFilename());
                    file.setChangeName(changeName);
                    file.setFilePath(uploadDir);
                    fileInfo.add(file);
                }
            }
        }
        log.info("{}",fileInfo);

        int result = spaceService.enroll(space, fileInfo);
        if(result>0){
            session.setAttribute("alertMsg","등록되었습니다.");
            return "redirect:/host";
        }else{
            session.setAttribute("alertMsg","등록실패.");
            return"redirect:/host/enroll";
        }

    }

    private String saveFile(MultipartFile upfile, HttpSession session, String path) {
        //파일명 수정 후 서버에 업로드하기("imgFile.jpg => 202404231004305488.jpg")
        String originName = upfile.getOriginalFilename();

        //년월일시분초
        String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        //5자리 랜덤값
        int ranNum = (int)(Math.random() * 90000) + 10000;

        //확장자
        String ext="-";
        if(originName.contains(".")){
            ext = originName.substring(originName.lastIndexOf("."));
        }

        //수정된 첨부파일명
        String changeName = currentTime + ranNum + ext;

//        //첨부파일을 저장할 폴더의 물리적 경로(session)
//        String savePath = session.getServletContext().getRealPath(path);
//        log.info("savePath: {}",savePath);
        try {
            upfile.transferTo(new File(path + changeName));
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return changeName;

    }

    //모든공간조회
    @GetMapping
    public String spaceList(@RequestParam(defaultValue = "1") int cpage,Model model){

        int count=spaceService.getCount();
        PageInfo spacePage= Pagination.getPageInfo(count,cpage,10,9);
        log.info("{}",count);
        log.info("current:{}",spacePage.getCurrentPage());
        log.info("start:{}",spacePage.getStartPage());
        log.info("end: {}",spacePage.getEndPage());
        log.info("max: {}",spacePage.getMaxPage());

        model.addAttribute("pi",spacePage);
        model.addAttribute("spList",spaceService.findSpaces(spacePage));
        return "main";
    }

    //공간 상세
    @GetMapping("/{spaceNo}")
    public String spaceDetail(@PathVariable int spaceNo,Model model) {

        SpaceDetail spaceByOne = spaceService.findSpaceByNo(spaceNo);
        log.info("space:{}",spaceByOne);
        model.addAttribute("space", spaceByOne);

        return "space/spaceDetail";
    }

    //공간 수정
    @PatchMapping("/{spaceNo}")
    public String spaceUpdate(@PathVariable int spaceNo ,@ModelAttribute SpaceDto.UpdateDto space,List<MultipartFile> files,HttpSession session){

        Member member = (Member) session.getAttribute("loginUser");
        space.setUserNo(member.getUserNo());

        log.info("files: {}",files);
        log.info("{}",space);
        log.info("{}",uploadDir);
        List<SpaceDto.SpaceAttachmentDto> fileInfo= new CopyOnWriteArrayList<>();

        int level=1;
        if(files!=null) {

            for(MultipartFile f :files) {
                log.info("changeName:{}",f.getOriginalFilename());
                if(!Objects.equals(f.getOriginalFilename(), "")) {
                    SpaceDto.SpaceAttachmentDto file=new SpaceDto.SpaceAttachmentDto();
                    String changeName=saveFile(f,session,uploadDir);
                    file.setFileLevel(level++);
                    file.setOriginName(f.getOriginalFilename());
                    file.setChangeName(changeName);
                    file.setFilePath(uploadDir);
                    fileInfo.add(file);
                }
            }
        }
        log.info("{}",fileInfo);

        int result = spaceService.updateSpace(spaceNo,space, fileInfo);
        if(result>0){
            session.setAttribute("alertMsg","수정되었습니다.");
        }else{
            session.setAttribute("alertMsg","수정실패.");
        }
        return "redirect:/space/{spaceNo}";
    }


    //공간 삭제
    @DeleteMapping("/{spaceNo}")
    public String deleteFile(@PathVariable int spaceNo,HttpSession session){
        int result = spaceService.deleteSpace(spaceNo);
        if(result>0){
            session.setAttribute("alertMsg","삭제되었습니다.");
        }else{
            session.setAttribute("alertMsg","삭제실패");
        }
        return "redirect:/";
    }



}
