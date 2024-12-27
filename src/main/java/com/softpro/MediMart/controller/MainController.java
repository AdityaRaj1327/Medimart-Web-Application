package com.softpro.MediMart.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softpro.MediMart.Dao.AdminLoginDao;
import com.softpro.MediMart.Dao.EnquiryDao;
import com.softpro.MediMart.Dao.RegistrationDao;
import com.softpro.MediMart.model.AdminLogin;
import com.softpro.MediMart.model.Enquiry;
import com.softpro.MediMart.model.Registration;
import com.softpro.MediMart.services.AdminLoginRepository;
import com.softpro.MediMart.services.EnquiryRepository;
import com.softpro.MediMart.services.RegistrationRepository;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MainController {
	
	 @Autowired
	 EnquiryRepository erepo;
	 @Autowired
	 RegistrationRepository rrepo;
	 
	 
	 @Autowired
	 private SendEmailService emailService;
	 
	 @Autowired
	 AdminLoginRepository adrepo;

	@GetMapping(value = "/home")
	public String ShowIndex()
	{
		return "index";
		
	}
	@GetMapping(value = "/about")
	public String Showabout()
	{
		return "about";
		
	}
	
	@GetMapping(value = "/contact")
	public String ShowContact( Model model)
	{
		EnquiryDao enquiryDao =new EnquiryDao();
		model.addAttribute("enquiryDao", enquiryDao);
		return "contact";
		
	}
	@PostMapping("/contact")
	public String SubmitEnquiry(@Valid @ModelAttribute EnquiryDao enquiryDao,BindingResult result, RedirectAttributes redirectAttributes)
	{
		if(result.hasErrors())
		{
			
			return "contact";
		}
		
	Enquiry enquiry = new Enquiry();
	enquiry.setName(enquiryDao.getName());
	enquiry.setContactno(enquiryDao.getContactno());
	enquiry.setEmail(enquiryDao.getEmail());
	enquiry.setSubject(enquiryDao.getSubject());
	enquiry.setMessage(enquiryDao.getMessage());
	enquiry.setPosteddate(new Date()+"");
	
	
	erepo.save(enquiry);
    redirectAttributes.addFlashAttribute("message", "Enquiry is saved succesfully");

		
		return "redirect:/contact";
	}
	
	@GetMapping(value = "/registration")
	public String ShowReg(Model model)
	{
		RegistrationDao registrationDao = new RegistrationDao();
		model.addAttribute("registrationDao", registrationDao);
		
		return "registration";
		
	}
	
	@PostMapping("/registration")
	 public String SubmitRegistration(@Valid @ModelAttribute RegistrationDao registrationDao, BindingResult result, RedirectAttributes redirectAttributes)
	{
	if(result.hasErrors())
	{
		
		return "registration";
			
	}
	
	try {
		MultipartFile image =registrationDao.getFile();
		if(image == null || image.isEmpty())
		{
			redirectAttributes.addFlashAttribute("message", "please select an image");
			return "redirect:/registration";
		}
		String storageFileName =new Date().getTime() +"-"+image.getOriginalFilename();
		String UploadDir ="public/profiles/";
		Path uploadPath = Paths.get(UploadDir);
		
		if(!Files.exists(uploadPath))
		{
			Files.createDirectories(uploadPath);
		}
		try(InputStream inputStream = image.getInputStream())
		{
			Files.copy(inputStream, uploadPath.resolve(storageFileName),StandardCopyOption.REPLACE_EXISTING);
			
		}
		
		Registration registration = new Registration();
		registration.setName(registrationDao.getName());
		registration.setGender(registrationDao.getGender());
		registration.setEmail(registrationDao.getEmail());
		registration.setPassword(registrationDao.getPassword());
		registration.setContactno(registrationDao.getContactno());
		registration.setDob(registrationDao.getDob());
		registration.setAddress(registrationDao.getAddress());
		registration.setFile(storageFileName);
		registration.setPostdate(new Date()+"");
		rrepo.save(registration);
		//sending mail
		String CustomerMail = registrationDao.getEmail();
		String CustomerName = registrationDao.getName();
		String CustomerPassword = registrationDao.getPassword();
		
		emailService.SendRegistrationEmail(CustomerMail,CustomerName,CustomerPassword);
		
	
		redirectAttributes.addFlashAttribute("message","Registration Successful");
		return "redirect:/customer";
		
	}catch(Exception ex ) {
	   return "redirect:/registration";
	}
	
	}
	
	@GetMapping(value = "/customer")
	public String ShowCus()
	{
		return "cu";
		
	}
	@PostMapping("/customer")
public String CustomerLogin(@ModelAttribute RegistrationDao registrationDao,HttpSession session,RedirectAttributes redirectAttributes)
{
		try {
			Registration reg = rrepo.getById(registrationDao.getEmail());
			if(reg.getPassword().equals(registrationDao.getPassword()))
			{
				session.setAttribute("userid", registrationDao.getPassword());
				return"redirect:/customerpanel/home";
			}
			else {
				
				redirectAttributes.addFlashAttribute("message", "invalid user");
			}
			
		} catch(Exception ex)
		{
			redirectAttributes.addFlashAttribute("message", "user doesnot exists");
			
		}
	return "redirect:/customer";
}
	
	@GetMapping(value = "/admin")
	public String ShowAdmin(Model model)
	{
		
		AdminLoginDao adminLoginDao = new  AdminLoginDao();
		model.addAttribute("adminLoginDao", adminLoginDao);
		
		return "admin";
		
		
	}
	
	@PostMapping(value ="/admin")
	public String AdminSignIn(@ModelAttribute AdminLoginDao adminLoginDao,HttpSession session, RedirectAttributes redirectAttributes)
	{
		
		
		try {
			AdminLogin adminLogin =  adrepo.getById(adminLoginDao.getUserid());
			if(adminLogin.getPassword().equals(adminLoginDao.getPassword()))
			{
				session.setAttribute("userid", adminLogin.getUserid());
				return "redirect:/adminpanel/home";
			}	
			
		}
		catch(Exception e)
		{
			redirectAttributes.addFlashAttribute("mesasage", "userid doesnot exist");
			
		}
		return "redirect:/admin";
		
		
	}
	
	@GetMapping(value = "/services")
	public String ShowServices()
	{
		return "services";
		
	}
	
	
	
	
}
