package com.pankiba.restfulwebservices.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pankiba.restfulwebservices.util.CustomJsonDateDeserializer;
import com.pankiba.restfulwebservices.util.CustomJsonDateSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@ApiModel(description = "All detais about employee.")
public class Employee implements Serializable {

	private static final long serialVersionUID = 6798516679232905689L;

	@Id
	private Long employeeId;

	@Size(min = 4, max = 20, message = "FirstName should have 4 to 20 characters")
	@ApiModelProperty(notes = "FirstName should have 4 to 20 characters")
	private String firstName;

	@Size(min = 4, max = 20, message = "LastName should have 4 to 20 characters")
	@ApiModelProperty(notes = "LastName should have 4 to 20 characters")
	private String lastName;

	private String gender;

	@Column(unique = true)
	private String email;

	@Past
	@ApiModelProperty(notes = "Birth date should be in past")
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	private Date birthDate;

	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	private Date joiningDate;

	@JsonIgnore
	private Long salary;

	/**
	 * JoinColumn says that Employee table will contain a separate column
	 * BUSINESS_UNIT_ID which will act as a foreign key reference to primary key of
	 * BUSINESS_UNIT table.
	 *
	 * ManyToOne annotation is used to define a single value association with
	 * another entity bean.
	 *
	 * Many to one side is the owning side of the relationship
	 * 
	 * @JsonBackReference is the back part of reference – it will be omitted from
	 *                    serialization.
	 * 
	 */
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "BUSINESS_UNIT_ID")
	private BusinessUnit businessUnit;

	public Employee(Long employeeId, String firstName, String lastName, String gender, String email, Date birthDate,
			Date joiningDate, Long salary) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.birthDate = birthDate;
		this.joiningDate = joiningDate;
		this.salary = salary;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + Objects.hashCode(employeeId);
		result = prime * result + Objects.hashCode(firstName);
		result = prime * result + Objects.hashCode(lastName);
		result = prime * result + Objects.hashCode(gender);
		result = prime * result + Objects.hashCode(email);
		result = prime * result + Objects.hashCode(birthDate);
		result = prime * result + Objects.hashCode(joiningDate);
		result = prime * result + Objects.hashCode(salary);
		return result;
	}

	@Override
	public boolean equals(Object object) {

		// self check
		if (this == object)
			return true;

		// null check
		if (object == null)
			return false;

		// type check. we can do instance of check instead but it will pass for subclass
		// too
		if (getClass() != object.getClass())
			return false;

		// cast
		Employee employee = (Employee) object;

		return Objects.equals(employeeId, employee.getEmployeeId())
				&& Objects.equals(firstName, employee.getFirstName())
				&& Objects.equals(lastName, employee.getLastName()) && Objects.equals(gender, employee.getGender())
				&& Objects.equals(email, employee.getEmail()) && Objects.equals(birthDate, employee.getBirthDate())
				&& Objects.equals(joiningDate, employee.getJoiningDate())
				&& Objects.equals(salary, employee.getSalary());
	}

}
