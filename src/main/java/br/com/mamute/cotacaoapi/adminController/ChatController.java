package br.com.mamute.cotacaoapi.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dashboard-admin")
public class ChatController {

	@GetMapping("app-chat.html")
	String chat() {
		return"app-chat";
	}
}
