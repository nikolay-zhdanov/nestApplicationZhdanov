package com.chisw.web;

import java.io.IOException;
import java.util.Map;

import com.chisw.bo.TermostatServiceBO;
import com.chisw.service.Application;
import com.chisw.service.HelloWorldService;
import com.chisw.service.NestAuthentication;
import com.chisw.service.TermostatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
public class NestController {

	private final Logger logger = LoggerFactory.getLogger(NestController.class);
	private final HelloWorldService helloWorldService;

	@Autowired
	public NestController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {

		logger.debug("index() is executed!");
		
		return "index";
	}

	@RequestMapping(value = "/authorize", method = RequestMethod.POST)
	public ModelAndView authorize(@RequestParam Map<String, String> params, HttpSession session) throws IOException {
		String auth = params.get("auth");
		logger.info(auth);
		TermostatService service = createService(auth);
		session.setAttribute("nestService", service);
		ModelAndView model = new ModelAndView();
		model.addObject("auth", auth);

		model.setViewName("main");
		return model;
	}

	@RequestMapping(value = "/values", method = RequestMethod.POST)
	public ModelAndView setValues(@RequestParam Map<String, String> params, HttpSession session) throws IOException {
		String auth = params.get("auth");
		TermostatService service = (TermostatService) session.getAttribute("nestService");
		String fun = params.get("fun");
		logger.info(fun);
		if (fun != null && !"".equals(fun)) {
			if (fun.equals("off")) {
				service.disableTermostatFan(NestAuthentication.TERMOSTAT_ID);
			} else {
				service.enableTermostatFan(NestAuthentication.TERMOSTAT_ID);
			}
		}
		String low_temp = params.get("low_temp");
		logger.info(low_temp);
		if (low_temp != null && !"".equals(low_temp)) {
			service.setLowTermostatTemperature(NestAuthentication.TERMOSTAT_ID, Float.parseFloat(low_temp));
		}
		String high_temp = params.get("high_temp");
		logger.info(high_temp);
		if (high_temp != null && !"".equals(high_temp)) {
			service.setHighTermostatTemperature(NestAuthentication.TERMOSTAT_ID, Float.parseFloat(high_temp));
		}
		ModelAndView model = new ModelAndView();
		model.addObject("auth", auth);
		model.addObject("fun", fun);
		model.addObject("low_temp", low_temp);
		model.addObject("high_temp", high_temp);
		model.addObject("msg", "Success");

		model.setViewName("main");
		return  model;
	}


	private TermostatService createService(String auth) throws IOException {
		Application application = new Application();
		application.start(auth);
		return  new TermostatService(application.getNestAuthentication());
	}

}