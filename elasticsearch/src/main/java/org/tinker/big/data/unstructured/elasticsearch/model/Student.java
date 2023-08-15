package org.tinker.big.data.unstructured.elasticsearch.model;

import java.util.Objects;

public class Student {

    private String studentId;
    private String studentName;

    //must have this empty constructor
    public Student() {
    }

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (this.studentId != null ? this.studentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        return Objects.equals(this.studentId, other.studentId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                '}';
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

}
