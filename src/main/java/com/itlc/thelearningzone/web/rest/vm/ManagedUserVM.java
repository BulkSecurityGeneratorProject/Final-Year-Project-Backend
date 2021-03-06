package com.itlc.thelearningzone.web.rest.vm;

import com.itlc.thelearningzone.service.dto.UserDTO;

import javax.validation.constraints.Size;

/**
 * View Model extending the UserDTO, which is meant to be used in the user management UI.
 */
public class ManagedUserVM extends UserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;
    
    private Long courseYearId;

    public ManagedUserVM() {
        // Empty constructor needed for Jackson.
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setCourseYearId(Long courseYearId) {
	    this.courseYearId = courseYearId;
	}
    
    public Long getCourseYearId() {
		return courseYearId;
	}

    @Override
    public String toString() {
        return "ManagedUserVM{" +
        		"courseYearId: " + getCourseYearId() +
            "} " + super.toString();
    }

}
