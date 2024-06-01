package com.example.springbatch.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SequenceGenerator(name = "employee_id_seq", sequenceName = "employee_id_seq", allocationSize = 10)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq")
    private Integer id;
    @NotNull
    private String name;
    private BigDecimal salary;
    private Integer age;
    private String gender;
    @NotEmpty(message = "Email is Mandatory")
    private String email;

    /*@NotNull: Ensures a field is not null.
@NotBlank: Enforces non-nullity and requires at least one non-whitespace character.
@NotEmpty: Guarantees that collections or arrays are not empty.
@Min(value): Checks if a numeric field is greater than or equal to the specified minimum value.
@Max(value): Checks if a numeric field is less than or equal to the specified maximum value.
@Size(min, max): Validates if a string or collection size is within a specific range.
@Pattern(regex): Verifies if a field matches the provided regular expression.
@Email: Ensures a field contains a valid email address format.
@Digits(integer, fraction): Validates that a numeric field has a specified number of integer and fraction digits.
@Past and @Future : Checks that a date or time field is in the past and future respectively.
@AssertTrue and @AssertFalse: Ensures that a boolean field is true. and false respectively.
@CreditCardNumber: Validates that a field contains a valid credit card number.
@Valid: Triggers validation of nested objects or properties.
@Validated: Specifies validation groups to be applied at the class or method level.*/


}
