package Server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.research.ws.wadl.Application;

import EntityManager.EventEntityManager;
import EntityManager.PhoneNumberEntityManager;
import EntityManager.UserEntityManager;
import POJO.ID;
import POJO.PhoneRecord;
import POJO.User;
import WindowClient.JsonHandler;

@Path("/user")
public class RESTServer {

	// first string is the client IP and the value is user information
	private static Map<String, User> onlineUsers = new HashMap<String, User>();
	private static User guest = new User("guest", " ", " ", " ", " ", 0);

	// login validation
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User loginValidation(User user, @Context HttpServletRequest request) {
		// Receiving the client remote address
		String ip = request.getRemoteAddr();
		System.out.println(ip);

		UserEntityManager userManager = new UserEntityManager();
		User validatedUser = userManager.userValidation(user);
		if (validatedUser == null) {
			// wrong user or pass

			EventDescriptionBuilder description = new EventDescriptionBuilder("wrong username or password", ip,
					"no result");
			new EventEntityManager().saveEvent(description.toString(), guest);

			return null;
		} else {
			// add this person to online users

			onlineUsers.put(ip, validatedUser);

			EventDescriptionBuilder description = new EventDescriptionBuilder("successfully logged in", ip,
					"logged in");
			new EventEntityManager().saveEvent(description.toString(), validatedUser);

			return validatedUser;
		}

	}

	@Path("/signUp")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response signup(User user, @Context HttpServletRequest request) {
		String ip = request.getRemoteAddr();

		UserEntityManager userManager = new UserEntityManager();
		boolean status = userManager.userSignUp(user);
		if (status) {

			EventDescriptionBuilder description = new EventDescriptionBuilder("signed up successfully", ip, "sign Up");
			new EventEntityManager().saveEvent(description.toString(), user);
			return Response.status(200).entity("success").build();

		} else {

			EventDescriptionBuilder description = new EventDescriptionBuilder("sign up tried-wrong information", ip,
					"sign Up");
			new EventEntityManager().saveEvent(description.toString(), guest);
			return Response.status(200).entity("failed").build();
		}

	}

	@Path("/addContact")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addContact(String contactEnter, @Context HttpServletRequest request) {
		String ip = request.getRemoteAddr();

		// check if this client has loged in
		if (onlineUsers.containsKey(ip)) {
			JsonHandler jsonHandler = new JsonHandler();
			System.out.println(contactEnter);
			PhoneRecord contact = (PhoneRecord) jsonHandler.createObject(contactEnter, PhoneRecord.class);

			PhoneNumberEntityManager entityManager = new PhoneNumberEntityManager();
			boolean status = entityManager.addContact(contact);
			if (status) {

				EventDescriptionBuilder description = new EventDescriptionBuilder("added a contact", ip,
						"add contact-" + contactEnter);
				new EventEntityManager().saveEvent(description.toString(), onlineUsers.get(ip));
				return Response.status(200).entity("success").build();

			} else {
				EventDescriptionBuilder description = new EventDescriptionBuilder("wrong contact information", ip,
						"add contact");
				new EventEntityManager().saveEvent(description.toString(), onlineUsers.get(ip));
				return Response.status(200).entity("failed").build();

			}
		} else {
			// this IP hasn't logged in yet

			EventDescriptionBuilder description = new EventDescriptionBuilder("Attacker", ip, "add contact");
			new EventEntityManager().saveEvent(description.toString(), guest);
			return Response.status(405).entity("access denied").build();
		}

	}

