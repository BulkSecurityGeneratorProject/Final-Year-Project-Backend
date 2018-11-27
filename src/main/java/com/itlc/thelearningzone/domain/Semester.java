package com.itlc.thelearningzone.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.itlc.thelearningzone.domain.enumeration.SemesterNumber;

/**
 * A Semester.
 */
@Entity
@Table(name = "semester")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Semester implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "semester_number", nullable = false)
    private SemesterNumber semesterNumber;

    @Column(name = "semester_start_date")
    private LocalDate semesterStartDate;

    @Column(name = "semester_end_date")
    private LocalDate semesterEndDate;

    @OneToMany(mappedBy = "semester")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SemesterGroup> semesterGroups = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("semesters")
    private CourseYear courseYear;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SemesterNumber getSemesterNumber() {
        return semesterNumber;
    }

    public Semester semesterNumber(SemesterNumber semesterNumber) {
        this.semesterNumber = semesterNumber;
        return this;
    }

    public void setSemesterNumber(SemesterNumber semesterNumber) {
        this.semesterNumber = semesterNumber;
    }

    public LocalDate getSemesterStartDate() {
        return semesterStartDate;
    }

    public Semester semesterStartDate(LocalDate semesterStartDate) {
        this.semesterStartDate = semesterStartDate;
        return this;
    }

    public void setSemesterStartDate(LocalDate semesterStartDate) {
        this.semesterStartDate = semesterStartDate;
    }

    public LocalDate getSemesterEndDate() {
        return semesterEndDate;
    }

    public Semester semesterEndDate(LocalDate semesterEndDate) {
        this.semesterEndDate = semesterEndDate;
        return this;
    }

    public void setSemesterEndDate(LocalDate semesterEndDate) {
        this.semesterEndDate = semesterEndDate;
    }

    public Set<SemesterGroup> getSemesterGroups() {
        return semesterGroups;
    }

    public Semester semesterGroups(Set<SemesterGroup> semesterGroups) {
        this.semesterGroups = semesterGroups;
        return this;
    }

    public Semester addSemesterGroup(SemesterGroup semesterGroup) {
        this.semesterGroups.add(semesterGroup);
        semesterGroup.setSemester(this);
        return this;
    }

    public Semester removeSemesterGroup(SemesterGroup semesterGroup) {
        this.semesterGroups.remove(semesterGroup);
        semesterGroup.setSemester(null);
        return this;
    }

    public void setSemesterGroups(Set<SemesterGroup> semesterGroups) {
        this.semesterGroups = semesterGroups;
    }

    public CourseYear getCourseYear() {
        return courseYear;
    }

    public Semester courseYear(CourseYear courseYear) {
        this.courseYear = courseYear;
        return this;
    }

    public void setCourseYear(CourseYear courseYear) {
        this.courseYear = courseYear;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Semester semester = (Semester) o;
        if (semester.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), semester.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Semester{" +
            "id=" + getId() +
            ", semesterNumber='" + getSemesterNumber() + "'" +
            ", semesterStartDate='" + getSemesterStartDate() + "'" +
            ", semesterEndDate='" + getSemesterEndDate() + "'" +
            "}";
    }
}
