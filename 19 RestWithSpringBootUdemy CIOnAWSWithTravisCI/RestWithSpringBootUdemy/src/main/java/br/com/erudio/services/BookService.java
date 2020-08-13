package br.com.erudio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.model.Book;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.request.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository repository;
	
	public BookVO create(BookVO book) {
		var entity = DozerConverter.parseObject(book, Book.class);
		var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}
	
	public Page<BookVO> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToBookVO);
	}
	
	public Page<BookVO> findBookByAuthor(String author, Pageable pageable) {
		var page = repository.findBookByAuthor(author, pageable);
		return page.map(this::convertToBookVO);
	}
	
	private BookVO convertToBookVO(Book entity) {
		return DozerConverter.parseObject(entity, BookVO.class);
	}
	
	public BookVO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found this ID"));
		return DozerConverter.parseObject(entity, BookVO.class);
	}
	
	public BookVO update(BookVO book) {
		var entity = repository.
				findById(book.
						getId()).
				orElseThrow(() -> new ResourceNotFoundException("No records found this ID"));
		
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setTitle(book.getTitle());
		entity.setPrice(book.getPrice());
		
		var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}

	public void delete(Long id) {
		Book entity = repository.
				findById(id).
				orElseThrow(() -> new ResourceNotFoundException("No records found this ID"));
		repository.delete(entity);
	}


}
