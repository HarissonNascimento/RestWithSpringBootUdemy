package br.com.erudio.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@Autowired
	private PagedResourcesAssembler<PersonVO> assembler;
	
	// @CrossOrigin(value="http://localhost:8080")
	@ApiOperation(value="Find all people")
	@GetMapping(produces= {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> findAll(@RequestParam (value="page", defaultValue = "0") int page,
								@RequestParam (value="limit", defaultValue = "12") int limit,
								@RequestParam (value="direction", defaultValue = "asc") String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
		
		Page<PersonVO> personVOList = services.findAll(pageable);
		personVOList.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<?> resources = assembler.toModel(personVOList);
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}
	
	@ApiOperation(value="Find all people with token name")
	@GetMapping(value="/findPersonByName/{firstName}", produces= {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> findPersonByName(@RequestParam (value="page", defaultValue = "0") int page,
								@PathVariable("firstName") String firstName,
								@RequestParam (value="limit", defaultValue = "12") int limit,
								@RequestParam (value="direction", defaultValue = "asc") String direction){
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));
		
		Page<PersonVO> personVOList = services.findPersonByName(firstName, pageable);
		personVOList.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<?> resources = assembler.toModel(personVOList);
		
		return new ResponseEntity<>(resources, HttpStatus.OK);
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
	
	@ApiOperation(value="Disabe a specific person by your ID")
	@PatchMapping(value="/{id}", produces= {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO disablePerson(@PathVariable("id") Long id){
		PersonVO personVO = services.disablePerson(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	@ApiOperation(value="Delete a person")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		 services.delete(id);
		 return ResponseEntity.ok().build();
	}
	
}
