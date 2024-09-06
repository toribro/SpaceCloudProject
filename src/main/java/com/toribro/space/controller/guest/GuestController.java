package com.toribro.space.controller.guest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guest")
public class GuestController {

    @GetMapping("/pickedList")
    public String pickedList(){
        return "guest/guestPickedList";
    }
    @GetMapping("/reservationList")
    public String reservationList(){

        return "guest/guestReservationList";
    }

}
