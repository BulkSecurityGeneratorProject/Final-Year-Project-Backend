package com.itlc.thelearningzone.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Subject.
 */
@Entity
@Table(name = "subject")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "subject_topic",
               joinColumns = @JoinColumn(name = "subjects_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "topics_id", referencedColumnName = "id"))
    private Set<Topic> topics = new HashSet<>();

    @OneToMany(mappedBy = "subject")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Booking> bookings = new HashSet<>();
    @ManyToMany(mappedBy = "subjects")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Semester> semesters = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Subject title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public Subject topics(Set<Topic> topics) {
        this.topics = topics;
        return this;
    }

    public Subject addTopic(Topic topic) {
        this.topics.add(topic);
        topic.getSubjects().add(this);
        return this;
    }

    public Subject removeTopic(Topic topic) {
        this.topics.remove(topic);
        topic.getSubjects().remove(this);
        return this;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public Subject bookings(Set<Booking> bookings) {
        this.bookings = bookings;
        return this;
    }

    public Subject addBooking(Booking booking) {
        this.bookings.add(booking);
        booking.setSubject(this);
        return this;
    }

    public Subject removeBooking(Booking booking) {
        this.bookings.remove(booking);
        booking.setSubject(null);
        return this;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public Set<Semester> getSemesters() {
        return semesters;
    }

    public Subject semesters(Set<Semester> semesters) {
        this.semesters = semesters;
        return this;
    }

    public Subject addSemester(Semester semester) {
        this.semesters.add(semester);
        semester.getSubjects().add(this);
        return this;
    }

    public Subject removeSemester(Semester semester) {
        this.semesters.remove(semester);
        semester.getSubjects().remove(this);
        return this;
    }

    public void setSemesters(Set<Semester> semesters) {
        this.semesters = semesters;
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
        Subject subject = (Subject) o;
        if (subject.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), subject.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Subject{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            "}";
    }
}