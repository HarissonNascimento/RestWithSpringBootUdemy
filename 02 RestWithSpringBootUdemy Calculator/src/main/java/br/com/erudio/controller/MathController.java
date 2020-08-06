package br.com.erudio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exception.UnsuportedMathOperationException;
import br.com.erudio.math.SimpleMath;
import br.com.erudio.request.converters.NumberConverter;

@RestController
public class MathController {
	@Autowired
	private SimpleMath math;
	
	@Autowired
	private NumberConverter converter;
	
	@RequestMapping(value="sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		if(!converter.isNumeric(numberOne) || !converter.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value");
		}
		Double sum = math.sum(converter.convertToDouble(numberOne), converter.convertToDouble(numberTwo));
		return sum;
		
	}
	
	@RequestMapping(value="subtraction/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double subtraction(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		if(!converter.isNumeric(numberOne) || !converter.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value");
		}
		Double subtraction = math.subtraction(converter.convertToDouble(numberOne), converter.convertToDouble(numberTwo));
		return subtraction;
	}
	
	@RequestMapping(value="multiplication/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double multiplication(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		if(!converter.isNumeric(numberOne) || !converter.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value");
		}
		Double multiplication = math.multiplicate(converter.convertToDouble(numberOne), converter.convertToDouble(numberTwo));
		return multiplication;
	}
	
	@RequestMapping(value="division/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double division(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		if(!converter.isNumeric(numberOne) || !converter.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value");
		}
		Double division = math.division(converter.convertToDouble(numberOne), converter.convertToDouble(numberTwo));
		return division;
	}
	
	
	@RequestMapping(value="average/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double average(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		if(!converter.isNumeric(numberOne) || !converter.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value");
		}
		Double average = math.mean(converter.convertToDouble(numberOne), converter.convertToDouble(numberTwo));
		return average;
	}
	
	@RequestMapping(value="square/{numberOne}", method=RequestMethod.GET)
	public Double square(@PathVariable("numberOne") String numberOne) throws Exception {
		if(!converter.isNumeric(numberOne)) {
			throw new UnsuportedMathOperationException("Please set a numeric value");
		}
		Double square = math.square(converter.convertToDouble(numberOne));
		return square;
	}
	

	
}
