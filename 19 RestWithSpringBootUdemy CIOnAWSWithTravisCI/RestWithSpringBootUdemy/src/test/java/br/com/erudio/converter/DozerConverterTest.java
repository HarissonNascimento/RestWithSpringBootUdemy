package br.com.erudio.converter;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.erudio.converter.mocks.MockBook;
import br.com.erudio.converter.mocks.MockPerson;
import br.com.erudio.data.model.Book;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.data.vo.v1.PersonVO;

public class DozerConverterTest {
	
	MockPerson inputPerson;
	MockBook inputBook;
	Date date;

    @Before
    public void setUp() {
        inputPerson = new MockPerson();
        inputBook = new MockBook();
        date = new Date();
    }

    @Test
    public void parseEntityToVOTest() {
        PersonVO outputPersonVO = DozerConverter.parseObject(inputPerson.mockEntity(), PersonVO.class);
        Assert.assertEquals(Long.valueOf(0L), outputPersonVO.getId());
        Assert.assertEquals("First Name Test0", outputPersonVO.getFirstName());
        Assert.assertEquals("Last Name Test0", outputPersonVO.getLastName());
        Assert.assertEquals("Addres Test0", outputPersonVO.getAddress());
        Assert.assertEquals("Male", outputPersonVO.getGender());
        
        date.setTime(0);
      
        BookVO outputBookVO = DozerConverter.parseObject(inputBook.mockEntity(), BookVO.class);
        Assert.assertEquals(Long.valueOf(0L), outputBookVO.getId());
        Assert.assertEquals("Author Test0", outputBookVO.getAuthor());
        Assert.assertEquals("Title Test0", outputBookVO.getTitle());
        Assert.assertEquals(date.getTime(), outputBookVO.getLaunchDate().getTime());
        Assert.assertEquals(Double.valueOf(0D), outputBookVO.getPrice());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<PersonVO> outputPersonList = DozerConverter.parseListObjects(inputPerson.mockEntityList(), PersonVO.class);
        List<BookVO> outputBookList = DozerConverter.parseListObjects(inputBook.mockEntityList(), BookVO.class);
        
        PersonVO outputPersonZero = outputPersonList.get(0);
        BookVO outputBookZero = outputBookList.get(0);
        date.setTime(0);
        
        Assert.assertEquals(Long.valueOf(0L), outputPersonZero.getId());
        Assert.assertEquals("First Name Test0", outputPersonZero.getFirstName());
        Assert.assertEquals("Last Name Test0", outputPersonZero.getLastName());
        Assert.assertEquals("Addres Test0", outputPersonZero.getAddress());
        Assert.assertEquals("Male", outputPersonZero.getGender());
        
        Assert.assertEquals(Long.valueOf(0L), outputBookZero.getId());
        Assert.assertEquals("Author Test0", outputBookZero.getAuthor());
        Assert.assertEquals("Title Test0", outputBookZero.getTitle());
        Assert.assertEquals(date.getTime(), outputBookZero.getLaunchDate().getTime());
        Assert.assertEquals(Double.valueOf(0D), outputBookZero.getPrice());
        
        PersonVO outputPersonSeven = outputPersonList.get(7);
        BookVO outputBookSeven = outputBookList.get(7);
        date.setTime(7);
        
        Assert.assertEquals(Long.valueOf(7L), outputPersonSeven.getId());
        Assert.assertEquals("First Name Test7", outputPersonSeven.getFirstName());
        Assert.assertEquals("Last Name Test7", outputPersonSeven.getLastName());
        Assert.assertEquals("Addres Test7", outputPersonSeven.getAddress());
        Assert.assertEquals("Female", outputPersonSeven.getGender());
        
        Assert.assertEquals(Long.valueOf(7L), outputBookSeven.getId());
        Assert.assertEquals("Author Test7", outputBookSeven.getAuthor());
        Assert.assertEquals("Title Test7", outputBookSeven.getTitle());
        Assert.assertEquals(date.getTime(), outputBookSeven.getLaunchDate().getTime());
        Assert.assertEquals(Double.valueOf(7D), outputBookSeven.getPrice());
        
        PersonVO outputPersonTwelve = outputPersonList.get(12);
        BookVO outputBookTwelve = outputBookList.get(12);
        date.setTime(12);
        
        Assert.assertEquals(Long.valueOf(12L), outputPersonTwelve.getId());
        Assert.assertEquals("First Name Test12", outputPersonTwelve.getFirstName());
        Assert.assertEquals("Last Name Test12", outputPersonTwelve.getLastName());
        Assert.assertEquals("Addres Test12", outputPersonTwelve.getAddress());
        Assert.assertEquals("Male", outputPersonTwelve.getGender());
        
        Assert.assertEquals(Long.valueOf(12L), outputBookTwelve.getId());
        Assert.assertEquals("Author Test12", outputBookTwelve.getAuthor());
        Assert.assertEquals("Title Test12", outputBookTwelve.getTitle());
        Assert.assertEquals(date.getTime(), outputBookTwelve.getLaunchDate().getTime());
        Assert.assertEquals(Double.valueOf(12D), outputBookTwelve.getPrice());
    }

