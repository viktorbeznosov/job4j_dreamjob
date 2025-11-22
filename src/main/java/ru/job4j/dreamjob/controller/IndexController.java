package ru.job4j.dreamjob.controller;

import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@ThreadSafe
@Controller
@SuppressWarnings("unused")
public class IndexController {
    @GetMapping("/index")
    public String getIndex() {
        log.error("Some error occured");
        log.warn("Some warn occured");
        log.info("Some info occured");
        return "index";
    }
}
