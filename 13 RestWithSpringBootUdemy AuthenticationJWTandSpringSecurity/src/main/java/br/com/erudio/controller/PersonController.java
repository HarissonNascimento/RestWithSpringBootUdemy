package br.com.erudio.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

// @CrossOrigin
@Api(value="Person Endpoint", tags= {"PersonEndpoint"},
produces= "application/json, application/xml, application/x-yaml",
consumes="application/json, application/xml, application/x-yaml")
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@Autowired
	private PersonService services;
	
	// @CrossOrigin(value="http://localhost:8080")
	@ApiOperation(value="Find all people")
	@GetMapping(produces= {"application/json", "application/xml", "application/x-yaml"})
	public List<PersonVO> findAll(){
		List<PersonVO> personVOList = services.findAll();
		personVOList.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getId())).withSelfRel()));
		return personVOList;
	}
	
	// @CrossOrigin(value={"http://localhost:8080", "http://www.erudio.com.br"})
	@ApiOperation(value="Find person by id")
	@GetMapping(value="/{id}", produces= {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO findById(@PathVariable("id") Long id){
		PersonVO personVO = services.findById(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value="Add new person")
	@PostMapping(produces= {"application/json", "application/xml", "application/x-yaml"},
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO create(@RequestBody PersonVO PersonVO){
		PersonVO personVO = services.create(PersonVO);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getId())).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value="Edit a person")
	@PutMapping(produces= {"application/json", "application/xml", "application/x-yaml"},
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO update(@RequestBody PersonVO PersonVO){
		PersonVO personVO = services.update(PersonVO);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getId())).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value="Delete a person")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		 services.delete(id);
		 return ResponseEntity.ok().build();
	}
	
}
