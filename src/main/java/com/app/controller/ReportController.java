package com.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.app.VerificationService;
import com.app.util.FileUtil;

@Controller
public class ReportController {
	
	@Autowired
	private VerificationService verificationService;
	
	private static final Logger log = LoggerFactory.getLogger(ReportController.class);

	@PostMapping("/verify-report")
	public String verifyReport(@RequestParam("reportFile") MultipartFile reportFile, Model model) throws Exception {

		String fileName = reportFile.getOriginalFilename();
		long fileSize = reportFile.getSize();
		
		log.info("OriginalFilename：{}", fileName);
		log.info("Size: {}", fileSize);
		
		String hashValue = FileUtil.calculateSHA256(reportFile.getInputStream());
		String verificationResult = verificationService.verifyHash(hashValue);
		
		model.addAttribute("fileName", "File Name: " + fileName);
		model.addAttribute("hashValue", "Hash Value: " + hashValue);
		model.addAttribute("verificationResult", "Verification Result: " + verificationResult);
		
		return "index";
	}

}