    @Test
    public void parseVOToEntityTest() {
        Person outputPerson = DozerConverter.parseObject(inputPerson.mockVO(), Person.class);
        Book outputBook = DozerConverter.parseObject(inputBook.mockVO(), Book.class);
        
        Assert.assertEquals(Long.valueOf(0L), outputPerson.getId());
        Assert.assertEquals("First Name Test0", outputPerson.getFirstName());
        Assert.assertEquals("Last Name Test0", outputPerson.getLastName());
        Assert.assertEquals("Addres Test0", outputPerson.getAddress());
        Assert.assertEquals("Male", outputPerson.getGender());
        
        date.setTime(0);
        
        Assert.assertEquals(Long.valueOf(0L), outputBook.getId());
        Assert.assertEquals("Author Test0", outputBook.getAuthor());
        Assert.assertEquals("Title Test0", outputBook.getTitle());
        Assert.assertEquals(date.getTime(), outputBook.getLaunchDate().getTime());
        Assert.assertEquals(Double.valueOf(0D), outputBook.getPrice());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Person> outputPersonVOList = DozerConverter.parseListObjects(inputPerson.mockVOList(), Person.class);
        List<Book> outputBookVOList = DozerConverter.parseListObjects(inputBook.mockVOList(), Book.class);
        
        Person outputPersonVOZero = outputPersonVOList.get(0);
        Book outputBookVOZero = outputBookVOList.get(0);
        date.setTime(0);
        
        Assert.assertEquals(Long.valueOf(0L), outputPersonVOZero.getId());
        Assert.assertEquals("First Name Test0", outputPersonVOZero.getFirstName());
        Assert.assertEquals("Last Name Test0", outputPersonVOZero.getLastName());
        Assert.assertEquals("Addres Test0", outputPersonVOZero.getAddress());
        Assert.assertEquals("Male", outputPersonVOZero.getGender());
        
        Assert.assertEquals(Long.valueOf(0L), outputBookVOZero.getId());
        Assert.assertEquals("Author Test0", outputBookVOZero.getAuthor());
        Assert.assertEquals("Title Test0", outputBookVOZero.getTitle());
        Assert.assertEquals(date.getTime(), outputBookVOZero.getLaunchDate().getTime());
        Assert.assertEquals(Double.valueOf(0D), outputBookVOZero.getPrice());
        
        Person outputPersonVOSeven = outputPersonVOList.get(7);
        Book outputBookVOSeven = outputBookVOList.get(7);
        date.setTime(7);
        
        Assert.assertEquals(Long.valueOf(7L), outputPersonVOSeven.getId());
        Assert.assertEquals("First Name Test7", outputPersonVOSeven.getFirstName());
        Assert.assertEquals("Last Name Test7", outputPersonVOSeven.getLastName());
        Assert.assertEquals("Addres Test7", outputPersonVOSeven.getAddress());
        Assert.assertEquals("Female", outputPersonVOSeven.getGender());
        
        Assert.assertEquals(Long.valueOf(7L), outputBookVOSeven.getId());
        Assert.assertEquals("Author Test7", outputBookVOSeven.getAuthor());
        Assert.assertEquals("Title Test7", outputBookVOSeven.getTitle());
        Assert.assertEquals(date.getTime(), outputBookVOSeven.getLaunchDate().getTime());
        Assert.assertEquals(Double.valueOf(7D), outputBookVOSeven.getPrice());
        
        Person outputPersonVOTwelve = outputPersonVOList.get(12);
        Book outputBookVOTwelve = outputBookVOList.get(12);
        date.setTime(12);
        
        Assert.assertEquals(Long.valueOf(12L), outputPersonVOTwelve.getId());
        Assert.assertEquals("First Name Test12", outputPersonVOTwelve.getFirstName());
        Assert.assertEquals("Last Name Test12", outputPersonVOTwelve.getLastName());
        Assert.assertEquals("Addres Test12", outputPersonVOTwelve.getAddress());
        Assert.assertEquals("Male", outputPersonVOTwelve.getGender());
        
        Assert.assertEquals(Long.valueOf(12L), outputBookVOTwelve.getId());
        Assert.assertEquals("Author Test12", outputBookVOTwelve.getAuthor());
        Assert.assertEquals("Title Test12", outputBookVOTwelve.getTitle());
        Assert.assertEquals(date.getTime(), outputBookVOTwelve.getLaunchDate().getTime());
        Assert.assertEquals(Double.valueOf(12D), outputBookVOTwelve.getPrice());
        
    }
	
}
