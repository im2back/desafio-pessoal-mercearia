package com.example.im2back.mercearia.infra.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalHandlerExceptions {

	@Autowired
	private MessageSource messageSource;
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,HttpServletRequest request, BindingResult bindingResult
			,RedirectAttributes redirectAttributes) {
			
		var erroList = criarListaDeErros(bindingResult);
	    	redirectAttributes.addFlashAttribute("errorMessages", erroList);
	        // Redirecione para a página anterior
	    	String referer = request.getHeader("Referer");
	    	return "redirect:" + referer;
	    }
	
	@ExceptionHandler(ServiceExceptions.class)
	@ResponseStatus(HttpStatus.FOUND)
	public String handleServiceExceptions(ServiceExceptions ex,HttpServletRequest request, RedirectAttributes redirectAttributes) {
			
			
	    	redirectAttributes.addFlashAttribute("serviceMessage", ex.getMessage());
	        // Redirecione para a página anterior
	        String referer = request.getHeader("Referer");
	        return "redirect:" + referer;
	    }
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.FOUND)
	public String handleDataIntegrityViolationException(DataIntegrityViolationException ex,HttpServletRequest request, BindingResult bindingResult
			,RedirectAttributes redirectAttributes) {
			
		var erroList = criarListaDeErros(bindingResult);
	    	redirectAttributes.addFlashAttribute("errorMessages", erroList);
	        // Redirecione para a página anterior
	    	String referer = request.getHeader("Referer");
	    	return "redirect:" + referer;
	    }
	
	@ExceptionHandler(JWTExceptions.class)
	@ResponseStatus(HttpStatus.FOUND)
	public String handleJWTExceptions(JWTExceptions ex,HttpServletRequest request, RedirectAttributes redirectAttributes) {
	    	redirectAttributes.addFlashAttribute("JWTMessage", ex.getMessage());
	        return "redirect:/login";
	    }


	
	
	
	private List<String> criarListaDeErros(BindingResult bindingResult) {
		List<String> erros = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) { // laco for que pede todos os erros que ocorreram
																		// nos campos dos atributos que foram validados
			String mensagemUsuario = fieldError.getField() + ":"
					+ messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			erros.add(mensagemUsuario);

		}
		return erros;
	}

}
