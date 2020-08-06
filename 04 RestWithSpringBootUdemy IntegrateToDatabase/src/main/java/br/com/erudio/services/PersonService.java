package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.request.repository.PersonRepository;

@Service //serve para que o spring cuide da injeção de dependência dessa classe onde for necessário
public class PersonService {
	
	@Autowired
	PersonRepository repository;
	
	public Person create(Person person) {
		return repository.save(person);
	}
	
	public List<Person> findAll() {
		return repository.findAll() ;
	}
	
	public Person findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found this ID"));
	}
	
	public Person update(Person person) {
		Person entity = repository.
				findById(person.
						getId()).
				orElseThrow(() -> new ResourceNotFoundException("No records found this ID"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return repository.save(entity) ;
	}

	public void delete(Long id) {
		Person entity = repository.
				findById(id).
				orElseThrow(() -> new ResourceNotFoundException("No records found this ID"));
		repository.delete(entity);
	}
	
	
	
	


}
