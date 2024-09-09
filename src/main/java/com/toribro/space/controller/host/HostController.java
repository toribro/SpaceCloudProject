package com.toribro.space.controller.host;

import com.toribro.space.domain.dto.space.SpaceDto;
import com.toribro.space.domain.entity.member.Member;
import com.toribro.space.service.space.SpaceService;
import jakarta.annotation.Resource;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


@Controller
@RequestMapping("/host")
@Slf4j
@RequiredArgsConstructor
public class HostController {

    private final SpaceService spaceService;

    @Value("${file.dir}")
    private String uploadDir;

    @GetMapping
    public String hostCenter(){
        return "host/hostMainPage";
    }
    @GetMapping("/pre")
    public String hostEnrollPre(){
        return "host/hostEnrollFormPre";
    }
    @GetMapping("/update")
    public String hostUpdateForm(){
        return "host/hostUpdateForm";
    }
    @GetMapping("/spacelist")
    public String hostSpaceList(){
        return "host/hostSpaceList";
    }


    //공간등록폼
    @GetMapping("/enroll")
    public String hostEnrollGet(Model model){
        model.addAttribute("space",new SpaceDto.EnrollDto());
        return "host/hostEnrollFormMain";
    }


    //공간등록
    @PostMapping("/enroll")
    public String hostEnrollPost(@ModelAttribute SpaceDto.EnrollDto space,
                                 List<MultipartFile> files,HttpSession session){

        Member member = (Member) session.getAttribute("loginUser");
        space.setUserNo(member.getUserNo());

        log.info("files: {}",files);
        log.info("{}",space);
        log.info("{}",uploadDir);
        List<SpaceDto.SpaceAttachmentDto> fileInfo= new CopyOnWriteArrayList<>();



        int level=1;
        if(files!=null) {

            for(MultipartFile f :files) {
                SpaceDto.SpaceAttachmentDto file=new SpaceDto.SpaceAttachmentDto();
                String changeName=saveFile(f,session,uploadDir);
                file.setFileLevel(level++);
                file.setOriginName(f.getOriginalFilename());
                file.setChangeName(changeName);
                file.setFilePath(uploadDir);
                fileInfo.add(file);
            }
        }
        log.info("{}",fileInfo);

        int result = spaceService.enroll(space, fileInfo);
        if(result>0){
            session.setAttribute("alertMsg","등록되었습니다.");
            return "redirect:/host";
        }else{
            session.setAttribute("alertMsg","등록되었습니다.");
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

}
