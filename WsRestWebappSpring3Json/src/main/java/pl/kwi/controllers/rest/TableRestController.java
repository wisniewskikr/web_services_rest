package pl.kwi.controllers.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import pl.kwi.commands.TableCommand;

@Controller
@RequestMapping("/table")
public class TableRestController{
	
	@RequestMapping("/")
	public ModelAndView displayPage(
			@ModelAttribute("command") TableCommand command,
			HttpServletRequest request, 
			HttpServletResponse response){
		
		return new ModelAndView("tableJsp");
		
	}
	
	@RequestMapping(value="/create-button")
	public ModelAndView handleCreateButton(
			@ModelAttribute("command")TableCommand command,
			HttpServletRequest request, 
			HttpServletResponse response){
		
		return new ModelAndView(new RedirectView("/create/", true, true, true));
		
	}
	
	@RequestMapping("/view-link/{id}")
	public ModelAndView handleViewLink(
			@ModelAttribute("command")TableCommand command,
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable Long id){
		
		return new ModelAndView(new RedirectView("/view/" + id, true, true, true));
		
	}
	
	@RequestMapping("/edit-link/{id}")
	public ModelAndView handleEditLink(
			@ModelAttribute("command")TableCommand command,
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable Long id){
		
		return new ModelAndView(new RedirectView("/edit/" + id, true, true, true));
		
	}
	
	@RequestMapping("/delete-link/{id}")
	public ModelAndView handleDeleteLink(
			@ModelAttribute("command")TableCommand command,
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable Long id){
		
		return new ModelAndView(new RedirectView("/delete/" + id, true, true, true));
		
	}


}