	@Path("/showAllContacts")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PhoneRecord> showAllContacts(@Context HttpServletRequest request) {

		String ip = request.getRemoteAddr();
		System.out.println("showint all contacts");
		PhoneNumberEntityManager entityManager = new PhoneNumberEntityManager();

		EventDescriptionBuilder description = new EventDescriptionBuilder("show all contacts", ip, "show all contacts");
		new EventEntityManager().saveEvent(description.toString(), guest);

		return entityManager.showAllContacts();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteContact(ID id, @Context HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		// this client should have at least access level 2
		User user = onlineUsers.get(ip);
		if (user == null) {
			// not logged in
			EventDescriptionBuilder description = new EventDescriptionBuilder("Atacker", ip,
					"Delete contact  " + id.getName() + " " + id.getFamily());
			new EventEntityManager().saveEvent(description.toString(), guest);
			return Response.status(405).entity("Access denied").build();

		} else {
			if (user.getAccessLevel() >= 2) {

				System.out.println("delete contact " + id.getName() + " " + id.getFamily());
				PhoneNumberEntityManager entityManager = new PhoneNumberEntityManager();
				boolean status = entityManager.deleteContact(id);
				if (status) {

					EventDescriptionBuilder description = new EventDescriptionBuilder("deleted successfully", ip,
							"Delete contact  " + id.getName() + " " + id.getFamily());
					new EventEntityManager().saveEvent(description.toString(), user);
					return Response.status(200).entity("success").build();
				} else {

					EventDescriptionBuilder description = new EventDescriptionBuilder("failed to delete", ip,
							"Delete contact  " + id.getName() + " " + id.getFamily());
					new EventEntityManager().saveEvent(description.toString(), user);
					return Response.status(200).entity("failed").build();

				}

			}else{
				EventDescriptionBuilder description = new EventDescriptionBuilder("Access denied", ip,
						"Delete contact  " + id.getName() + " " + id.getFamily());
				new EventEntityManager().saveEvent(description.toString(), user);
				return Response.status(405).entity("Access denied").build();
			}
		}

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateContact(PhoneRecord phoneRecord, @Context HttpServletRequest request) {

		// access level manager or above
		String ip = request.getRemoteAddr();
		User user = onlineUsers.get(ip);
		if (user == null) {
			EventDescriptionBuilder description = new EventDescriptionBuilder("Attacker", ip,
					"update contact  "+phoneRecord.getId().getName()+" "+phoneRecord.getId().getFamily() );
			new EventEntityManager().saveEvent(description.toString(), guest);
			return Response.status(405).entity("Access denied").build();

		} else if (user.getAccessLevel() >= 2) {
			
			PhoneNumberEntityManager entityManager = new PhoneNumberEntityManager();
			boolean status = entityManager.updateContact(phoneRecord);
			if (status) {

				EventDescriptionBuilder description = new EventDescriptionBuilder("update contact successfully", ip,
						"update contact  "+phoneRecord.getId().getName()+" "+phoneRecord.getId().getFamily() );
				new EventEntityManager().saveEvent(description.toString(), user);
				return Response.status(200).entity("success").build();
			} else {
				
				EventDescriptionBuilder description = new EventDescriptionBuilder(" failed update contact ", ip,
						"update contact  "+phoneRecord.getId().getName()+" "+phoneRecord.getId().getFamily() );
				new EventEntityManager().saveEvent(description.toString(), user);
				return Response.status(200).entity("failed").build();

			}

		} else {

			EventDescriptionBuilder description = new EventDescriptionBuilder(" Access denied ", ip,
					"update contact  "+phoneRecord.getId().getName()+" "+phoneRecord.getId().getFamily() );
			new EventEntityManager().saveEvent(description.toString(), user);
			return Response.status(405).entity("Access denied").build();
		}

	}

	@Path("/showAllUsers")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> showAllUsers(@Context HttpServletRequest request) {

		// access level of general manager
		String ip = request.getRemoteAddr();
		User user = onlineUsers.get(ip);
		if (user == null) {

			EventDescriptionBuilder description = new EventDescriptionBuilder(" Attacker ", ip,
					"show all users  ");
			new EventEntityManager().saveEvent(description.toString(), guest);
			return null;
		} else if (user.getAccessLevel() == 3) {

			EventDescriptionBuilder description = new EventDescriptionBuilder(" showed all users successfully ", ip,
					"show all users  ");
			new EventEntityManager().saveEvent(description.toString(), user);
			UserEntityManager entityManager = new UserEntityManager();
			return entityManager.showAllUsers();

		} else {
			EventDescriptionBuilder description = new EventDescriptionBuilder(" Access denied ", ip,
					"show all users  ");
			new EventEntityManager().saveEvent(description.toString(), user);
			return null;
		}

	}

	@Path("/updateUser")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(User user, @Context HttpServletRequest request) {

		// access level for general manager
		String ip = request.getRemoteAddr();
		User client = onlineUsers.get(ip);
		
		if (client == null) {
			
			EventDescriptionBuilder description = new EventDescriptionBuilder(" Attacker ", ip,
					"update user "+user.getName()+" "+user.getFamily());
			new EventEntityManager().saveEvent(description.toString(), guest);
			
			return Response.status(405).entity("Access denied").build();

		} else if (client.getAccessLevel() == 3) {
			UserEntityManager entityManager = new UserEntityManager();
			boolean status = entityManager.updateUser(user);
			if (status) {

				EventDescriptionBuilder description = new EventDescriptionBuilder(" updated user successfully ", ip,
						"update user "+user.getName()+" "+user.getFamily());
				new EventEntityManager().saveEvent(description.toString(), client);
				return Response.status(200).entity("success").build();
			} else {

				EventDescriptionBuilder description = new EventDescriptionBuilder(" wrong information ", ip,
						"update user "+user.getName()+" "+user.getFamily());
				new EventEntityManager().saveEvent(description.toString(), client);
				return Response.status(200).entity("failed").build();

			}
		} else {

			EventDescriptionBuilder description = new EventDescriptionBuilder(" Access denied ", ip,
					"update user "+user.getName()+" "+user.getFamily());
			new EventEntityManager().saveEvent(description.toString(), client);
			return Response.status(405).entity("Access denied").build();

		}

	}

	@Path("/DeleteUser")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteUser(String username, @Context HttpServletRequest request) {

		// access level only for general manager
		String ip = request.getRemoteAddr();
		User user = onlineUsers.get(ip);
		if (user == null) {
			
			EventDescriptionBuilder description = new EventDescriptionBuilder(" Attecker ", ip,
					"Delete user "+username);
			new EventEntityManager().saveEvent(description.toString(), guest);
			
			return Response.status(405).entity("Access denied").build();

		} else if (user.getAccessLevel() == 3) {

			UserEntityManager entityManager = new UserEntityManager();
			boolean status = entityManager.deleteUser(username);
			if (status) {

				EventDescriptionBuilder description = new EventDescriptionBuilder(" deleted successfully ", ip,
						"Delete user "+username);
				new EventEntityManager().saveEvent(description.toString(), user);
				return Response.status(200).entity("success").build();
			} else {

				EventDescriptionBuilder description = new EventDescriptionBuilder(" failed to delete ", ip,
						"Delete user "+username);
				new EventEntityManager().saveEvent(description.toString(), user);
				return Response.status(200).entity("failed").build();

			}
		} else {

			EventDescriptionBuilder description = new EventDescriptionBuilder(" Access denied ", ip,
					"Delete user "+username);
			new EventEntityManager().saveEvent(description.toString(), user);
			return Response.status(405).entity("Access denied").build();
		}

	}

}
