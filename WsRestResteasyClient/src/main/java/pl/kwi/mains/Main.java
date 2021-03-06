package pl.kwi.mains;

import java.text.MessageFormat;
import java.util.List;

import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import pl.kwi.entities.UserEntity;
import pl.kwi.entities.UsersEntity;

public class Main {
	
	
	private static final String URL = "http://localhost:8080/WsRestResteasyServer";

	/**
	 * Main method of client.
	 * 
	 * @param args array of String from command line
	 */
	public static void main(String[] args) {
		
		String option = args[0];
		String arg1 = null;
		String arg2 = null;
		
		if(args.length >= 2){
			arg1 = args[1];
		}
		
		if(args.length >= 3){
			arg2 = args[2];
		}
		
		Main main = new Main();
		
		if("-create".equals(option)){
			main.create(arg1);
		}
		if("-read".equals(option)){
			main.read(arg1);
		}
		if("-readAll".equals(option)){
			main.readAll();
		}
		if("-update".equals(option)){
			main.update(arg1, arg2);
		}
		if("-delete".equals(option)){
			main.delete(arg1);
		}
		
	}
	
	public void create(String name){
		
		UserEntity user = new UserEntity();
		user.setName(name);
		
		ClientRequest request = new ClientRequest(URL + "/rest/user");
		ClientResponse<UserEntity> response = null;
		try {
			response = 
					request.
					accept(MediaType.APPLICATION_XML).
					body(MediaType.APPLICATION_XML, user).
					put(UserEntity.class);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
			
		System.out.println("Status: " + response.getStatus());
		
		user = response.getEntity(UserEntity.class);
				
		System.out.println(MessageFormat.format("Create user with id: {0} and name: {1}", user.getId(), user.getName()));
		
	}
	
	public void read(String id){
		
		ClientRequest request = new ClientRequest(URL + "/rest/user/" + id);
		ClientResponse<UserEntity> response = null;
		try {
			response = 
					request.
					accept(MediaType.APPLICATION_XML).
					get(UserEntity.class);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		System.out.println("Status: " + response.getStatus());
		
		UserEntity user = response.getEntity(UserEntity.class);
		
		System.out.println(MessageFormat.format("Read user with id: {0} and name: {1}", user.getId(), user.getName()));
		
	}
	
	public void readAll(){
		
		ClientRequest request = new ClientRequest(URL + "/rest/user");
		ClientResponse<UsersEntity> response = null;
		try {
			response = 
					request.
					accept(MediaType.APPLICATION_XML).
					get(UsersEntity.class);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		System.out.println("Status: " + response.getStatus());
		
		UsersEntity users = response.getEntity(UsersEntity.class);
		List<UserEntity> userList = users.getUsers();
		
		for (UserEntity user : userList) {
			System.out.println(MessageFormat.format("Read user with id: {0} and name: {1}", user.getId(), user.getName()));
		}
		
		
	}
	
	public void update(String id, String name){
		
		UserEntity user = new UserEntity();
		user.setId(Long.valueOf(id));
		user.setName(name);
		
		ClientRequest request = new ClientRequest(URL + "/rest/user");
		ClientResponse<UserEntity> response = null;
		try {
			response = 
					request.
					accept(MediaType.APPLICATION_XML).
					body(MediaType.APPLICATION_XML, user).
					post(UserEntity.class);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		System.out.println("Status: " + response.getStatus());
		
		user = response.getEntity(UserEntity.class);
				
		System.out.println(MessageFormat.format("Update user with id: {0} and name: {1}", user.getId(), user.getName()));
		
	}
	
	public void delete(String id){
		
		ClientRequest request = new ClientRequest(URL + "/rest/user/" + id);
		ClientResponse response = null;
		try {
			response = 
					request.
					delete();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		System.out.println("Status: " + response.getStatus());
		
		System.out.println(MessageFormat.format("Delete user with id: {0}", id));
		
	}

}
