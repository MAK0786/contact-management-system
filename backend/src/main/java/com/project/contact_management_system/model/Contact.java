package com.project.contact_management_system.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Schema(description = "Represents a contact in the address book.")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Schema(description = "Contact name", example = "John Doe")
    @NotBlank(message = "Name is required")
    private String name;
    
    @Email(message="Inalid Email Address!")
    @NotBlank(message = "Email Address is required")
    @Schema(description = "Contact Email", example = "john@example.com")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
    @Schema(description = "Phone number", example = "9876543210")
    private String phone;
    
    @Schema(description = "Home address", example = "123 Main St, NY")
    private String address;

    //public Contact() {}

    public Contact(String name,String email,String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // public List<Contact> deleteByName(String name){
    //     for (int i=0 ; i < name.length(); i++){
    //         return name[i];
    //     }
    //}
}
