package br.edu.ifpb.mt.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.mt.googlewebservices.GoogleWebServicesResponse;
import br.edu.ifpb.mt.model.Doador;

/**
 * 
 * @author <a href="https://github.com/JoseRafael97">Rafael Feitosa</a>
 *
 */
@RestController
public class ApiRestService {

	@Autowired
	private GoogleWebServicesResponse googleWebServicesResponse;


	@RequestMapping(value = "/mesageiromaisproximo", method = RequestMethod.GET)
	public ResponseEntity<?> mesageiroMaisProximo() {

		
		return new ResponseEntity<Doador>(HttpStatus.CREATED);
	}


}
