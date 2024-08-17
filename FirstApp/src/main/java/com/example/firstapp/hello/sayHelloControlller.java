package com.example.firstapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // thats used for spring handles web requests

public class sayHelloControlller {

    @RequestMapping("say-hello") // for url.
    @ResponseBody // to return string as it is.
    public String SayHello() {
        return " Hello! What are you learning today";
    }

    @RequestMapping("say-hello-html")
    @ResponseBody
    public String SayHelloHtml() { // this way is not used. Use view technologies. -JSP - Java Server Page
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>  My First HTML page -Changed");
        sb.append("</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("Hello! What are you learning today");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();

    }

    @RequestMapping("say-hello-jsp")
    public String SayHelloJSP() {
        return "sayHello"; // name of the jsp file. Dont forget to add prefix and suffix to application
                           // prop.
    }
}
