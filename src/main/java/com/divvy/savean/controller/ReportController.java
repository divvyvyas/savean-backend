package com.divvy.savean.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.divvy.savean.model.Email;
import com.divvy.savean.model.Otp;
import com.divvy.savean.controller.EmailController;
import com.divvy.savean.controller.FileController;
import com.divvy.savean.dao.ReportDao;
import com.divvy.savean.model.Report;
import com.divvy.savean.model.ReportPojo;

@RestController
@RequestMapping("report")
public class ReportController
{
	Map<String, Long> otpStorage = new HashMap<>();
	
	@Autowired
	EmailController econ;
	
	@Autowired
	ReportDao reportDao;
	
	@Autowired
	FileController fileController;
	
	@GetMapping("/")
	@ResponseBody
	public List<Report> getAllReport()
	{
		return reportDao.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Optional<Report> getOneReport(@PathVariable("id")long id)
	{
		return reportDao.findById(id);
	}
	
	@PostMapping("/add")
	@ResponseBody
	public Report addReport(ReportPojo reportPojo)
	{
		Report report = new Report();
		if(reportPojo.getFile()!=null)
		{
			String downloadPath = fileController.uploadFile(reportPojo.getFile()).getFileDownloadUri();
			report.setFilePath(downloadPath);
		}
		report.setEmail(reportPojo.getEmail());
		report.setName(reportPojo.getName());
		report.setMessage(reportPojo.getMessage());
		report.setLatitude(reportPojo.getLatitude());
		report.setLongitude(reportPojo.getLongitude());
		return reportDao.save(report);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public String deleteReport(@PathVariable("id")long id)
	{
		reportDao.deleteById(id);
		return "deleted";
	}
	
	@PutMapping("/update")
	@ResponseBody
	public Report updateReport(@RequestBody Report r)
	{
		reportDao.deleteById(r.getId());
		return reportDao.save(r);
	}
	
	@GetMapping("/sendOtp/{email}")
	@ResponseBody
	public void sendOtp(@PathVariable String email) {
		Random r = new Random();
		Otp otp = new Otp();
		otp.setOtp(r.nextInt(899999) + 100000);
		if(otpStorage.containsKey(email)) {
			otpStorage.replace(email, otp.getOtp());
		}
		else {
			otpStorage.put(email, otp.getOtp());
		}
		Email emailobj = new Email();
		emailobj.setMsgTo(email);
		emailobj.setSubject("OTP for Registration");
		emailobj.setMessage("your OTP for Registration is " + otp.getOtp());
		econ.sendMail(emailobj);
	}
	
	@PostMapping("/checkotp")
	@ResponseBody
	public boolean checkOtp(Otp o) {
		System.out.println(o.getOtp());
		System.out.println(otpStorage.get(o.getEmail()));
		if (otpStorage.get(o.getEmail()) == o.getOtp()) {
			otpStorage.remove(o.getEmail());
			return true;
		} else
			return false;
	}
}
