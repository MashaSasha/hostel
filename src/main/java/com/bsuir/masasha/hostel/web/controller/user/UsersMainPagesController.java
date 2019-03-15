package com.bsuir.masasha.hostel.web.controller.user;

import com.bsuir.masasha.hostel.core.domain.Hotel;
import com.bsuir.masasha.hostel.core.service.HotelEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.bsuir.masasha.hostel.web.WebConstants.BASKET_ATR;

@Controller
public class UsersMainPagesController {

    @Autowired
    HotelEditService hotelEditService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model) {
        Hotel hotel = hotelEditService.findHotel();
        model.addAttribute("hotel", hotel);
        return "user/main";
    }

    @GetMapping("/booking")
    public String booking(Model model, HttpServletRequest request) {

        model.addAttribute(BASKET_ATR, request.getSession(true).getAttribute(BASKET_ATR));
        return "user/book";
    }



//
//    @GetMapping("/main")
//    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
//        Iterable<Message> messages;
//
//        if (filter == null || filter.isEmpty()) {
//            messages = messageRepository.findAll();
//        } else {
//            messages = messageRepository.findByTag(filter);
//        }
//
//        model.addAttribute("messages", messages);
//        model.addAttribute("filter", filter);
//
//        return "user/main";
//    }
//
//    @PostMapping("/main")
//    public String add(
//            @AuthenticationPrincipal User user,
//            @RequestParam String text,
//            @RequestParam String tag, Map<String, Object> model,
//            @RequestParam("file") MultipartFile file
//    ) throws IOException {
//        Message message = new Message(text, tag, user);
//
//        if (file != null) {
//            File uploadDir = new File(uploadPath);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//
//            String uuidFile = UUID.randomUUID().toString();
//            String resultFilename = uuidFile + "." + file.getOriginalFilename();
//
//            file.transferTo(new File(uploadPath + "/" + resultFilename));
//
//            message.setFilename(resultFilename);
//        }
//
//        messageRepository.save(message);
//
//        model.put("filter", "");
//
//        Iterable<Message> messages = messageRepository.findAll();
//        model.put("messages", messages);
//        return "main";
//    }

}