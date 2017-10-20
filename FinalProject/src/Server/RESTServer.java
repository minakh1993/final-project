package Server;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import EntityManager.PhoneNumberEntityManager;
import EntityManager.UserEntityManager;
import POJO.PhoneRecord;
import POJO.User;
import WindowClient.JsonHandler;

@Path("/user")
public class RESTServer {
	
	
	
	
	//login validation
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public User loginValidation(User user){
			UserEntityManager userManager=new UserEntityManager();
			User validatedUser=userManager.userValidation(user);
			if(validatedUser==null){
				//wrong user or pass
				return null;
			}else{
				validatedUser.setPassword("0");
				return validatedUser;
			}
			
		}
		
		@Path("/signUp")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response signup(User user){
			UserEntityManager userManager=new UserEntityManager();
			boolean status=userManager.userSignUp(user);
			if(status){
				return Response.status(200).entity("success").build();
				
			}else{
				return Response.status(200).entity("failed").build();
			}
			
		}
		
		
		@Path("/addContact")
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response addContact(String contactEnter){
			JsonHandler jsonHandler=new JsonHandler();
			System.out.println(contactEnter);
			PhoneRecord contact=(PhoneRecord) jsonHandler.createObject(contactEnter, PhoneRecord.class);
			
			PhoneNumberEntityManager entityManager=new PhoneNumberEntityManager();
			boolean status= entityManager.addContact(contact);
			 if (status){
				 return Response.status(200).entity("success").build();
				 
			 }else{
				 return Response.status(200).entity("failed").build();
				 
			 }
			
		}


}
