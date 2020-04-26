package com.divvy.savean.controller;

import java.util.List;
import java.util.Optional;

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

import com.divvy.savean.model.PasswordPojo;
import com.divvy.savean.dao.UserDao;
import com.divvy.savean.model.User;

@RestController
@RequestMapping("/user")
public class UserController
{
	@Autowired
	UserDao userDao;
	
	@GetMapping("/")
	@ResponseBody
	public List<User> getAllUser()
	{
		return userDao.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Optional<User> getOneUser(@PathVariable("id") long id)
	{
		return userDao.findById(id);
	}
	
	@PostMapping("/add")
	@ResponseBody
	public User addUser(@RequestBody User u)
	{
		return userDao.save(u);
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public String deleteUser(long id)
	{
		userDao.deleteById(id);
		return "deleted";
	}
	
	@PutMapping("/update")
	public User updateUser(@RequestBody User u)
	{
		userDao.deleteById(u.getId());
		return userDao.save(u);
	}
	
	@PostMapping("/login")
	@ResponseBody
	public User login(@RequestBody User u)
	{
		if(userDao.login(u.getUsername(), u.getPassword()).size() > 0)
		{
			return u;
		}
		return null;
	}
	
	@PostMapping("/changePassword")
    @ResponseBody
    public boolean changePassword(@RequestBody PasswordPojo p)
    {
		Optional<User> u = userDao.findByUsername(p.getUsername());
    	User user = u.get();
    	
    	if(user.getPassword().equals(p.getCurrentPassword())) {
    		userDao.updatePassword(p.getNewPassword(), p.getUsername());
    		return true;
    	}
    	return false;
    }
}
