package study.spring.jpaexam;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String street;

	private String city;

	private String status;

	private String zipCode;

}
