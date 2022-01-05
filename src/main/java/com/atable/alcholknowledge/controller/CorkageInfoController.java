package com.atable.alcholknowledge.controller;

import com.atable.alcholknowledge.model.CorkageInfo;
import com.atable.alcholknowledge.service.CorkageInfoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class CorkageInfoController {

    private final CorkageInfoService corkageInfoService;

    @Autowired
    public CorkageInfoController(CorkageInfoService corkageInfoService) {
        this.corkageInfoService = corkageInfoService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/api/corkage-info/list")
    @ResponseBody
    public String ckInfoListToJson() throws JsonProcessingException {
        List<CorkageInfo> stores = corkageInfoService.findCkInfos();
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(stores);
        }
        catch (Exception e) {
            System.out.println("error : " + e);
        }
        return json;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("api/corkage-info/new")
    public String createCkInfoFromJson(@RequestBody CorkageInfoForm ckInfoForm) throws JsonProcessingException {
        CorkageInfo corkageInfo = new CorkageInfo();
        corkageInfo.setAddr(ckInfoForm.getAddr());
        corkageInfo.setDesc(ckInfoForm.getDesc());
        corkageInfo.setDateCreate(LocalDateTime.now());
        corkageInfoService.register(corkageInfo);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(ckInfoForm));

        return "redirect:/corkage-store/list";
    }

    @GetMapping("/corkage-info/new")
    public String createForm() {
        return "corkage/createCkInfoForm";
    }

    @PostMapping("/corkage-info/new")
    public String create(CorkageInfoForm ckInfoForm) {
        CorkageInfo corkageInfo = new CorkageInfo();
        corkageInfo.setAddr(ckInfoForm.getAddr());
        corkageInfo.setDesc(ckInfoForm.getDesc());
        corkageInfo.setDateCreate(LocalDateTime.now());
        corkageInfoService.register(corkageInfo);
        return "redirect:/corkage-store/list";
    }

    @GetMapping("/corkage-info/list")
    public String list(Model model) {
        List<CorkageInfo> stores = corkageInfoService.findCkInfos();
        model.addAttribute("stores", stores);
        return "corkage/ckInfoList";
    }
}