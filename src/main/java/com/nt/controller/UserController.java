package com.nt.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.entities.Credentials;
import com.nt.entities.TempPass;
import com.nt.entities.UserInfo;
import com.nt.entities.Userdummy;
import com.nt.service.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	@Autowired
	private UserServiceImpl service;

	@GetMapping("/")
	public String login(Model model) {
		log.info("login page is executed");
		model.addAttribute("cr", new Credentials());
		return "login";
	}// login

	@PostMapping("/result")
	public String result(@ModelAttribute Credentials cr, RedirectAttributes rd) {

		boolean flag = service.existByMail(cr);
		if (flag) {
			String mail = cr.getEmail();
			List<Credentials> Mail = service.findByMail(mail);
			String formpass = cr.getPassword();
			int status = Mail.get(0).getUser().getStatus();
			String dbpass = Mail.get(0).getPassword();

			System.out.println(flag);

			if (status == 1 && formpass.equalsIgnoreCase(dbpass)) {
				log.info("welcome page is executed");
				return "welcome";
			} // inner if1
			if (status == 0)
				log.warn("account is in dective mode");
			rd.addFlashAttribute("error", "account is in dective mode");
			return "status";
		} // if1
		log.error("wrong credential enterd");
		rd.addFlashAttribute("error", "wrong credentials");
		return "redirect:./";
	}// result

	@GetMapping("/logout")
	public String logout() {
		log.info("logout activitie done");
		return "redirect:./";
	}// logout

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("user", new Userdummy());
		model.addAttribute("countries", service.getAllContry());
		model.addAttribute("states", service.getAllState());
		model.addAttribute("cities", service.getAllCity());
		log.info("acount is registering");
		return "signup";
	}

	@PostMapping("/signups")
	public String signup(@ModelAttribute Userdummy user, RedirectAttributes rd, Model model) {
		String dumPass = "abc" + new Random().nextLong();
		UserInfo copyData = service.copyData(user);
		Credentials crd = new Credentials();
		crd.setEmail(user.getEmail());
		if (service.existByMail(crd)) {
			model.addAttribute("user", user);
			model.addAttribute("countries", service.getAllContry());
			model.addAttribute("states", service.getAllState());
			model.addAttribute("cities", service.getAllCity());
			model.addAttribute("msg", "choose unique mail id");
			log.info("user is eneterd dublicate mail id:" + user.getEmail());
			return "signup";
		} // if

		crd.setPassword(dumPass);
		crd.setUser(copyData);
		copyData.setCredentials(crd);
		service.saveUser(copyData);
		String subject = "UNLOCK IES ACCOUNT ";
		String to = user.getEmail();
		String body = "from:sairiraghu223@gmail.com" + "\n To:" + user.getEmail() + "\nSubject:" + subject
				+ "\n Body:\n" + "hi wardhan\n" + "welcome to IES family,your registration is alomest complete.\n"
				+ "please unlock your account using below details.\n" + "Temporary Password:" + dumPass + "\nhere : "
				+ "http://localhost:8080/confirm-account?token=" + crd.getEmail();

		boolean sendMail = service.sendMail(to, body, subject);
		if (sendMail) {
			rd.addFlashAttribute("msg", "mail is sent to" + user.getEmail());
			log.info("mail is sent to" + user.getEmail());
		} else {
			log.info("sendtin mail is faile to" + user.getEmail());
			rd.addFlashAttribute("msg", "sendin mail is failed");
		}
		return "redirect:/signup";
	}// mail
		// http://localhost:8080/confirm-account?token=wardhan778@gmail.com

	@GetMapping("/confirm-account")
	public String confirm(@RequestParam("token") String email, Model model) {
		log.info("tempfrom page is executed by clicking url from:" + email);
		System.out.println("Get confirm");
		TempPass tp = new TempPass();
		tp.setEmail(email);
		model.addAttribute("tp", tp);

		return "tempform";
	}// confirm

	@PostMapping("/confirm")
	public String confirm(@ModelAttribute TempPass tp, RedirectAttributes model) {
		System.out.println(tp);
		String dbPass = service.findPasswordByMail(tp.getEmail());
		String tempPass = tp.getTempPass();
		String newPass = tp.getNewPass();
		String cfrmPass = tp.getCfrmpass();
		System.out.println("TempPass:" + tempPass);
		System.out.println("TempPass:" + newPass);
		System.out.println("TempPass:" + cfrmPass);
		if (dbPass.equalsIgnoreCase(tempPass) && newPass.equalsIgnoreCase(cfrmPass)) {
			service.updatePassByMail(tp.getEmail(), cfrmPass);
			log.info("user account is created with mail -id:" + tp.getEmail());
			return "welcome";
		} // if

		else {
			model.addFlashAttribute("msg", "Wrong Entries EnterCorrect one");
			log.warn("wrong code is enterd that we have sent to mail by " + tp.getEmail());
			return "redirect:/confirm-account?token=" + tp.getEmail();

		}
	}// confirm

	@GetMapping("/forgot")
	public String forGot(Model model) {
		log.info("for got method is executed");
		System.out.print("get forgot method");
		model.addAttribute("tp", new TempPass());
		return "mail";
	}// for got

	@PostMapping("/forgot")
	public String forGot(@ModelAttribute TempPass tp, RedirectAttributes model) {
		System.out.println(tp);
		Credentials cr = new Credentials();
		cr.setEmail(tp.getEmail());
		if (service.existByMail(cr)) {
			String mailPass = service.findPasswordByMail(tp.getEmail());
			String subject = "UNLOCK IES ACCOUNT ";
			String to = tp.getEmail();
			String body = "from:sairiraghu223@gmail.com" + "\n To:" + tp.getEmail() + "\nSubject:" + subject
					+ "\n Body:\n" + "hi wardhan\n" + "welcome to IES family"
					+ "please unlock your account using below details.\n" + "Your  Password:"
					+ service.findPasswordByMail(tp.getEmail());

			boolean sendMail = service.sendMail(to, body, subject);
			if (sendMail) {
				model.addFlashAttribute("msg", "Your password is sent to your mail-id");
				log.info("forgot password is sento mail-id:" + tp.getEmail());
			}
		} //
		else {
			log.info("wrong enterd by user mail -id:" + tp.getEmail());
			model.addFlashAttribute("msg", "wrong mail id");
		}
		return "redirect:/forgot";
	}// for got

}
