package br.com.mamute.cotacaoapi.adminController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErroController implements ErrorController  {

@RequestMapping("/error")
public String Erro(HttpServletRequest request) {
   Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

   if (status != null) {
       Integer statusCode = Integer.valueOf(status.toString());

       if(statusCode == HttpStatus.NOT_FOUND.value()) {
           return "pagina-erro/erro404";
       }
       else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
           return "pagina-erro/erro500";
       }
       if(statusCode == HttpStatus.BAD_REQUEST.value()) {
           return "pagina-erro/erro404";
       }
       else if(statusCode == HttpStatus.FORBIDDEN.value()) {
           return "pagina-erro/erro403";
       }
   }
   return "pagina-erro/error404";
}

   public String getErrorPath() {
       return "/error";
   }
}
