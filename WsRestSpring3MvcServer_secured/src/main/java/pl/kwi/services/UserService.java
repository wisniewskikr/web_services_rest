package pl.kwi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.kwi.daos.UserDao;
import pl.kwi.entities.UserEntity;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public Long createUser(UserEntity user){
		
		userDao.create(user);
		return user.getId();
		
	}
	
	public UserEntity readUser(Long id){
		
		return userDao.read(id);
		
	}
	
	public void updateUser(UserEntity user){
		
		userDao.update(user);
		
	}
	
	public void deleteUser(UserEntity user){
		
		userDao.delete(user);
		
	}
	
	public void deleteUser(Long id){
		
		UserEntity user = userDao.read(id);
		userDao.delete(user);
		
	}
	
	public List<UserEntity> getUserList(){
		
		return userDao.findAll();
		
	}

}
